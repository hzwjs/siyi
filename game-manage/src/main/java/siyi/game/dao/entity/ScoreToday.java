package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_score_today")
public class ScoreToday implements Serializable {
    /**
     * 玩家id
     */
    @Id
    @Column(name = "player_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String playerId;

    /**
     * 文关通关次数
     */
    @Column(name = "wen_pass_num")
    private Integer wenPassNum;

    /**
     * 武关通关次数
     */
    @Column(name = "wu_pass_num")
    private Integer wuPassNum;

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
     * 获取文关通关次数
     *
     * @return wen_pass_num - 文关通关次数
     */
    public Integer getWenPassNum() {
        return wenPassNum;
    }

    /**
     * 设置文关通关次数
     *
     * @param wenPassNum 文关通关次数
     */
    public void setWenPassNum(Integer wenPassNum) {
        this.wenPassNum = wenPassNum;
    }

    /**
     * 获取武关通关次数
     *
     * @return wu_pass_num - 武关通关次数
     */
    public Integer getWuPassNum() {
        return wuPassNum;
    }

    /**
     * 设置武关通关次数
     *
     * @param wuPassNum 武关通关次数
     */
    public void setWuPassNum(Integer wuPassNum) {
        this.wuPassNum = wuPassNum;
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