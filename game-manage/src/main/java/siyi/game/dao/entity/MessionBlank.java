package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_mession_blank")
public class MessionBlank implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    private Long id;

    /**
     * 玩家id
     */
    @Column(name = "player_id")
    private String playerId;

    /**
     * 任务栏1状态 0：关，1：开，2：冷却中
     */
    @Column(name = "blank_one_status")
    private String blankOneStatus;

    /**
     * 任务栏2状态 0：关，1：开，2：冷却中
     */
    @Column(name = "blank_two_status")
    private String blankTwoStatus;

    /**
     * 任务栏3状态 0：关，1：开，2：冷却中
     */
    @Column(name = "blank_three_status")
    private String blankThreeStatus;

    /**
     * 任务栏4状态 0：关，1：开，2：冷却中
     */
    @Column(name = "blank_four_status")
    private String blankFourStatus;

    /**
     * 任务栏5状态 0：关，1：开，2：冷却中
     */
    @Column(name = "blank_five_status")
    private String blankFiveStatus;

    /**
     * 任务栏6状态 0：关，1：开，2：冷却中
     */
    @Column(name = "blank_six_status")
    private String blankSixStatus;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键 自增
     *
     * @return id - 主键 自增
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
     * 获取玩家id
     *
     * @return player_id - 玩家id
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
     * 获取任务栏1状态 0：关，1：开，2：冷却中
     *
     * @return blank_one_status - 任务栏1状态 0：关，1：开，2：冷却中
     */
    public String getBlankOneStatus() {
        return blankOneStatus;
    }

    /**
     * 设置任务栏1状态 0：关，1：开，2：冷却中
     *
     * @param blankOneStatus 任务栏1状态 0：关，1：开，2：冷却中
     */
    public void setBlankOneStatus(String blankOneStatus) {
        this.blankOneStatus = blankOneStatus == null ? null : blankOneStatus.trim();
    }

    /**
     * 获取任务栏2状态 0：关，1：开，2：冷却中
     *
     * @return blank_two_status - 任务栏2状态 0：关，1：开，2：冷却中
     */
    public String getBlankTwoStatus() {
        return blankTwoStatus;
    }

    /**
     * 设置任务栏2状态 0：关，1：开，2：冷却中
     *
     * @param blankTwoStatus 任务栏2状态 0：关，1：开，2：冷却中
     */
    public void setBlankTwoStatus(String blankTwoStatus) {
        this.blankTwoStatus = blankTwoStatus == null ? null : blankTwoStatus.trim();
    }

    /**
     * 获取任务栏3状态 0：关，1：开，2：冷却中
     *
     * @return blank_three_status - 任务栏3状态 0：关，1：开，2：冷却中
     */
    public String getBlankThreeStatus() {
        return blankThreeStatus;
    }

    /**
     * 设置任务栏3状态 0：关，1：开，2：冷却中
     *
     * @param blankThreeStatus 任务栏3状态 0：关，1：开，2：冷却中
     */
    public void setBlankThreeStatus(String blankThreeStatus) {
        this.blankThreeStatus = blankThreeStatus == null ? null : blankThreeStatus.trim();
    }

    /**
     * 获取任务栏4状态 0：关，1：开，2：冷却中
     *
     * @return blank_four_status - 任务栏4状态 0：关，1：开，2：冷却中
     */
    public String getBlankFourStatus() {
        return blankFourStatus;
    }

    /**
     * 设置任务栏4状态 0：关，1：开，2：冷却中
     *
     * @param blankFourStatus 任务栏4状态 0：关，1：开，2：冷却中
     */
    public void setBlankFourStatus(String blankFourStatus) {
        this.blankFourStatus = blankFourStatus == null ? null : blankFourStatus.trim();
    }

    /**
     * 获取任务栏5状态 0：关，1：开，2：冷却中
     *
     * @return blank_five_status - 任务栏5状态 0：关，1：开，2：冷却中
     */
    public String getBlankFiveStatus() {
        return blankFiveStatus;
    }

    /**
     * 设置任务栏5状态 0：关，1：开，2：冷却中
     *
     * @param blankFiveStatus 任务栏5状态 0：关，1：开，2：冷却中
     */
    public void setBlankFiveStatus(String blankFiveStatus) {
        this.blankFiveStatus = blankFiveStatus == null ? null : blankFiveStatus.trim();
    }

    /**
     * 获取任务栏6状态 0：关，1：开，2：冷却中
     *
     * @return blank_six_status - 任务栏6状态 0：关，1：开，2：冷却中
     */
    public String getBlankSixStatus() {
        return blankSixStatus;
    }

    /**
     * 设置任务栏6状态 0：关，1：开，2：冷却中
     *
     * @param blankSixStatus 任务栏6状态 0：关，1：开，2：冷却中
     */
    public void setBlankSixStatus(String blankSixStatus) {
        this.blankSixStatus = blankSixStatus == null ? null : blankSixStatus.trim();
    }
}