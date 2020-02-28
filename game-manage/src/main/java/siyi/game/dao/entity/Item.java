package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_item")
public class Item implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 道具名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 道具编号
     */
    @Column(name = "item_no")
    private String itemNo;

    /**
     * 道具作用
     */
    @Column(name = "item_affect")
    private String itemAffect;

    /**
     * 游戏编码
     */
    @Column(name = "game_code")
    private String gameCode;

    /**
     * 启用状态 0：启用，1：停用
     */
    @Column(name = "item_status")
    private String itemStatus;

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
     * 获取道具名称
     *
     * @return item_name - 道具名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置道具名称
     *
     * @param itemName 道具名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
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
     * 获取道具作用
     *
     * @return item_affect - 道具作用
     */
    public String getItemAffect() {
        return itemAffect;
    }

    /**
     * 设置道具作用
     *
     * @param itemAffect 道具作用
     */
    public void setItemAffect(String itemAffect) {
        this.itemAffect = itemAffect == null ? null : itemAffect.trim();
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
     * 获取启用状态 0：启用，1：停用
     *
     * @return item_status - 启用状态 0：启用，1：停用
     */
    public String getItemStatus() {
        return itemStatus;
    }

    /**
     * 设置启用状态 0：启用，1：停用
     *
     * @param itemStatus 启用状态 0：启用，1：停用
     */
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus == null ? null : itemStatus.trim();
    }
}