package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.entity.Game;
import siyi.game.service.game.GameService;
import siyi.game.service.player.PlayerService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: ReportController <br>
 * date: 2020/2/27 22:23 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("report")
public class ReportController extends BaseController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @GetMapping("getAreaInfo")
    public Map<String, Object> getAreaInfo(String gameCode) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> areaInfo = playerService.selectAreaReportByGameCode(gameCode);
        getSuccessResult(resultMap);
        resultMap.put("areaInfo", areaInfo);
        Game game = gameService.selectGameByGameCode(gameCode);
        resultMap.put("gameName", game.getGameName());
        resultMap.put("gameCode", gameCode);
        long playerNum = playerService.selectCountByGameCode(gameCode);
        resultMap.put("playerNum", playerNum);
        return resultMap;
    }
}
