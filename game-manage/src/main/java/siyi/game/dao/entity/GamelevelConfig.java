package siyi.game.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
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

    @Column(name = "QUTION_TIANZI")
    private String qutionTianzi;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUTION_TIANZI_WRONG")
    private String qutionTianziWrong;

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

    @Column(name = "QUTION_DUICUO")
    private String qutionDuicuo;

    /**
     * 允许错误的次数
     */
    @Column(name = "QUTION_DUICUO_WRONG")
    private String qutionDuicuoWrong;

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

    private static final long serialVersionUID = 1L;


}