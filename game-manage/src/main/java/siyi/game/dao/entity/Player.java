package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_player")
public class Player implements Serializable {
    /**
     * id 主键，自增
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 玩家名称
     */
    @Column(name = "player_name")
    private String playerName;

    /**
     * 玩家ID
     */
    @Column(name = "player_id")
    private String playerId;

    /**
     * 玩家经验
     */
    private String experience;

    /**
     * 金币数量
     */
    private String gold;

    /**
     * 玩家级别
     */
    @Column(name = "player_level")
    private String playerLevel;

    /**
     * 到达游戏关卡
     */
    @Column(name = "game_level")
    private String gameLevel;

    /**
     * 玩家体力值
     */
    @Column(name = "player_hp")
    private String playerHp;

    /**
     * 游戏编码
     */
    @Column(name = "game_code")
    private String gameCode;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id 主键，自增
     *
     * @return ID - id 主键，自增
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id 主键，自增
     *
     * @param id id 主键，自增
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取玩家名称
     *
     * @return player_name - 玩家名称
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * 设置玩家名称
     *
     * @param playerName 玩家名称
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName == null ? null : playerName.trim();
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
     * 获取玩家经验
     *
     * @return experience - 玩家经验
     */
    public String getExperience() {
        return experience;
    }

    /**
     * 设置玩家经验
     *
     * @param experience 玩家经验
     */
    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    /**
     * 获取金币数量
     *
     * @return gold - 金币数量
     */
    public String getGold() {
        return gold;
    }

    /**
     * 设置金币数量
     *
     * @param gold 金币数量
     */
    public void setGold(String gold) {
        this.gold = gold == null ? null : gold.trim();
    }

    /**
     * 获取玩家级别
     *
     * @return player_level - 玩家级别
     */
    public String getPlayerLevel() {
        return playerLevel;
    }

    /**
     * 设置玩家级别
     *
     * @param playerLevel 玩家级别
     */
    public void setPlayerLevel(String playerLevel) {
        this.playerLevel = playerLevel == null ? null : playerLevel.trim();
    }

    /**
     * 获取到达游戏关卡
     *
     * @return game_level - 到达游戏关卡
     */
    public String getGameLevel() {
        return gameLevel;
    }

    /**
     * 设置到达游戏关卡
     *
     * @param gameLevel 到达游戏关卡
     */
    public void setGameLevel(String gameLevel) {
        this.gameLevel = gameLevel == null ? null : gameLevel.trim();
    }

    /**
     * 获取玩家体力值
     *
     * @return player_hp - 玩家体力值
     */
    public String getPlayerHp() {
        return playerHp;
    }

    /**
     * 设置玩家体力值
     *
     * @param playerHp 玩家体力值
     */
    public void setPlayerHp(String playerHp) {
        this.playerHp = playerHp == null ? null : playerHp.trim();
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
}