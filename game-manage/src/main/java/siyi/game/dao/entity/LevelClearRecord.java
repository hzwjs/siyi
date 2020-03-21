package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_level_clear_record")
public class LevelClearRecord implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    private Long id;

    /**
     * 玩家ID
     */
    @Column(name = "player_id")
    private String playerId;

    /**
     * 游戏编码
     */
    @Column(name = "game_code")
    private String gameCode;

    /**
     * 闯关次数
     */
    @Column(name = "barrier_count")
    private Integer barrierCount;

    /**
     * 最高记录
     */
    @Column(name = "best_score")
    private String bestScore;

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
     * 获取闯关次数
     *
     * @return barrier_count - 闯关次数
     */
    public Integer getBarrierCount() {
        return barrierCount;
    }

    /**
     * 设置闯关次数
     *
     * @param barrierCount 闯关次数
     */
    public void setBarrierCount(Integer barrierCount) {
        this.barrierCount = barrierCount;
    }

    /**
     * 获取最高记录
     *
     * @return best_score - 最高记录
     */
    public String getBestScore() {
        return bestScore;
    }

    /**
     * 设置最高记录
     *
     * @param bestScore 最高记录
     */
    public void setBestScore(String bestScore) {
        this.bestScore = bestScore == null ? null : bestScore.trim();
    }
}