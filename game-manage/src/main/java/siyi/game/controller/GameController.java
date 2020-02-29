package siyi.game.controller;

import com.github.pagehelper.PageInfo;
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

    @GetMapping("getAll")
    public Map<String, Object> getGamePageList(String gameName, int pageNum, int pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        Game game = new Game();
        game.setGameName(gameName);
        List<Game> gameList = gameService.selectGamePageList(game, pageNum, pageSize);
        PageInfo<Game> page = new PageInfo<>(gameList);
        getSuccessResult(resultMap);
        resultMap.put("page", page);
        return resultMap;
    }

    @GetMapping("changeGameStatus")
    public Map<String, Object> changeGameStatus(String id, String status) {
        Map<String, Object> resultMap = new HashMap<>();
        Game game = new Game();
        game.setId(Long.valueOf(id));
        game.setIsOnSale(status);
        int i = gameService.updateByIdSelective(game);
        if (i <= 0) {
            getFailResult(resultMap, "游戏状态更新失败");
            return resultMap;
        }
        getSuccessResult(resultMap);
        return resultMap;
    }

}
