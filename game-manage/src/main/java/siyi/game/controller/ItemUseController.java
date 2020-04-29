package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.entity.ItemPlayerRelation;
import siyi.game.dao.entity.Player;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.player.PlayerService;

import java.util.HashMap;
import java.util.Map;

/**
 * 道具使用方法
 */
@RestController
@RequestMapping("idiom/itemuse")
public class ItemUseController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;

    /**
     * 使用跳关卡道具
     *
     * @param itemId 道具id
     * @param playerId 玩家id
     * @param addNum 跳关数
     * @return
     */
    @GetMapping("useSkipLevel")
    public Map<String, Object> useSkipLevel(String itemId, String playerId, String addNum) {
        Map<String, Object> resultMap = new HashMap<>();
        // 玩家道具数量减一
        ItemPlayerRelation selectRelation = new ItemPlayerRelation();
        selectRelation.setPlayerId(playerId);
        selectRelation.setItemNo(itemId);
        ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectRelation);
        String quantity = itemPlayerRelation.getQuantity();
        if (Integer.parseInt(quantity) <= 0) {
            resultMap.put("errCode", "000011");
            resultMap.put("errMsg", "玩家道具数量不足");
            return resultMap;
        }
        int finalQuantity = Integer.parseInt(quantity) - 1;
        itemPlayerRelation.setQuantity(String.valueOf(finalQuantity));
        itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
        // 更新玩家游戏层数
        Player player = playerService.selectByPlayerId(playerId);
        player.setGameLevel(addNum);
        playerService.updateByIdSelective(player);
        // 返回结果
        resultMap.put("errCode", "000000");
        resultMap.put("errMsg", "使用道具成功");
        return resultMap;
    }

    /**
     * 使用金币卡道具
     *
     * @param itemId 道具id
     * @param playerId 玩家id
     * @param addNum 新增金币数量
     * @return
     */
    @GetMapping("userAddGold")
    public Map<String, Object> useAddGold(String itemId, String playerId, String addNum) {
        Map<String, Object> resultMap = new HashMap<>();
        // 玩家道具数量减一
        ItemPlayerRelation selectRelation = new ItemPlayerRelation();
        selectRelation.setPlayerId(playerId);
        selectRelation.setItemNo(itemId);
        ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectRelation);
        String quantity = itemPlayerRelation.getQuantity();
        if (Integer.parseInt(quantity) <= 0) {
            resultMap.put("errCode", "000011");
            resultMap.put("errMsg", "玩家道具数量不足");
            return resultMap;
        }
        int finalQuantity = Integer.parseInt(quantity) - 1;
        itemPlayerRelation.setQuantity(String.valueOf(finalQuantity));
        itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
        // 将道具的金币数累加至玩家处
        Player player = playerService.selectByPlayerId(playerId);
        String gold = player.getGold();
        int finalGoldInt = Integer.parseInt(gold) + Integer.parseInt(addNum);
        player.setGold(String.valueOf(finalGoldInt));
        playerService.updateByIdSelective(player);
        // 返回结果
        resultMap.put("errCode", "000000");
        resultMap.put("errMsg", "使用道具成功");
        return resultMap;
    }

    /**
     * 使用经验卡道具
     *
     * @param itemId 道具id
     * @param playerId 玩家id
     * @param addNum 增加经验值
     * @return
     */
    @GetMapping("useAddExp")
    public Map<String, Object> useAddExp(String itemId, String playerId, String addNum) {
        Map<String, Object> resultMap = new HashMap<>();
        // 玩家道具数量减一
        ItemPlayerRelation selectRelation = new ItemPlayerRelation();
        selectRelation.setPlayerId(playerId);
        selectRelation.setItemNo(itemId);
        ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectRelation);
        String quantity = itemPlayerRelation.getQuantity();
        if (Integer.parseInt(quantity) <= 0) {
            resultMap.put("errCode", "000011");
            resultMap.put("errMsg", "玩家道具数量不足");
            return resultMap;
        }
        int finalQuantity = Integer.parseInt(quantity) - 1;
        itemPlayerRelation.setQuantity(String.valueOf(finalQuantity));
        itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
        // 道具经验累加至玩家经验处
        Player player = playerService.selectByPlayerId(playerId);
        String experience = player.getExperience();
        int finalExp = Integer.parseInt(experience) + Integer.parseInt(addNum);
        player.setExperience(String.valueOf(finalExp));
        playerService.updateByIdSelective(player);
        // 返回结果
        resultMap.put("errCode", "000000");
        resultMap.put("errMsg", "使用道具成功");
        return resultMap;
    }
}
