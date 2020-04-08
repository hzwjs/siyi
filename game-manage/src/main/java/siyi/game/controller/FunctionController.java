package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.service.fuctionbtn.FunctionService;

import java.util.Map;

/**
 * 功能按钮对应的接口
 */
@Controller
@RequestMapping("function")
public class FunctionController {

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
}
