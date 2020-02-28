package siyi.game.service.loginLog;

import siyi.game.dao.model.LoginLogInfo;

import java.util.List;

/**
 * description: LoginLogService <br>
 * date: 2020/2/28 22:01 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface LoginLogService {

    /**
     * description: 分页查询玩家登录信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 22:10 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @param playerName
     * @param gameCode
     * @param pageNum
     * @param pageSize
     * @return java.util.List<siyi.game.dao.model.LoginLogInfo>
     */
    List<LoginLogInfo> getLoginLogPageInfo(String playerId, String playerName, String gameCode, int pageNum, int pageSize);
}
