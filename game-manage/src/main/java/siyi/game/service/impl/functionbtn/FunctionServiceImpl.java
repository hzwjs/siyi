package siyi.game.service.impl.functionbtn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.PlayerSignMapper;
import siyi.game.dao.entity.*;
import siyi.game.service.fuctionbtn.FunctionService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.utill.RandomUtil;

import java.util.*;


@Service
public class FunctionServiceImpl implements FunctionService {

    private final Logger log = LoggerFactory.getLogger(FunctionServiceImpl.class);

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private ItemConfigMapper itemConfigMapper;
    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;
    @Autowired
    private PlayerSignMapper playerSignMapper;

    @Override
    public Map getLotteryInfo(String playerId, boolean flag) {
        Map result = new HashMap();
        Player player = new Player();
        player.setPlayerId(playerId);
        player = playerMapper.selectOne(player);
        if (player != null) {
            result.put("num", player.getLotteryNum());
            result.put("items", getItemByRandom(8));
            if (flag) {
                player.setLotteryNum(player.getLotteryNum() - 1);
                playerMapper.updateByPrimaryKey(player);
            }
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public boolean saveLotteryInfo(String playerId, String itemId, int num, String gameCode) {
        boolean flag = false;
        try {
            // 获取道具关联信息，判断是否已有该道具
            ItemPlayerRelation selectParam = new ItemPlayerRelation();
            selectParam.setPlayerId(playerId);
            selectParam.setItemNo(itemId);
            selectParam.setGameCode(gameCode);
            ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectParam);
            if (itemPlayerRelation != null) {
                log.info("玩家已有该道具，开始更新道具记录");
                String oldQuantity = itemPlayerRelation.getQuantity();
                Long newQuantity = Long.valueOf(oldQuantity) + num;
                itemPlayerRelation.setQuantity(String.valueOf(newQuantity));
                itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
                flag = true;
            } else {
                log.info("玩家新增道具信息，开始新增道具记录");
                itemPlayerRelationService.insertSelective(itemPlayerRelation);
            }
        } catch (Exception e) {
            log.error("=== 保存抽奖道具失败：{}===", e);
        }
        return flag;
    }

    @Override
    public PlayerSign querySignInfo(String playerId) {
        PlayerSign playerSign = new PlayerSign();
        playerSign.setPlayerId(playerId);
        playerSign = playerSignMapper.selectOne(playerSign);
        if (playerSign == null) {
            // 第一次签到的时候是没有记录的需要手动插入一条
            PlayerSign firstSign = new PlayerSign();
            firstSign.setPlayerId(playerId);
            firstSign.setStartDate(new Date());
            log.info("=== 第一次签到 ===");
        }
        return playerSign;
    }

    @Override
    public boolean submitSignInfo(String playerId, String signDays) {

        return false;
    }

    /**
     * 随机获取指定个数的道具
     * @param num
     * @return
     */
    private List<String> getItemByRandom(int num) {
        List<String> resultList = new ArrayList<>();
        ItemConfig itemConfig = new ItemConfig();
        itemConfig.setOnoff("0"); // 0-启用
        List<ItemConfig> list = itemConfigMapper.select(itemConfig);
        int len = list.size();
        for (int i = 0; i < num; i++) {
            int index = RandomUtil.getRandomNumInTwoIntNum(0, len);
            resultList.add(list.get(index).getId());
        }
        return resultList;
    }
}
