package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.bo.gamelevel.GameLevel;
import siyi.game.dao.GamelevelConfigMapper;
import siyi.game.dao.QuTianziMapper;
import siyi.game.dao.QuXuanzeMapper;
import siyi.game.dao.entity.*;
import siyi.game.manager.excel.read.GameLevelConfigDataListener;
import siyi.game.manager.excel.read.QuXuanzeDataListener;
import siyi.game.service.gamelevel.GameLevelService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.player.PlayerService;
import siyi.game.utill.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 跟游戏关卡相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "gameLevel")
public class GameLevelController {
    private final Logger logger = LoggerFactory.getLogger(GameLevelController.class);

    @Autowired
    private QuTianziMapper quTianziMapper;
    @Autowired
    GamelevelConfigMapper configMapper;
    @Autowired
    private QuXuanzeMapper quXuanzeMapper;

    @Autowired
    private GameLevelService gameLevelService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;

    @RequestMapping(value = "queryGameLevelInfo")
    @ResponseBody
    public GameLevel queryGameLevelInfo(String gameLevelType, String qType) {
        GameLevel gameLevel = new GameLevel();
        try {
            if (Constants.GAME_LEVEL_TYPE_WEN.equals(gameLevelType)) {
                gameLevel = gameLevelService.queryWenGameLevelInfo();
            } else if (Constants.GAME_LEVEL_TYPE_WU.equals(gameLevelType)) {
                gameLevel = gameLevelService.queryWuGameLevelInfo();
            } else
                gameLevel = null;
            return gameLevel;
        } catch (Exception e) {
            logger.error("获取关卡信息失败：{}", e);
            return null;
        } finally {

        }
    }


    @RequestMapping(value = "fileAnalysis")
    @ResponseBody
    public void fileAnalysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_xuanze.xlsx";
            EasyExcel.read(filePath, QuXuanze.class, new QuXuanzeDataListener(quXuanzeMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "submitGame")
    @ResponseBody
    public void submitGame(Player player, List<ItemPlayerRelation> itemPlayerRelations) {
        logger.info("提交游戏数据，开始更新数据库");
        logger.info("获取玩家信息：{}", player.toString());
        logger.info("获取道具信息：{}", itemPlayerRelations.toString());
        String playerId = player.getPlayerId();
        Player findPlayer = playerService.selectByPlayerId(playerId);
        String experience = player.getExperience();
        String gold = player.getGold();
        // 更新玩家经验及金币信息
        int finalExp = Integer.valueOf(experience) + Integer.valueOf(findPlayer.getExperience());
        int finalGold = Integer.valueOf(gold) + Integer.valueOf(findPlayer.getGold());
        findPlayer.setExperience(String.valueOf(finalExp));
        findPlayer.setGold(String.valueOf(finalGold));
        playerService.updateByIdSelective(findPlayer);
        // 更新道具关联关系
        List<ItemPlayerRelation> existRelations = new ArrayList<>();
        List<ItemPlayerRelation> notExistRelations = new ArrayList<>();
        List<ItemPlayerRelation> relations = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemPlayerRelations)) {
            relations = itemPlayerRelationService.selectByPlayerIdAndItemNoList(itemPlayerRelations);
            relations.forEach(itemPlayerRelation -> existRelations.addAll(
                    itemPlayerRelations.stream().filter(
                            exitRelation -> exitRelation.getItemNo().equals(itemPlayerRelation.getItemNo())
                                    && exitRelation.getPlayerId().equals(itemPlayerRelation.getPlayerId())
                                    && exitRelation.getGameCode().equals(itemPlayerRelation.getGameCode()))
                            .collect(Collectors.toList())));
            itemPlayerRelations.removeAll(existRelations);
            notExistRelations.addAll(itemPlayerRelations);
        }
        logger.info("已有道具列表：{}", existRelations.toString());
        logger.info("新增道具列表：{}", notExistRelations.toString());
        if (!CollectionUtils.isEmpty(notExistRelations)) {
            logger.info("存在新增道具，开始新增");
            itemPlayerRelationService.insertListSelective(notExistRelations);
            logger.info("新增结束");
        }
        if (!CollectionUtils.isEmpty(existRelations)) {
            logger.info("存在已有道具信息，开始更新");
            for (ItemPlayerRelation relation : relations) {
                Long id = relation.getId();
                String gameCode = relation.getGameCode();
                String itemNo = relation.getItemNo();
                String quantity = relation.getQuantity();
                String playerId1 = relation.getPlayerId();
                for (ItemPlayerRelation existRelation : existRelations) {
                    if (playerId1.equals(existRelation.getPlayerId()) && itemNo.equals(existRelation.getItemNo()) && gameCode.equals(existRelation.getGameCode())) {
                        existRelation.setId(id);
                        int newQuantity = Integer.valueOf(quantity) + Integer.valueOf(existRelation.getQuantity());
                        existRelation.setQuantity(String.valueOf(newQuantity));
                    }
                }
            }
            logger.info("获取最后已有道具信息：{}", existRelations.toString());
            itemPlayerRelationService.updateQuantityListById(existRelations);
            logger.info("更新结束");
        }
    }
}
