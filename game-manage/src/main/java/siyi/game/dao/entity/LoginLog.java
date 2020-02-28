package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_login_log")
public class LoginLog implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 游戏登录id
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 玩家ID
     */
    @Column(name = "player_id")
    private String playerId;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登出时间
     */
    @Column(name = "logout_time")
    private Date logoutTime;

    /**
     * 广告点击次数
     */
    @Column(name = "advert_frequency")
    private Integer advertFrequency;

    /**
     * 游戏编码
     */
    @Column(name = "game_code")
    private String gameCode;

    /**
     * 登录时长
     */
    @Column(name = "login_duration")
    private Date loginDuration;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键，自增
     *
     * @return id - 主键，自增
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键，自增
     *
     * @param id 主键，自增
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取游戏登录id
     *
     * @return open_id - 游戏登录id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置游戏登录id
     *
     * @param openId 游戏登录id
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * 获取玩家ID
     *
     * @return player_id - 玩家ID
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * 设置玩家ID
     *
     * @param playerId 玩家ID
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登出时间
     *
     * @return logout_time - 登出时间
     */
    public Date getLogoutTime() {
        return logoutTime;
    }

    /**
     * 设置登出时间
     *
     * @param logoutTime 登出时间
     */
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    /**
     * 获取广告点击次数
     *
     * @return advert_frequency - 广告点击次数
     */
    public Integer getAdvertFrequency() {
        return advertFrequency;
    }

    /**
     * 设置广告点击次数
     *
     * @param advertFrequency 广告点击次数
     */
    public void setAdvertFrequency(Integer advertFrequency) {
        this.advertFrequency = advertFrequency;
    }

    /**
     * 获取游戏编码
     *
     * @return game_code - 游戏编码
     */
    public String getGameCode() {
        return gameCode;
    }

    /**
     * 设置游戏编码
     *
     * @param gameCode 游戏编码
     */
    public void setGameCode(String gameCode) {
        this.gameCode = gameCode == null ? null : gameCode.trim();
    }

    /**
     * 获取登录时长
     *
     * @return login_duration - 登录时长
     */
    public Date getLoginDuration() {
        return loginDuration;
    }

    /**
     * 设置登录时长
     *
     * @param loginDuration 登录时长
     */
    public void setLoginDuration(Date loginDuration) {
        this.loginDuration = loginDuration;
    }
}