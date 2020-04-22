package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_player_mession_relation")
public class PlayerMessionRelation implements Serializable {
    /**
     * 主键 自增
     */
    @Id
    private Integer id;

    /**
     * 玩家ID
     */
    @Column(name = "player_id")
    private String playerId;

    /**
     * 任务ID
     */
    @Column(name = "mession_id")
    private String messionId;

    /**
     * 完成状态 0：未完成，1：已完成
     */
    @Column(name = "complete_status")
    private String completeStatus;

    /**
     * 当前进度
     */
    private String process;

    /**
     * 任务目标数量
     */
    private String target;

    /**
     * 是否奖励道具 0：否，1：是
     */
    @Column(name = "is_item")
    private String isItem;

    /**
     * 奖励道具ID
     */
    @Column(name = "item_id")
    private String itemId;

    /**
     * 奖励道具数量
     */
    @Column(name = "item_num")
    private String itemNum;

    /**
     * 任务奖励金币
     */
    private String gold;

    /**
     * 任务奖励经验
     */
    private String exp;

    /**
     * 归属任务栏id 该任务属于哪一个任务栏
     */
    @Column(name = "blank_id")
    private String blankId;

    /**
     * 任务描述
     */
    @Column(name = "mession_tips")
    private String messionTips;

    /**
     * 目标道具
     */
    @Column(name = "target_item")
    private String targetItem;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键 自增
     *
     * @return id - 主键 自增
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键 自增
     *
     * @param id 主键 自增
     */
    public void setId(Integer id) {
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
     * 获取完成状态 0：未完成，1：已完成
     *
     * @return complete_status - 完成状态 0：未完成，1：已完成
     */
    public String getCompleteStatus() {
        return completeStatus;
    }

    /**
     * 设置完成状态 0：未完成，1：已完成
     *
     * @param completeStatus 完成状态 0：未完成，1：已完成
     */
    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus == null ? null : completeStatus.trim();
    }

    /**
     * 获取当前进度
     *
     * @return process - 当前进度
     */
    public String getProcess() {
        return process;
    }

    /**
     * 设置当前进度
     *
     * @param process 当前进度
     */
    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    /**
     * 获取任务目标数量
     *
     * @return target - 任务目标数量
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置任务目标数量
     *
     * @param target 任务目标数量
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 获取是否奖励道具 0：否，1：是
     *
     * @return is_item - 是否奖励道具 0：否，1：是
     */
    public String getIsItem() {
        return isItem;
    }

    /**
     * 设置是否奖励道具 0：否，1：是
     *
     * @param isItem 是否奖励道具 0：否，1：是
     */
    public void setIsItem(String isItem) {
        this.isItem = isItem == null ? null : isItem.trim();
    }

    /**
     * 获取奖励道具ID
     *
     * @return item_id - 奖励道具ID
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 设置奖励道具ID
     *
     * @param itemId 奖励道具ID
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * 获取奖励道具数量
     *
     * @return item_num - 奖励道具数量
     */
    public String getItemNum() {
        return itemNum;
    }

    /**
     * 设置奖励道具数量
     *
     * @param itemNum 奖励道具数量
     */
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? null : itemNum.trim();
    }

    /**
     * 获取任务奖励金币
     *
     * @return gold - 任务奖励金币
     */
    public String getGold() {
        return gold;
    }

    /**
     * 设置任务奖励金币
     *
     * @param gold 任务奖励金币
     */
    public void setGold(String gold) {
        this.gold = gold == null ? null : gold.trim();
    }

    /**
     * 获取任务奖励经验
     *
     * @return exp - 任务奖励经验
     */
    public String getExp() {
        return exp;
    }

    /**
     * 设置任务奖励经验
     *
     * @param exp 任务奖励经验
     */
    public void setExp(String exp) {
        this.exp = exp == null ? null : exp.trim();
    }

    /**
     * 获取归属任务栏id 该任务属于哪一个任务栏
     *
     * @return blank_id - 归属任务栏id 该任务属于哪一个任务栏
     */
    public String getBlankId() {
        return blankId;
    }

    /**
     * 设置归属任务栏id 该任务属于哪一个任务栏
     *
     * @param blankId 归属任务栏id 该任务属于哪一个任务栏
     */
    public void setBlankId(String blankId) {
        this.blankId = blankId == null ? null : blankId.trim();
    }

    /**
     * 获取任务描述
     *
     * @return blank_id - 任务描述
     */
    public String getMessionTips() {
        return messionTips;
    }

    /**
     * 设置任务描述
     *
     * @param messionTips 任务描述
     */
    public void setMessionTips(String messionTips) {
        this.messionTips = messionTips;
    }

    /**
     * 获取目标道具
     *
     * @return targetItem 目标道具
     */
    public String getTargetItem() {
        return targetItem;
    }

    /**
     * 设置目标道具
     *
     * @param targetItem 目标道具
     */
    public void setTargetItem(String targetItem) {
        this.targetItem = targetItem;
    }

    @Override
    public String toString() {
        return "PlayerMessionRelation{" +
                "id=" + id +
                ", playerId='" + playerId + '\'' +
                ", messionId='" + messionId + '\'' +
                ", completeStatus='" + completeStatus + '\'' +
                ", process='" + process + '\'' +
                ", target='" + target + '\'' +
                ", isItem='" + isItem + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemNum='" + itemNum + '\'' +
                ", gold='" + gold + '\'' +
                ", exp='" + exp + '\'' +
                ", blankId='" + blankId + '\'' +
                ", messionTips='" + messionTips + '\'' +
                ", targetItem='" + targetItem + '\'' +
                '}';
    }
}
