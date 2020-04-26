package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_physical_power")
public class PhysicalPower implements Serializable {
    /**
     * 玩家id
     */
    @Id
    @Column(name = "player_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String playerId;

    /**
     * 体力点数
     */
    private Integer hp;

    /**
     * 玩家等级
     */
    @Column(name = "player_level")
    private String playerLevel;

    /**
     * 当前等级最大的体力值
     */
    @Column(name = "max_hp")
    private Integer maxHp;

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
     * 获取玩家id
     *
     * @return player_Id - 玩家id
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * 设置玩家id
     *
     * @param playerId 玩家id
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    /**
     * 获取体力点数
     *
     * @return hp - 体力点数
     */
    public Integer getHp() {
        return hp;
    }

    /**
     * 设置体力点数
     *
     * @param hp 体力点数
     */
    public void setHp(Integer hp) {
        this.hp = hp;
    }

    /**
     * 获取玩家等级
     *
     * @return player_level - 玩家等级
     */
    public String getPlayerLevel() {
        return playerLevel;
    }

    /**
     * 设置玩家等级
     *
     * @param playerLevel 玩家等级
     */
    public void setPlayerLevel(String playerLevel) {
        this.playerLevel = playerLevel == null ? null : playerLevel.trim();
    }

    /**
     * 获取当前等级最大的体力值
     *
     * @return max_hp - 当前等级最大的体力值
     */
    public Integer getMaxHp() {
        return maxHp;
    }

    /**
     * 设置当前等级最大的体力值
     *
     * @param maxHp 当前等级最大的体力值
     */
    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
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