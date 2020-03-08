package siyi.game.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import siyi.game.dao.entity.*;
import siyi.game.service.game.GameService;
import siyi.game.service.gamelevel.LevelUpService;
import siyi.game.service.item.ItemConfigService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.loginLog.LoginLogService;
import siyi.game.service.player.PlayerService;
import siyi.game.utill.RandomUtil;
import siyi.game.utill.UUIDUtil;

import java.util.*;

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

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;

    @Autowired
    private ItemConfigService itemConfigService;

    @Autowired
    private LevelUpService levelUpService;

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody Player player) {
        Map<String, Object> resultMap = new HashMap<>();
        Player findPlayer = playerService.selectByPlatFormId(player.getPlatformId());
        if (findPlayer == null) {
            // 若没有玩家信息，则新增玩家
            // 生成玩家编号
            String playerId = RandomUtil.generate16();
            player.setPlayerId(playerId);
            playerService.insertSelective(player);
        }
        // 查询最新玩家信息
        findPlayer = playerService.selectByPlatFormId(player.getPlatformId());
        // 设置登录ID，通过该值判断是哪一次登录
        String uuid32 = UUIDUtil.getUUID32();
        // 查询玩家道具信息
        List<ItemPlayerRelation> relations = itemPlayerRelationService.selectByPlayerIdAndGameCode(player.getPlayerId(), player.getGameCode());
        List<String> itemNoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(relations)) {
            for (ItemPlayerRelation relation : relations) {
                itemNoList.add(relation.getItemNo());
            }
        }
        List<ItemConfig> itemConfigs = itemConfigService.selectByItemNoList(itemNoList);
        // 查询玩家称号信息
        String playerLevel = player.getPlayerLevel();
        LevelUpConfig levelUpConfig = levelUpService.selectByLevel(playerLevel);

        // 插入玩家登录数据
        LoginLog loginLog = new LoginLog();
        loginLog.setGameCode(player.getGameCode());
        loginLog.setOpenId(uuid32);
        loginLog.setPlayerId(findPlayer.getPlayerId());
        loginLog.setLoginTime(new Date());
        loginLogService.insertSelective(loginLog);
        getSuccessResult(resultMap);
        // 返回数据组装
        resultMap.put("player", findPlayer);
        resultMap.put("openId", uuid32);
        resultMap.put("itemList", itemConfigs);
        resultMap.put("level", levelUpConfig);

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
