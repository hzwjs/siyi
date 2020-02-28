package siyi.game.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siyi.game.dao.entity.Game;
import siyi.game.dao.entity.Player;
import siyi.game.service.game.GameService;
import siyi.game.service.player.PlayerService;
import siyi.game.utill.UUIDUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: PlayerController <br>
 * date: 2020/2/26 22:09 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/player")
public class PlayerController extends BaseController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody Player player) {
        Map<String, Object> resultMap = new HashMap<>();
        Player findPlayer = playerService.selectByPlayerId(player.getPlayerId());
        if (findPlayer == null) {
            playerService.insertSelective(player);
        }
        findPlayer = playerService.selectByPlayerId(player.getPlayerId());
        getSuccessResult(resultMap);
        String uuid32 = UUIDUtil.getUUID32();
        resultMap.put("player", findPlayer);
        resultMap.put("openId", uuid32);
        // TODO 插入玩家登录数据
        return resultMap;
    }

    @GetMapping("/logOut")
    public Map<String, Object> logOut(String playerId, String openId) {
        // TODO 更新玩家登录数据，计算登录时长
        return new HashMap<>();
    }

    /**
     * description: 分页查询玩家信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/26 23:15 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerName
     * @param playerId
     * @param gameCode
     * @param pageNum
     * @param pageSize
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @GetMapping("/getAll")
    public Map<String, Object> getPlayerList(@RequestParam(name = "playerName", required = false, defaultValue = "") String playerName,
                                             @RequestParam(name = "playerId", required = false, defaultValue = "") String playerId,
                                             @RequestParam(name = "gameCode", required = false, defaultValue = "") String gameCode,
                                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        Player player = new Player();
        player.setPlayerName(playerName);
        player.setPlayerId(playerId);
        player.setGameCode(gameCode);
        List<Player> playerList = playerService.selectPlayerPageInfo(player, pageNum, pageSize);
        PageInfo<Player> page = new PageInfo<>(playerList);
        getSuccessResult(resultMap);
        resultMap.put("page", page);
        List<Game> gameList = gameService.selectAllGame();
        resultMap.put("gameList", gameList);
        return resultMap;
    }
}
