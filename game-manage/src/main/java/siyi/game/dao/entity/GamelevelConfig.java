package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_gamelevel_config")
public class GamelevelConfig implements Serializable {
    /**
     * 关卡ID
     */
    @Id
    @Column(name = "GAME_LEVEL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String gameLevelId;

    /**
     * 关卡限时
     */
    @Column(name = "TIME")
    private String time;

    @Column(name = "Q_TIANZI")
    private String qTianzi;

    /**
     * 允许错误的次数
     */
    @Column(name = "Q_TIANZI_WRONG")
    private Integer qTianziWrong;

    /**
     * 旋转概率
     */
    @Column(name = "XUAN_ZHUAN_GAI_LV")
    private String xuanZhuanGaiLv;

    /**
     * 交换概率
     */
    @Column(name = "JIAO_HUAN_GAI_LV")
    private String jiaoHuanGaiLv;

    /**
     * 变黑概率
     */
    @Column(name = "BIAN_HEI_GAI_LV")
    private String bianHeiGaiLv;

    /**
     * 点击变亮
     */
    @Column(name = "TAP_GAI_LV")
    private String tapGaiLv;

    /**
     * 类型(0,同步全部调用；1,异步全部调用；2,同步部分调用；3,异步部分调用)
     */
    @Column(name = "TYPE")
    private String type;

    @Column(name = "Q_XUANZE")
    private String qXuanze;

    /**
     * 允许错误的次数
     */
    @Column(name = "Q_XUANZE_WRONG")
    private String qXuanzeWrong;

    /**
     * 选择题型变黑的概率
     */
    @Column(name = "BIAN_HEI_GAI_LV2")
    private String bianHeiGaiLv2;

    @Column(name = "Q_DUICUO")
    private String qDuicuo;

    /**
     * 允许错误的次数
     */
    @Column(name = "Q_DUICUO_WRONG")
    private String qDuicuoWrong;

    /**
     * 奖励金币
     */
    @Column(name = "JIANGLI_GOLD")
    private String jiangliGold;

    /**
     * 奖励经验
     */
    @Column(name = "JIANGLI_EXP")
    private String jiangliExp;

    /**
     * 奖励道具
     */
    @Column(name = "JIANG_LI_TIME")
    private String jiangLiTime;

    /**
     * 道具数量
     */
    @Column(name = "ITEM_SHU_LIANG")
    private String itemShuLiang;

    /**
     * 奖励概率
     */
    @Column(name = "JIANG_LI_GAI_LV")
    private String jiangLiGaiLv;

    /**
     * 双倍按钮出现的概率
     */
    @Column(name = "DOUBLE_GAI_LV")
    private String doubleGaiLv;

    /**
     * 复活按钮出现的概率
     */
    @Column(name = "FU_HUO")
    private String fuHuo;

    /**
     * 速率
     */
    @Column(name = "SPEED")
    private String speed;

    /**
     * CD
     */
    @Column(name = "CD")
    private String cd;

    /**
     * 武关出现的概率
     */
    @Column(name = "W_GAI_LV")
    private String wGaiLv;

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
     * 获取关卡ID
     *
     * @return GAME_LEVEL_ID - 关卡ID
     */
    public String getGameLevelId() {
        return gameLevelId;
    }

    /**
     * 设置关卡ID
     *
     * @param gameLevelId 关卡ID
     */
    public void setGameLevelId(String gameLevelId) {
        this.gameLevelId = gameLevelId == null ? null : gameLevelId.trim();
    }

    /**
     * 获取关卡限时
     *
     * @return TIME - 关卡限时
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置关卡限时
     *
     * @param time 关卡限时
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * @return Q_TIANZI
     */
    public String getqTianzi() {
        return qTianzi;
    }

    /**
     * @param qTianzi
     */
    public void setqTianzi(String qTianzi) {
        this.qTianzi = qTianzi == null ? null : qTianzi.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return Q_TIANZI_WRONG - 允许错误的次数
     */
    public Integer getqTianziWrong() {
        return qTianziWrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qTianziWrong 允许错误的次数
     */
    public void setqTianziWrong(Integer qTianziWrong) {
        this.qTianziWrong = qTianziWrong;
    }

    /**
     * 获取旋转概率
     *
     * @return XUAN_ZHUAN_GAI_LV - 旋转概率
     */
    public String getXuanZhuanGaiLv() {
        return xuanZhuanGaiLv;
    }

    /**
     * 设置旋转概率
     *
     * @param xuanZhuanGaiLv 旋转概率
     */
    public void setXuanZhuanGaiLv(String xuanZhuanGaiLv) {
        this.xuanZhuanGaiLv = xuanZhuanGaiLv == null ? null : xuanZhuanGaiLv.trim();
    }

    /**
     * 获取交换概率
     *
     * @return JIAO_HUAN_GAI_LV - 交换概率
     */
    public String getJiaoHuanGaiLv() {
        return jiaoHuanGaiLv;
    }

    /**
     * 设置交换概率
     *
     * @param jiaoHuanGaiLv 交换概率
     */
    public void setJiaoHuanGaiLv(String jiaoHuanGaiLv) {
        this.jiaoHuanGaiLv = jiaoHuanGaiLv == null ? null : jiaoHuanGaiLv.trim();
    }

    /**
     * 获取变黑概率
     *
     * @return BIAN_HEI_GAI_LV - 变黑概率
     */
    public String getBianHeiGaiLv() {
        return bianHeiGaiLv;
    }

    /**
     * 设置变黑概率
     *
     * @param bianHeiGaiLv 变黑概率
     */
    public void setBianHeiGaiLv(String bianHeiGaiLv) {
        this.bianHeiGaiLv = bianHeiGaiLv == null ? null : bianHeiGaiLv.trim();
    }

    /**
     * 获取点击变亮
     *
     * @return TAP_GAI_LV - 点击变亮
     */
    public String getTapGaiLv() {
        return tapGaiLv;
    }

    /**
     * 设置点击变亮
     *
     * @param tapGaiLv 点击变亮
     */
    public void setTapGaiLv(String tapGaiLv) {
        this.tapGaiLv = tapGaiLv == null ? null : tapGaiLv.trim();
    }

    /**
     * 获取类型(0,同步全部调用；1,异步全部调用；2,同步部分调用；3,异步部分调用)
     *
     * @return TYPE - 类型(0,同步全部调用；1,异步全部调用；2,同步部分调用；3,异步部分调用)
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型(0,同步全部调用；1,异步全部调用；2,同步部分调用；3,异步部分调用)
     *
     * @param type 类型(0,同步全部调用；1,异步全部调用；2,同步部分调用；3,异步部分调用)
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * @return Q_XUANZE
     */
    public String getqXuanze() {
        return qXuanze;
    }

    /**
     * @param qXuanze
     */
    public void setqXuanze(String qXuanze) {
        this.qXuanze = qXuanze == null ? null : qXuanze.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return Q_XUANZE_WRONG - 允许错误的次数
     */
    public String getqXuanzeWrong() {
        return qXuanzeWrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qXuanzeWrong 允许错误的次数
     */
    public void setqXuanzeWrong(String qXuanzeWrong) {
        this.qXuanzeWrong = qXuanzeWrong == null ? null : qXuanzeWrong.trim();
    }

    /**
     * 获取选择题型变黑的概率
     *
     * @return BIAN_HEI_GAI_LV2 - 选择题型变黑的概率
     */
    public String getBianHeiGaiLv2() {
        return bianHeiGaiLv2;
    }

    /**
     * 设置选择题型变黑的概率
     *
     * @param bianHeiGaiLv2 选择题型变黑的概率
     */
    public void setBianHeiGaiLv2(String bianHeiGaiLv2) {
        this.bianHeiGaiLv2 = bianHeiGaiLv2 == null ? null : bianHeiGaiLv2.trim();
    }

    /**
     * @return Q_DUICUO
     */
    public String getqDuicuo() {
        return qDuicuo;
    }

    /**
     * @param qDuicuo
     */
    public void setqDuicuo(String qDuicuo) {
        this.qDuicuo = qDuicuo == null ? null : qDuicuo.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return Q_DUICUO_WRONG - 允许错误的次数
     */
    public String getqDuicuoWrong() {
        return qDuicuoWrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qDuicuoWrong 允许错误的次数
     */
    public void setqDuicuoWrong(String qDuicuoWrong) {
        this.qDuicuoWrong = qDuicuoWrong == null ? null : qDuicuoWrong.trim();
    }

    /**
     * 获取奖励金币
     *
     * @return JIANGLI_GOLD - 奖励金币
     */
    public String getJiangliGold() {
        return jiangliGold;
    }

    /**
     * 设置奖励金币
     *
     * @param jiangliGold 奖励金币
     */
    public void setJiangliGold(String jiangliGold) {
        this.jiangliGold = jiangliGold == null ? null : jiangliGold.trim();
    }

    /**
     * 获取奖励经验
     *
     * @return JIANGLI_EXP - 奖励经验
     */
    public String getJiangliExp() {
        return jiangliExp;
    }

    /**
     * 设置奖励经验
     *
     * @param jiangliExp 奖励经验
     */
    public void setJiangliExp(String jiangliExp) {
        this.jiangliExp = jiangliExp == null ? null : jiangliExp.trim();
    }

    /**
     * 获取奖励道具
     *
     * @return JIANG_LI_TIME - 奖励道具
     */
    public String getJiangLiTime() {
        return jiangLiTime;
    }

    /**
     * 设置奖励道具
     *
     * @param jiangLiTime 奖励道具
     */
    public void setJiangLiTime(String jiangLiTime) {
        this.jiangLiTime = jiangLiTime == null ? null : jiangLiTime.trim();
    }

    /**
     * 获取道具数量
     *
     * @return ITEM_SHU_LIANG - 道具数量
     */
    public String getItemShuLiang() {
        return itemShuLiang;
    }

    /**
     * 设置道具数量
     *
     * @param itemShuLiang 道具数量
     */
    public void setItemShuLiang(String itemShuLiang) {
        this.itemShuLiang = itemShuLiang == null ? null : itemShuLiang.trim();
    }

    /**
     * 获取奖励概率
     *
     * @return JIANG_LI_GAI_LV - 奖励概率
     */
    public String getJiangLiGaiLv() {
        return jiangLiGaiLv;
    }

    /**
     * 设置奖励概率
     *
     * @param jiangLiGaiLv 奖励概率
     */
    public void setJiangLiGaiLv(String jiangLiGaiLv) {
        this.jiangLiGaiLv = jiangLiGaiLv == null ? null : jiangLiGaiLv.trim();
    }

    /**
     * 获取双倍按钮出现的概率
     *
     * @return DOUBLE_GAI_LV - 双倍按钮出现的概率
     */
    public String getDoubleGaiLv() {
        return doubleGaiLv;
    }

    /**
     * 设置双倍按钮出现的概率
     *
     * @param doubleGaiLv 双倍按钮出现的概率
     */
    public void setDoubleGaiLv(String doubleGaiLv) {
        this.doubleGaiLv = doubleGaiLv == null ? null : doubleGaiLv.trim();
    }

    /**
     * 获取复活按钮出现的概率
     *
     * @return FU_HUO - 复活按钮出现的概率
     */
    public String getFuHuo() {
        return fuHuo;
    }

    /**
     * 设置复活按钮出现的概率
     *
     * @param fuHuo 复活按钮出现的概率
     */
    public void setFuHuo(String fuHuo) {
        this.fuHuo = fuHuo == null ? null : fuHuo.trim();
    }

    /**
     * 获取速率
     *
     * @return SPEED - 速率
     */
    public String getSpeed() {
        return speed;
    }

    /**
     * 设置速率
     *
     * @param speed 速率
     */
    public void setSpeed(String speed) {
        this.speed = speed == null ? null : speed.trim();
    }

    /**
     * 获取CD
     *
     * @return CD - CD
     */
    public String getCd() {
        return cd;
    }

    /**
     * 设置CD
     *
     * @param cd CD
     */
    public void setCd(String cd) {
        this.cd = cd == null ? null : cd.trim();
    }

    /**
     * 获取武关出现的概率
     *
     * @return W_GAI_LV - 武关出现的概率
     */
    public String getwGaiLv() {
        return wGaiLv;
    }

    /**
     * 设置武关出现的概率
     *
     * @param wGaiLv 武关出现的概率
     */
    public void setwGaiLv(String wGaiLv) {
        this.wGaiLv = wGaiLv == null ? null : wGaiLv.trim();
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