package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_player_mession_record")
public class PlayerMessionRecord implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 玩家ID
     */
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String playerId;

    /**
     * 任务ID
     */
    @Column(name = "mession_id")
    private String messionId;

    /**
     * 任务领取时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 任务完成时间
     */
    @Column(name = "complete_time")
    private Date completeTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键 自增
     *
     * @return ID - 主键 自增
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键 自增
     *
     * @param id 主键 自增
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取任务ID
     *
     * @return mession_id - 任务ID
     */
    public String getMessionId() {
        return messionId;
    }

    /**
     * 设置任务ID
     *
     * @param messionId 任务ID
     */
    public void setMessionId(String messionId) {
        this.messionId = messionId == null ? null : messionId.trim();
    }

    /**
     * 获取任务领取时间
     *
     * @return create_time - 任务领取时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置任务领取时间
     *
     * @param createTime 任务领取时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取任务完成时间
     *
     * @return complete_time - 任务完成时间
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * 设置任务完成时间
     *
     * @param completeTime 任务完成时间
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
}