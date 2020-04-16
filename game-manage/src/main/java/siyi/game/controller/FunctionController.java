package siyi.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.bo.functionbtn.AwardInfo;
import siyi.game.bo.functionbtn.ItemBo;
import siyi.game.dao.entity.LevelClearRecord;
import siyi.game.dao.entity.PlayerSign;
import siyi.game.service.fuctionbtn.FunctionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能按钮对应的接口
 */
@Controller
@RequestMapping("idiom/function")
public class FunctionController extends BaseController{

    private final Logger log = LoggerFactory.getLogger(FunctionController.class);

    @Autowired
    private FunctionService functionService;


    /**
     * 查询天梯信息：最高层数和挑战次数
     * @param playerId 玩家ID
     * @return
     */
    @RequestMapping("tianti")
    @ResponseBody
    public LevelClearRecord queryTiantiInfo(String playerId) {
        LevelClearRecord result = functionService.getTiantiInfo(playerId);
        return result;
    }

    /**
     * 获取当前的可抽奖次数
     * @param playerId 玩家ID
     * @param flag     是否更新抽奖次数的标志
     * @return
     */
    @RequestMapping("lottery")
    @ResponseBody
    public Map getlotteryInfo(String playerId, boolean flag) {
        Map result = functionService.getLotteryInfo(playerId,flag);
        return result;
    }

    /**
     * 提交获取的道具
     * @param awardInfo
     * @return
     */
    @RequestMapping(value = "itemSubmit")
    @ResponseBody
    public Map<String, Object> submitLottery(AwardInfo awardInfo) {
        log.info("玩家获取道具保存，方法入参：itemId：{}，num：{}，playerId：{}，gameCode：{}", awardInfo.getItemId(), awardInfo.getNum(), awardInfo.getPlayerId(), awardInfo.getGameCode());
        Map<String, Object> resultMap = new HashMap<>();
        if (!StringUtils.isEmpty(awardInfo.getItemId()) && !StringUtils.isEmpty(awardInfo.getNum()) && !StringUtils.isEmpty(awardInfo.getPlayerId())){
            boolean flag = functionService.saveLotteryInfo(awardInfo.getPlayerId(), awardInfo.getItemId(), awardInfo.getNum(), awardInfo.getGameCode());
            if (flag) {
                resultMap.put("errCode", "000000");
                resultMap.put("errMsg", "保存成功");
            }
            getFailResult(resultMap, "保存失败");
        } else {
            getFailResult(resultMap, "缺少必输参数");
        }

        return resultMap;
    }


    /**
     * 查询背包中的道具
     * @param playerId
     * @param gameCode
     * @return
     */
    @RequestMapping(value = "queryItemInPack")
    @ResponseBody
    public List<ItemBo> queryItemInPack(String playerId, String gameCode) {
        log.info("=== 背包查询参数 playerId:{} gameCode:{}", playerId, gameCode);
        List<ItemBo> list = functionService.queryItemInPacksack(playerId, gameCode);
        return list;
    }

    /**
     * 查询签到信息，签到现在是由前端做的没有调用这个接口
     * @param playerId
     * @return
     */
    @RequestMapping(value = "querySignInfo")
    @ResponseBody
    public PlayerSign queryLottery(String playerId){
        PlayerSign playerSign = functionService.querySignInfo(playerId);
        return playerSign;
    }

}
