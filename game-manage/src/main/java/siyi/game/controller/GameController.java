package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.entity.Game;
import siyi.game.service.game.GameService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: GameController <br>
 * date: 2020/2/27 0:11 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("game")
public class GameController extends BaseController {
    @Autowired
    private GameService gameService;

    @GetMapping("getSelect")
    public Map<String, Object> getGameSelect() {
        List<Game> gameList = gameService.selectAllGame();
        Map<String, Object> resultMap = new HashMap<>();
        getSuccessResult(resultMap);
        resultMap.put("gameList", gameList);
        return resultMap;
    }

}
