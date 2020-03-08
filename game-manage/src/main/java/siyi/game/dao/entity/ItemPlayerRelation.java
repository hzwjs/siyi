package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Table(name = "tb_item_player_relation")
public class ItemPlayerRelation implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 道具编号
     */
    @Column(name = "item_no")
    private String itemNo;

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
     * 道具数量
     */
    private String quantity;

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
     * 获取道具编号
     *
     * @return item_no - 道具编号
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * 设置道具编号
     *
     * @param itemNo 道具编号
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
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
     * 获取道具数量
     *
     * @return quantity - 道具数量
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * 设置道具数量
     *
     * @param quantity 道具数量
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity == null ? null : quantity.trim();
    }

    @Override
    public String toString() {
        return "ItemPlayerRelation{" +
                "id=" + id +
                ", itemNo='" + itemNo + '\'' +
                ", playerId='" + playerId + '\'' +
                ", gameCode='" + gameCode + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPlayerRelation that = (ItemPlayerRelation) o;
        return Objects.equals(itemNo, that.itemNo) &&
                Objects.equals(playerId, that.playerId) &&
                Objects.equals(gameCode, that.gameCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemNo, playerId, gameCode);
    }
}
