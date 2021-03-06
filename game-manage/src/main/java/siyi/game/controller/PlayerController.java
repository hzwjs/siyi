package siyi.game.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import siyi.game.bo.PlayerBo;
import siyi.game.bo.WxLoginResponse;
import siyi.game.bo.functionbtn.ItemBo;
import siyi.game.dao.PhysicalPowerMapper;
import siyi.game.dao.PlayerWxInfoMapper;
import siyi.game.dao.entity.*;
import siyi.game.framework.annotation.WebLog;
import siyi.game.service.fuctionbtn.FunctionService;
import siyi.game.service.game.GameService;
import siyi.game.service.gamelevel.LevelClearRecordService;
import siyi.game.service.gamelevel.LevelUpService;
import siyi.game.service.item.ItemConfigService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.loginLog.LoginLogService;
import siyi.game.service.mission.PlayerMessionRelationService;
import siyi.game.service.player.PhysicalPowerService;
import siyi.game.service.player.PlayerService;
import siyi.game.utill.Constants;
import siyi.game.utill.RandomUtil;
import siyi.game.utill.UUIDUtil;

import java.util.*;

/**
 * description: PlayerController <br>
 * date: 2020/2/26 22:09 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 *
 * @author hzw
 * @version 1.0
 * @date 2020 -06-01
 */
@RestController
@RequestMapping("idiom/player")
public class PlayerController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);
    @Value("${appid}")
    private String appid;
    @Value("${secret}")
    private String secret;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PlayerWxInfoMapper playerWxInfoMapper;
    @Autowired
    private PlayerMessionRelationService playerMessionRelationService;
    @Autowired
    private PhysicalPowerMapper physicalPowerMapper;
    @Autowired
    private PhysicalPowerService physicalPowerService;

    /**
     * Login map.
     *
     * @param playerBo the player bo
     * @return the map
     */
    @RequestMapping("login")
    @WebLog(description = "玩家登录")
    public Map<String, Object> login(@RequestBody PlayerBo playerBo) {
        Map<String, Object> resultMap = new HashMap<>();
        PhysicalPower physicalPower = new PhysicalPower(); // 玩家体力信息
        List<ItemBo> itemConfigs = null; // 道具信息
        List<PlayerMessionRelation> messionList = null; // 任务信息
        // 微信登录
        String jsCode = playerBo.getWxCode();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" +secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String responseStr = restTemplate.getForObject(url, String.class);
        logger.info("=== 微信登录的返回信息:{} ===", responseStr);
        WxLoginResponse response = JSONObject.parseObject(responseStr, WxLoginResponse.class);
        String openId = response.getOpenid();
        // 设置登录ID，通过该值判断是哪一次登录
        String uuid32 = UUIDUtil.getUUID32();
        if (!StringUtils.isEmpty(openId)) {
            Player player = playerService.selectByPlatFormId(openId);
            if (player == null) {
                logger.info("该玩家为新玩家");
                // 若没有玩家信息，则新增玩家
                player = new Player();
                String playerId = RandomUtil.generate16();
                player.setPlayerId(playerId);
                player.setPlatformId(response.getOpenid());
                player.setGameLevel("1");
                player.setGameCode(playerBo.getGameCode());
                playerService.insertSelective(player);
                // 初始化玩家体力
                physicalPower.setPlayerId(playerId);
                physicalPower.setHp(5);
                physicalPower.setMaxHp(5);
                physicalPower.setPlayerLevel("1");
                physicalPower.setCreatedTime(new Date());
                physicalPower.setUpdatedTime(new Date());
                physicalPowerMapper.insert(physicalPower);
            } else {
                logger.info("获取登录玩家信息：{}", player);
                // 查询玩家道具信息
                itemConfigs = functionService.queryItemInPacksack(player.getPlayerId(), Constants.GAME_CODE_WENWU);
                resultMap.put("itemList", itemConfigs);
                // 查询玩家任务信息
//                messionList = playerMessionRelationService.selectByPlayerId(player.getPlayerId());
//                if (CollectionUtils.isEmpty(messionList)) {
//                    logger.info("玩家无任务信息，开始生成任务");
//                    messionList = playerMessionRelationService.createNewMession(player.getPlayerId(), null);
//                }
//                resultMap.put("messionList", messionList);
                // 获取玩家体力信息
                physicalPower.setPlayerId(player.getPlayerId());
                physicalPower = physicalPowerMapper.selectOne(physicalPower);
            }
            // 插入玩家登录数据
            LoginLog loginLog = new LoginLog();
            loginLog.setGameCode(player.getGameCode());
            loginLog.setOpenId(uuid32);
            loginLog.setPlayerId(player.getPlayerId());
            loginLog.setLoginTime(new Date());
            loginLogService.insertSelective(loginLog);
            // 返回数据组装
            BeanCopier copier = BeanCopier.create(Player.class, PlayerBo.class, false);
            copier.copy(player, playerBo, null);
            if (physicalPower != null) {
                playerBo.setHp(physicalPower.getHp());
                playerBo.setMaxHp(physicalPower.getMaxHp());
                playerBo.setStartTimeRecover(physicalPower.getUpdatedTime());
            }
            resultMap.put("player", playerBo);
            resultMap.put("openId", uuid32);
            resultMap.put("session_key", response.getSession_key());
        } else {
            logger.info("=== 登录失败 ===");
            resultMap.put("errCode", response.getErrcode());
            resultMap.put("errMsg", response.getErrmsg());
            return resultMap;
        }
        // 返回成功响应
        getSuccessResult(resultMap);
        logger.info("=== 登录返回数据:{} ===", JSON.toJSONString(resultMap));
        return resultMap;

    }

    /**
     * description: 授权 <br>
     * version: 1.0 <br>
     * date: 2020/3/26 23:30 <br>
     * author: zhengzhiqiang <br>
     *
     * @param wxInfo the wx info
     * @return java.util.Map<java.lang.String, java.lang.Object> map
     */
    @PostMapping("authorize")
    public Map<String, Object> Authorize(@RequestBody PlayerWxInfo wxInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        PlayerWxInfo playerWxInfo = playerWxInfoMapper.selectOne(wxInfo);
        if (playerWxInfo == null) {
            playerWxInfoMapper.insert(wxInfo);
        } else {
            playerWxInfoMapper.updateByPrimaryKey(wxInfo);
        }
        getSuccessResult(resultMap);
        return resultMap;
    }


    /**
     * Log out map.
     *
     * @param playerId the player id
     * @param openId   the open id
     * @return the map
     */
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
     * @param playerName the player name
     * @param playerId   the player id
     * @param gameCode   the game code
     * @param pageNum    the page num
     * @param pageSize   the page size
     * @return java.util.Map<java.lang.String, java.lang.Object> player list
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

    /**
     * Query player info .
     * 查询玩家信息
     *
     * @param playerId the player id
     * @return the playerbo
     */
    @RequestMapping("/queryPlayerInfo")
    public PlayerBo queryPlayerInfo(String playerId) {
        Player player = playerService.selectByPlayerId(playerId);
        PlayerBo playerBo = new PlayerBo();
        // 基本信息：等级、经验、金币等
        BeanCopier copier = BeanCopier.create(Player.class, PlayerBo.class, false);
        copier.copy(player, playerBo, null);
        // 体力信息
        playerBo.setHp(physicalPowerService.calculateHp(playerId));
        return playerBo;
    }
}
