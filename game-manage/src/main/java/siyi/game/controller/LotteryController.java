package siyi.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.dao.entity.ItemConfig;
import siyi.game.dao.entity.ItemPlayerRelation;
import siyi.game.dao.entity.Player;
import siyi.game.service.item.ItemConfigService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.player.PlayerService;

import java.util.*;

/**
 * description: LotteryController 抽奖 <br>
 * date: 2020/3/23 22:32 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "idiom/lottery")
public class LotteryController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(LotteryController.class);
    @Autowired
    private ItemConfigService itemConfigService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;

    /**
     * description: 获取抽奖列表 <br>
     * version: 1.0 <br>
     * date: 2020/3/23 23:27 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.List<siyi.game.dao.entity.ItemConfig>
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    @ResponseBody
    public List<ItemConfig> getLotteryList() {
        List<ItemConfig> resourceList = itemConfigService.selectAll();
        List<ItemConfig> targetList = new ArrayList<>();
        // 如果获取数据条数大于等于源集合，则将源集合返回
        if (8 >= resourceList.size()) {
            return resourceList;
        }
        //随机取出8条不重复的数据
        for (int i = 8; i >= 1; i--) {
            Random random = new Random();
            //在数组大小之间产生一个随机数 j
            int j = random.nextInt(resourceList.size());
            //取得resourceList 中下标为j 的数据存储到 targetList 中
            targetList.add(resourceList.get(j));
            //把已取到的数据移除,避免下次再次取到出现重复
            resourceList.remove(j);
        }
        return targetList;
    }

    /**
     * description: 玩家提交抽奖信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/23 23:27 <br>
     * author: zhengzhiqiang <br>
     *
     * @param itemId 道具id
     * @param quantity 道具数量
     * @param playerId 玩家id
     * @param gameCode 游戏编码
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLottery(String itemId, String quantity, String playerId, String gameCode) {
        Map<String, Object> resultMap = new HashMap<>();
        logger.info("提交玩家抽奖记录，方法入参：itemId：{}，quantity：{}，playerId：{}，gameCode：{}", itemId, quantity, playerId, gameCode);
        try {
            // 获取玩家信息，查询抽奖次数，若为零，则返回失败
            Player player = playerService.selectByPlayerId(playerId);
            Long lotteryNum = player.getLotteryNum();
            logger.info("当前玩家抽奖次数：{}", lotteryNum.toString());
            if (lotteryNum <= 0) {
                logger.info("玩家抽奖次数不足，抽奖失败");
                resultMap.put("errCode", "000002");
                resultMap.put("errMsg", "玩家抽奖次数不足");
                return resultMap;
            }
            // 获取道具关联信息，判断是否已有该道具
            ItemPlayerRelation selectParam = new ItemPlayerRelation();
            selectParam.setPlayerId(playerId);
            selectParam.setItemNo(itemId);
            selectParam.setGameCode(gameCode);
            ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectParam);
            if (itemPlayerRelation != null) {
                logger.info("玩家已有该道具，开始更新道具记录");
                String oldQuantity = itemPlayerRelation.getQuantity();
                Long newQuantity = Long.valueOf(oldQuantity) + Long.valueOf(quantity);
                itemPlayerRelation.setQuantity(String.valueOf(newQuantity));
                itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
            } else {
                logger.info("玩家新增道具信息，开始新增道具记录");
                itemPlayerRelationService.insertSelective(itemPlayerRelation);
            }
            resultMap.put("errCode", "000000");
            resultMap.put("errMsg", "保存成功");
            return resultMap;
        } catch (Exception e) {
            logger.error("保存抽奖信息失败：{}", e);
            getFailResult(resultMap, "保存失败");
            return resultMap;
        }
    }
}
