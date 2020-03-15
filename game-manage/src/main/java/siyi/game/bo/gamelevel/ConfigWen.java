package siyi.game.bo.gamelevel;

import lombok.Data;

/**
 * 文关配置对象
 */
@Data
public class ConfigWen {
    // 题目ID
    private String questionId;
    // 题目类型
    private String qType;
    // 关卡限时
    private int limitTime;
    // 容错次数
    private String qutionTianziWrong;
    private String qutionXuanzeWrong;
    private String qutionDuicuoWrong;
    // 是否有旋转效果
    private boolean xuanZhuan;
    // 是否有交换效果
    private boolean jiaoHuan;
    // 是否有变黑效果
    private boolean bianHeiTianzi;
    private boolean bianHeiXuanze;
    // 是否有点击周围单元格变亮的效果
    private boolean Tap;
    // 类型(0,同步全部调用；1,异步全部调用；2,同步部分调用；3,异步部分调用)
    private String type;
    // 奖励金币
    private String jiangliGold;
    // 奖励经验
    private String jiangliExp;
    // 奖励道具
    private String jiangLiItem;
    // 奖励道具数量
    private String itemShuLiang;
    // 是否奖励
    private boolean jiangli;
    // 是否出现双倍按钮
    private boolean doubleBut;
    // 是否出现复活按钮
    private boolean fuhouBut;
    // 速率
    private String speed;
    // cd时间
    private String cd;
    // 是否出现武关
    private boolean wuguan;
}
