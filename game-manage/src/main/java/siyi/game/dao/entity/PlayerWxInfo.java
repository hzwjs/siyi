package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_player_wx_info")
public class PlayerWxInfo implements Serializable {
    /**
     * 文武双全游戏ID
     */
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String playerId;

    /**
     * 微信openId
     */
    @Column(name = "wx_openid")
    private String wxOpenid;

    /**
     * 头像url
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 性别 0-未知；1-男；2-女
     */
    private String gender;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 昵称
     */
    @Column(name = "nickName")
    private String nickname;

    /**
     * 乐观锁
     */
    @Column(name = "REVISION")
    private Integer revision;

    /**
     * 创建人
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取文武双全游戏ID
     *
     * @return player_id - 文武双全游戏ID
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * 设置文武双全游戏ID
     *
     * @param playerId 文武双全游戏ID
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    /**
     * 获取微信openId
     *
     * @return wx_openid - 微信openId
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置微信openId
     *
     * @param wxOpenid 微信openId
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    /**
     * 获取头像url
     *
     * @return avatar_url - 头像url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置头像url
     *
     * @param avatarUrl 头像url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * 获取性别 0-未知；1-男；2-女
     *
     * @return gender - 性别 0-未知；1-男；2-女
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别 0-未知；1-男；2-女
     *
     * @param gender 性别 0-未知；1-男；2-女
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickName - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取乐观锁
     *
     * @return REVISION - 乐观锁
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * 设置乐观锁
     *
     * @param revision 乐观锁
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * 获取创建人
     *
     * @return CREATED_BY - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CREATED_TIME - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新人
     *
     * @return UPDATED_BY - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * 获取更新时间
     *
     * @return UPDATED_TIME - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}