package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_game")
public class Game implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 游戏名称
     */
    @Column(name = "game_name")
    private String gameName;

    /**
     * 游戏编码
     */
    @Column(name = "game_code")
    private String gameCode;

    /**
     * 是否在售 0：在售，1：停售
     */
    @Column(name = "is_on_sale")
    private String isOnSale;

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
     * 获取游戏名称
     *
     * @return game_name - 游戏名称
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * 设置游戏名称
     *
     * @param gameName 游戏名称
     */
    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
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
     * 获取是否在售 0：在售，1：停售
     *
     * @return is_on_sale - 是否在售 0：在售，1：停售
     */
    public String getIsOnSale() {
        return isOnSale;
    }

    /**
     * 设置是否在售 0：在售，1：停售
     *
     * @param isOnSale 是否在售 0：在售，1：停售
     */
    public void setIsOnSale(String isOnSale) {
        this.isOnSale = isOnSale == null ? null : isOnSale.trim();
    }
}