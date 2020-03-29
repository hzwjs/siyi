package siyi.game.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import siyi.game.bo.PlayerBo;
import siyi.game.bo.WxLoginResponse;
import siyi.game.dao.PlayerWxInfoMapper;
import siyi.game.dao.entity.*;
import siyi.game.service.game.GameService;
import siyi.game.service.gamelevel.LevelClearRecordService;
import siyi.game.service.gamelevel.LevelUpService;
import siyi.game.service.item.ItemConfigService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.loginLog.LoginLogService;
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
 */
@RestController
@RequestMapping("idiom/player")
public class PlayerController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);
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

    @Autowired
    private LevelClearRecordService levelClearRecordService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PlayerWxInfoMapper playerWxInfoMapper;

    @RequestMapping("login")
    public Map<String, Object> login(@RequestBody PlayerBo playerBo) {
        logger.info("开始玩家登录，登录玩家：{}", JSON.toJSONString(playerBo));
        Map<String, Object> resultMap = new HashMap<>();
        // 微信登录
        String jsCode = playerBo.getWxCode();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxca19bfbb81a34a47&secret=9702b32d9a5df96bee6ab8f0df4c9fc7&js_code=" + jsCode + "&grant_type=authorization_code";
        String requestStr = restTemplate.getForObject(url, String.class);
        logger.info("=== 微信登录的返回信息:{} ===", requestStr);
        WxLoginResponse response = JSONObject.parseObject(requestStr, WxLoginResponse.class);
        String openId = response.getOpenid();
        // 设置登录ID，通过该值判断是哪一次登录
        String uuid32 = UUIDUtil.getUUID32();
        if (!StringUtils.isEmpty(openId)) {
            Player player = playerService.selectByPlatFormId(openId);
            if (player == null) {
                logger.info("该玩家为新玩家");
                // 若没有玩家信息，则新增玩家
                Player loginPlayer = new Player();
                // 生成玩家编号
                String playerId = RandomUtil.generate16();
                loginPlayer.setPlayerId(playerId);
                loginPlayer.setPlatformId(response.getOpenid());
                playerService.insertSelective(loginPlayer);
            } else {
                logger.info("获取登录玩家信息：{}", player);
                // 查询玩家道具信息
                List<ItemPlayerRelation> relations = itemPlayerRelationService.selectByPlayerIdAndGameCode(player.getPlayerId(), player.getGameCode());
                List<String> itemNoList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(relations)) {
                    for (ItemPlayerRelation relation : relations) {
                        itemNoList.add(relation.getItemNo());
                    }
                }
                List<ItemConfig> itemConfigs = new ArrayList<>();
                if (itemNoList.size() != 0) {
                    itemConfigs = itemConfigService.selectByItemNoList(itemNoList);
                }
                // 查询玩家称号信息
                String playerLevel = player.getPlayerLevel();
                LevelUpConfig levelUpConfig = levelUpService.selectByLevel(playerLevel);

                // 查询玩家挑战记录信息
                LevelClearRecord selectParam = new LevelClearRecord();
                selectParam.setPlayerId(player.getPlayerId());
                selectParam.setGameCode(player.getGameCode());
                LevelClearRecord levelClearRecord = levelClearRecordService.selectByBean(selectParam);
                // 返回数据组装
                resultMap.put("player", player);
                resultMap.put("openId", uuid32);
                resultMap.put("itemList", itemConfigs);
                resultMap.put("level", levelUpConfig);
                resultMap.put("levelRecord", levelClearRecord);
            }
            // 插入玩家登录数据
            LoginLog loginLog = new LoginLog();
            loginLog.setGameCode(player.getGameCode());
            loginLog.setOpenId(uuid32);
            loginLog.setPlayerId(player.getPlayerId());
            loginLog.setLoginTime(new Date());
            loginLogService.insertSelective(loginLog);
        } else {
            logger.info("=== 登录失败 ===");
            resultMap.put("errCode", response.getErrcode());
            resultMap.put("errMsg", response.getErrmsg());
            return resultMap;
        }
        // 返回成功响应
        getSuccessResult(resultMap);
        return resultMap;

    }

    /**
     * description: 授权 <br>
     * version: 1.0 <br>
     * date: 2020/3/26 23:30 <br>
     * author: zhengzhiqiang <br>
     *
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
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
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
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
