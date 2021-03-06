package siyi.game.bo.gamelevel;

import lombok.Data;
import siyi.game.dao.entity.DianJiConfig;

import java.util.List;

/**
 * description: 武关配置信息 <br>
 * date: 2020/3/1 14:26 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Data
public class ConfigWu {
    // 关卡数
    private String levelId;
    // 武关类型
    private String levelType;
    // 关卡配置出现道具数量
    private String baseNum;
    // 需破坏数量
    private String totalNum;
    // 坠落速度
    private String speed;
    // 冷却时间
    private String cd;
    // hp系数
    private String hpXishu;
    // 道具hp
    private String hp;
    // 关卡时间
    private String guanqiaTime;
    // 关卡奖励时间
    private String jiangliTime;
    // 关卡金币
    private String gold;
    // 关卡经验
    private String exp;
    // 规则配置时间
    private String guizeTime;
    // 道具id
    private String item;
    // 道具数量
    private String itemNum;
    // 是否奖励道具
    private boolean isHaveItem;
    // 是否出现双倍
    private boolean isFuhuo;
    // 是否出现广告
    private boolean isGuangGao;
    // 出现时间
    private String chuxianTime;
    // 出现次数
    private String cishu;
    // 错误次数
    private String wrong;
    // 杯子数量
    private String beiziNum;
    // 球数量
    private String qiuNum;
    // 交换次数
    private String jiaohuanNum;
    // 翻牌数
    private String fanpainum;
    // 翻牌文字集合
    private List<String> wordList;
    // 命中规则
    private String hitRule;
    // 点击关卡信息
    private List<DianJiConfigInfo> dianJiConfigs;
}
