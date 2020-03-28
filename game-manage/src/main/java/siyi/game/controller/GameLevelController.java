package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
import siyi.game.service.gamelevel.LevelUpService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.player.PlayerService;
import siyi.game.utill.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: 跟游戏关卡相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "idiom/gameLevel")
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

    @Autowired
    private LevelUpService levelUpService;


    @RequestMapping(value = "queryGameLevelInfo")
    @ResponseBody
    public GameLevel queryGameLevelInfo(String gameLevelType,String qType, String preQType, String userId, String preQID, String preStatus) {
        GameLevel gameLevel = new GameLevel();
        try {
            if (Constants.GAME_LEVEL_TYPE_WEN.equals(gameLevelType)) {
                gameLevel = gameLevelService.queryWenGameLevelInfo(userId, preQType, preQID, preStatus);
            } else if (Constants.GAME_LEVEL_TYPE_WU.equals(gameLevelType)) {
                gameLevel = gameLevelService.queryWuGameLevelInfo(preQID, userId);
            } else
                gameLevel = null;
            return gameLevel;
        } catch (Exception e) {
            logger.error("获取关卡信息失败:", e);
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

    /**
     * description: 提交经验及道具信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/24 23:22 <br>
     * author: zhengzhiqiang <br>
     *
     * @param player
     * @param itemPlayerRelations
     * @return void
     */
    @PostMapping(value = "submitGame")
    @ResponseBody
    public void submitGame(Player player, List<ItemPlayerRelation> itemPlayerRelations) {
        try {
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
        } catch (Exception e) {
            logger.error("提交游戏数据失败：{}", e);
        }
    }

    /**
     * description: 升级经验 <br>
     * version: 1.0 <br>
     * date: 2020/3/24 23:01 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId 玩家id
     * @param expType  升级类型 文关 or 武关
     * @param exp      升级经验值
     * @param addExp   新获取的经验值
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
     */
    @PostMapping("splitExp")
    @ResponseBody
    public Map<String, Object> splitExp(String playerId, String expType, String exp, String addExp) {
        logger.info("开始升级，方法入参：玩家id：{}，升级类型：{}，升级经验值：{}，新增经验值：{}", playerId, expType, exp, addExp);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            // 根据玩家id查询对应玩家信息
            Player player = playerService.selectByPlayerId(playerId);
            // 获取当前玩家的总经验值
            String experience = player.getExperience();
            Long totalExp = Long.valueOf(experience);
            if (!StringUtils.isEmpty(addExp)) {
                totalExp = Long.valueOf(experience) + Long.valueOf(addExp);
            }
            // 判断当前经验值是否满足升级需要
            if (totalExp < Long.valueOf(exp)) {
                resultMap.put("errCode", "000100");
                resultMap.put("errMsg", "玩家经验不足");
                return resultMap;
            }
            // 若满足升级需要，则根据升级类型判断将经验值分去哪个经验
            String targetExp = "";
            if ("wen".equals(expType)) {
                logger.info("获取文关经验");
                targetExp = player.getWenExperience();
            } else {
                logger.info("获取武关经验");
                targetExp = player.getWuExperience();
            }
            logger.info("获取最终经验值：{}", targetExp);
            Long finalPlayerExp = totalExp - Long.valueOf(exp);
            player.setExperience(String.valueOf(finalPlayerExp));
            Long finalTargetExp = Long.valueOf(targetExp) + Long.valueOf(exp);
            if ("wen".equals(expType)) {
                logger.info("更新文关经验");
                player.setWenExperience(String.valueOf(finalTargetExp));
            } else {
                logger.info("更新武关经验");
                player.setWuExperience(String.valueOf(finalTargetExp));
            }
            // 更新用户信息
            playerService.updateByIdSelective(player);
            resultMap.put("errCode", "000000");
            resultMap.put("errMsg", "升级成功");
            resultMap.put("newExp", String.valueOf(finalTargetExp));
            return resultMap;
        } catch (Exception e) {
           logger.error("升级错误：{}", e);
           resultMap.put("errCode", "999999");
           resultMap.put("errMsg", "升级错误");
           return resultMap;
        }
    }


    /**
     * description: 兑换称号 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 23:39 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return void
     */
    @PostMapping("submitDesignation")
    @ResponseBody
    public Map<String, Object> submitDesignation(String playerId, String levelType) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            logger.info("开始兑换称号操作，玩家id：{}，兑换类型：{}", playerId, levelType);
            Player player = playerService.selectByPlayerId(playerId);
            String playerLevel = "";
            String experience = "";
            if ("wen".equals(levelType)) {
                playerLevel = player.getPlayerLevel();
                experience = player.getWenExperience();
                logger.info("兑换文关称号，玩家当前文关等级：{}，玩家当前文关经验：{}", playerLevel, experience);
            } else {
                playerLevel = player.getWuLevel();
                experience = player.getWuExperience();
                logger.info("兑换武关称号，玩家当前武关等级：{}，玩家当前武关经验：{}", playerLevel, experience);
            }
            Integer currentExpInt = Integer.valueOf(experience);
            Integer currentLevelInt = Integer.valueOf(playerLevel);
            if (currentLevelInt < 10) {
                int nextLevel = currentLevelInt + 1;
                logger.info("玩家下一级别为{}", nextLevel);
                LevelUpConfig levelUpConfig = levelUpService.selectByLevel(String.valueOf(nextLevel));
                logger.info("获取下一等级配置信息：{}", levelUpConfig.toString());
                String levelUpExpStr = levelUpConfig.getExp();
                logger.info("兑换称号所需经验：{}", levelUpExpStr);
                Integer levelUpExpInt = Integer.valueOf(levelUpExpStr);
                if (currentExpInt <= levelUpExpInt) {
                    logger.info("玩家现有经验不足，兑换失败");
                    resultMap.put("resCode", "000001");
                    resultMap.put("resMsg", "称号兑换失败，当前玩家经验不足");
                    return resultMap;
                }
                // 开始更新玩家数据，获取剩余经验
                int surplusExpInt = currentLevelInt - currentExpInt;
                logger.info("玩家兑换称号后，剩余经验为：{}", surplusExpInt);
                if ("wen".equals(levelType)) {
                    // 更新文关经验及文关称号
                    logger.info("开始更新玩家文关经验及文关称号");
                    player.setWenExperience(String.valueOf(surplusExpInt));
                    player.setPlayerLevel(String.valueOf(nextLevel));
                } else {
                    logger.info("开始更新玩家武关经验及武关称号");
                    player.setWuExperience(String.valueOf(surplusExpInt));
                    player.setWuLevel(String.valueOf(nextLevel));
                }
                playerService.updateByIdSelective(player);
                player = playerService.selectByPlayerId(playerId);
                logger.info("兑换完成，玩家信息：{}", player.toString());
                resultMap.put("resCode", "000000");
                resultMap.put("resMsg", "称号兑换成功");
                resultMap.put("player", player);
                resultMap.put("levelUp", levelUpConfig);
                return resultMap;
            } else {
                logger.info("玩家已经是当前最高等级");
                resultMap.put("resCode", "000010");
                resultMap.put("resMsg", "当前已是最高称号");
                return resultMap;
            }
        } catch (Exception e) {
            logger.error("兑换称号失败：{}", e);
            resultMap.put("resCode", "000010");
            resultMap.put("resMsg", "当前已是最高称号");
            return resultMap;
        }
    }
}
