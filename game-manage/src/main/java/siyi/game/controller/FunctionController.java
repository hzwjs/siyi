package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.entity.Player;

/**
 * 功能按钮对应的接口
 */
@Controller
@RequestMapping("/function")
public class FunctionController {

    @Autowired
    private PlayerMapper playerMapper;
    /**
     * 获取当前的可抽奖次数
     * @param playerId 玩家ID
     * @return
     */
    @RequestMapping("/award")
    @ResponseBody
    public Long getAwardNum(String playerId) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player = playerMapper.selectOne(player);
        return player.getLotteryNum();
    }
}
