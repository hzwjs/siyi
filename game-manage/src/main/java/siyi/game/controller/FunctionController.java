package siyi.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.bo.functionbtn.AwardInfo;
import siyi.game.dao.entity.ItemPlayerRelation;
import siyi.game.dao.entity.Player;
import siyi.game.dao.entity.PlayerSign;
import siyi.game.service.fuctionbtn.FunctionService;

import java.util.HashMap;
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
        boolean flag = functionService.saveLotteryInfo(awardInfo.getPlayerId(), awardInfo.getItemId(), awardInfo.getNum(), awardInfo.getGameCode());
        if (flag) {
            resultMap.put("errCode", "000000");
            resultMap.put("errMsg", "保存成功");
        } else {
            getFailResult(resultMap, "保存失败");
        }
        return resultMap;
    }

    @RequestMapping(value = "querySignInfo")
    @ResponseBody
    public PlayerSign submitLottery(String playerId){
        PlayerSign playerSign = functionService.querySignInfo(playerId);
        return playerSign;
    }

}
