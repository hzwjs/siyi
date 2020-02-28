package siyi.game.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.entity.Game;
import siyi.game.dao.model.LoginLogInfo;
import siyi.game.service.game.GameService;
import siyi.game.service.loginLog.LoginLogService;
import siyi.game.utill.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: LoginLogController <br>
 * date: 2020/2/28 22:00 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("loginLog")
public class LoginLogController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private GameService gameService;

    @GetMapping("getAll")
    public Map<String, Object> getLoginLogPageInfo(@RequestParam(name = "playerId", required = false, defaultValue = "") String playerId,
                                                   @RequestParam(name = "playerName", required = false, defaultValue = "") String playerName,
                                                   @RequestParam(name = "gameCode", required = false, defaultValue = "") String gameCode,
                                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Map<String, Object> resultMap = new HashMap<>();
        List<LoginLogInfo> playerInfoList = loginLogService.getLoginLogPageInfo(playerId, playerName, gameCode, pageNum, pageSize);
        if (!CollectionUtils.isEmpty(playerInfoList)) {
            for (LoginLogInfo loginLogInfo : playerInfoList) {
                Date loginTime = loginLogInfo.getLoginTime();
                Date logoutTime = loginLogInfo.getLogoutTime();
                String timeDiff = DateUtil.getTimeDiff(loginTime, logoutTime);
                loginLogInfo.setLoginDurationStr(timeDiff);
            }
        }
        PageInfo<LoginLogInfo> pageInfo = new PageInfo<>(playerInfoList);
        getSuccessResult(resultMap);
        resultMap.put("page", pageInfo);
        List<Game> gameList = gameService.selectAllGame();
        resultMap.put("gameList", gameList);
        return resultMap;
    }
}
