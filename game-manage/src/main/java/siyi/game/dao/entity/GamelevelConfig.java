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

    /**
     * 填字题型权重
     */
    @Column(name = "QUTION_TIANZI")
    private String qutionTianzi;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUTION_TIANZI_WRONG")
    private String qutionTianziWrong;

    /**
     * 选择4题型权重
     */
    @Column(name = "QUESTION_XUANZE4")
    private String questionXuanze4;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUESTION_XUANZE4_WRONG")
    private String questionXuanze4Wrong;

    /**
     * 选择5题型权重
     */
    @Column(name = "QUESTION_XUANZE5")
    private String questionXuanze5;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUESTION_XUANZE5_WRONG")
    private String questionXuanze5Wrong;

    /**
     * 选择7题型权重
     */
    @Column(name = "QUESTION_XUANZE7")
    private String questionXuanze7;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUESTION_XUANZE7_WRONG")
    private String questionXuanze7Wrong;

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

    /**
     * 选择题型权重
     */
    @Column(name = "QUTION_XUANZE")
    private String qutionXuanze;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUTION_XUANZE_WRONG")
    private String qutionXuanzeWrong;

    /**
     * 选择题型变黑的概率
     */
    @Column(name = "BIAN_HEI_GAI_LV2")
    private String bianHeiGaiLv2;

    /**
     * 对错题型权重
     */
    @Column(name = "QUTION_DUICUO")
    private String qutionDuicuo;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUTION_DUICUO_WRONG")
    private String qutionDuicuoWrong;

    /**
     * 对错题型2权重
     */
    @Column(name = "QUTION_DUICUO2")
    private String qutionDuicuo2;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUTION_DUICUO2_WRONG")
    private String qutionDuicuo2Wrong;

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
    @Column(name = "JIANG_LI_ITEM")
    private String jiangLiItem;

    /**
     * 道具数量
     */
    @Column(name = "ITEM_SHU_LIANG")
    private String itemShuLiang;

    /**
     * 奖励概率
     */
    @Column(name = "JIANGLI_GAI_LV")
    private String jiangliGaiLv;

    /**
     * 显示广告概率
     */
    @Column(name = "SHOW_AD")
    private String showAd;

    /**
     * 自动出现广告的概率
     */
    @Column(name = "SHOW_AD_AUTO")
    private String showAdAuto;

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
    @Column(name = "WU_GAI_LV")
    private String wuGaiLv;

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

    private String untitled;

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
     * 获取填字题型权重
     *
     * @return QUTION_TIANZI - 填字题型权重
     */
    public String getQutionTianzi() {
        return qutionTianzi;
    }

    /**
     * 设置填字题型权重
     *
     * @param qutionTianzi 填字题型权重
     */
    public void setQutionTianzi(String qutionTianzi) {
        this.qutionTianzi = qutionTianzi == null ? null : qutionTianzi.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUTION_TIANZI_WRONG - 允许错误的次数
     */
    public String getQutionTianziWrong() {
        return qutionTianziWrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qutionTianziWrong 允许错误的次数
     */
    public void setQutionTianziWrong(String qutionTianziWrong) {
        this.qutionTianziWrong = qutionTianziWrong == null ? null : qutionTianziWrong.trim();
    }

    /**
     * 获取选择4题型权重
     *
     * @return QUESTION_XUANZE4 - 选择4题型权重
     */
    public String getQuestionXuanze4() {
        return questionXuanze4;
    }

    /**
     * 设置选择4题型权重
     *
     * @param questionXuanze4 选择4题型权重
     */
    public void setQuestionXuanze4(String questionXuanze4) {
        this.questionXuanze4 = questionXuanze4 == null ? null : questionXuanze4.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUESTION_XUANZE4_WRONG - 允许错误的次数
     */
    public String getQuestionXuanze4Wrong() {
        return questionXuanze4Wrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param questionXuanze4Wrong 允许错误的次数
     */
    public void setQuestionXuanze4Wrong(String questionXuanze4Wrong) {
        this.questionXuanze4Wrong = questionXuanze4Wrong == null ? null : questionXuanze4Wrong.trim();
    }

    /**
     * 获取选择5题型权重
     *
     * @return QUESTION_XUANZE5 - 选择5题型权重
     */
    public String getQuestionXuanze5() {
        return questionXuanze5;
    }

    /**
     * 设置选择5题型权重
     *
     * @param questionXuanze5 选择5题型权重
     */
    public void setQuestionXuanze5(String questionXuanze5) {
        this.questionXuanze5 = questionXuanze5 == null ? null : questionXuanze5.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUESTION_XUANZE5_WRONG - 允许错误的次数
     */
    public String getQuestionXuanze5Wrong() {
        return questionXuanze5Wrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param questionXuanze5Wrong 允许错误的次数
     */
    public void setQuestionXuanze5Wrong(String questionXuanze5Wrong) {
        this.questionXuanze5Wrong = questionXuanze5Wrong == null ? null : questionXuanze5Wrong.trim();
    }

    /**
     * 获取选择7题型权重
     *
     * @return QUESTION_XUANZE7 - 选择7题型权重
     */
    public String getQuestionXuanze7() {
        return questionXuanze7;
    }

    /**
     * 设置选择7题型权重
     *
     * @param questionXuanze7 选择7题型权重
     */
    public void setQuestionXuanze7(String questionXuanze7) {
        this.questionXuanze7 = questionXuanze7 == null ? null : questionXuanze7.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUESTION_XUANZE7_WRONG - 允许错误的次数
     */
    public String getQuestionXuanze7Wrong() {
        return questionXuanze7Wrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param questionXuanze7Wrong 允许错误的次数
     */
    public void setQuestionXuanze7Wrong(String questionXuanze7Wrong) {
        this.questionXuanze7Wrong = questionXuanze7Wrong == null ? null : questionXuanze7Wrong.trim();
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
     * 获取选择题型权重
     *
     * @return QUTION_XUANZE - 选择题型权重
     */
    public String getQutionXuanze() {
        return qutionXuanze;
    }

    /**
     * 设置选择题型权重
     *
     * @param qutionXuanze 选择题型权重
     */
    public void setQutionXuanze(String qutionXuanze) {
        this.qutionXuanze = qutionXuanze == null ? null : qutionXuanze.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUTION_XUANZE_WRONG - 允许错误的次数
     */
    public String getQutionXuanzeWrong() {
        return qutionXuanzeWrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qutionXuanzeWrong 允许错误的次数
     */
    public void setQutionXuanzeWrong(String qutionXuanzeWrong) {
        this.qutionXuanzeWrong = qutionXuanzeWrong == null ? null : qutionXuanzeWrong.trim();
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
     * 获取对错题型权重
     *
     * @return QUTION_DUICUO - 对错题型权重
     */
    public String getQutionDuicuo() {
        return qutionDuicuo;
    }

    /**
     * 设置对错题型权重
     *
     * @param qutionDuicuo 对错题型权重
     */
    public void setQutionDuicuo(String qutionDuicuo) {
        this.qutionDuicuo = qutionDuicuo == null ? null : qutionDuicuo.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUTION_DUICUO_WRONG - 允许错误的次数
     */
    public String getQutionDuicuoWrong() {
        return qutionDuicuoWrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qutionDuicuoWrong 允许错误的次数
     */
    public void setQutionDuicuoWrong(String qutionDuicuoWrong) {
        this.qutionDuicuoWrong = qutionDuicuoWrong == null ? null : qutionDuicuoWrong.trim();
    }

    /**
     * 获取对错题型2权重
     *
     * @return QUTION_DUICUO2 - 对错题型2权重
     */
    public String getQutionDuicuo2() {
        return qutionDuicuo2;
    }

    /**
     * 设置对错题型2权重
     *
     * @param qutionDuicuo2 对错题型2权重
     */
    public void setQutionDuicuo2(String qutionDuicuo2) {
        this.qutionDuicuo2 = qutionDuicuo2 == null ? null : qutionDuicuo2.trim();
    }

    /**
     * 获取允许错误的次数
     *
     * @return QUTION_DUICUO2_WRONG - 允许错误的次数
     */
    public String getQutionDuicuo2Wrong() {
        return qutionDuicuo2Wrong;
    }

    /**
     * 设置允许错误的次数
     *
     * @param qutionDuicuo2Wrong 允许错误的次数
     */
    public void setQutionDuicuo2Wrong(String qutionDuicuo2Wrong) {
        this.qutionDuicuo2Wrong = qutionDuicuo2Wrong == null ? null : qutionDuicuo2Wrong.trim();
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
     * @return JIANG_LI_ITEM - 奖励道具
     */
    public String getJiangLiItem() {
        return jiangLiItem;
    }

    /**
     * 设置奖励道具
     *
     * @param jiangLiItem 奖励道具
     */
    public void setJiangLiItem(String jiangLiItem) {
        this.jiangLiItem = jiangLiItem == null ? null : jiangLiItem.trim();
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
     * @return JIANGLI_GAI_LV - 奖励概率
     */
    public String getJiangliGaiLv() {
        return jiangliGaiLv;
    }

    /**
     * 设置奖励概率
     *
     * @param jiangliGaiLv 奖励概率
     */
    public void setJiangliGaiLv(String jiangliGaiLv) {
        this.jiangliGaiLv = jiangliGaiLv == null ? null : jiangliGaiLv.trim();
    }

    /**
     * 获取显示广告概率
     *
     * @return SHOW_AD - 显示广告概率
     */
    public String getShowAd() {
        return showAd;
    }

    /**
     * 设置显示广告概率
     *
     * @param showAd 显示广告概率
     */
    public void setShowAd(String showAd) {
        this.showAd = showAd == null ? null : showAd.trim();
    }

    /**
     * 获取自动出现广告的概率
     *
     * @return SHOW_AD_AUTO - 自动出现广告的概率
     */
    public String getShowAdAuto() {
        return showAdAuto;
    }

    /**
     * 设置自动出现广告的概率
     *
     * @param showAdAuto 自动出现广告的概率
     */
    public void setShowAdAuto(String showAdAuto) {
        this.showAdAuto = showAdAuto == null ? null : showAdAuto.trim();
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
     * @return WU_GAI_LV - 武关出现的概率
     */
    public String getWuGaiLv() {
        return wuGaiLv;
    }

    /**
     * 设置武关出现的概率
     *
     * @param wuGaiLv 武关出现的概率
     */
    public void setWuGaiLv(String wuGaiLv) {
        this.wuGaiLv = wuGaiLv == null ? null : wuGaiLv.trim();
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

    /**
     * @return untitled
     */
    public String getUntitled() {
        return untitled;
    }

    /**
     * @param untitled
     */
    public void setUntitled(String untitled) {
        this.untitled = untitled == null ? null : untitled.trim();
    }
}