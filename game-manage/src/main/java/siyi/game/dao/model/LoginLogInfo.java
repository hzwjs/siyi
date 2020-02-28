package siyi.game.dao.model;

import siyi.game.dao.entity.LoginLog;

/**
 * description: LoginLogInfo <br>
 * date: 2020/2/28 22:08 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class LoginLogInfo extends LoginLog {

    /**
     * description: 玩家姓名 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 22:09 <br>
     * author: zhengzhiqiang <br>
     *
     * @param null
     * @return
     */
    private String playerName;

    private String loginDurationStr;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getLoginDurationStr() {
        return loginDurationStr;
    }

    public void setLoginDurationStr(String loginDurationStr) {
        this.loginDurationStr = loginDurationStr;
    }
}
