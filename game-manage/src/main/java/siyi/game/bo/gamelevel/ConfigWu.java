package siyi.game.bo.gamelevel;

import lombok.Data;

/**
 * description: 武关配置信息 <br>
 * date: 2020/3/1 14:26 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Data
public class ConfigWu {

    // 武关类型
    private String levelType;
    // 出现道具数量
    private String totalNum;
    // 坠落速度
    private String speed;
    // 冷却时间
    private String cd;
    // 道具hp
    private String hp;
    // 关卡时间
    private String totalTime;
    // 关卡奖励时间
    private String jiangliTime;
    // 关卡金币
    private String gold;
    // 关卡经验
    private String exp;
    // 总时间
    private String zongTime;
    // 道具id
    private String item;
    // 道具数量
    private String itemNum;
    // 是否奖励道具
    private boolean isHaveItem;
    // 是否出现双倍
    private boolean isDouble;
    // 是否出现广告
    private boolean isGuangGao;

}
