package siyi.game.bo.functionbtn;

import lombok.Data;

@Data
public class ItemBo {
    // 道具编号
    String id;
    // 道具名称
    String name;
    // 描述
    String tips;
    // 道具状态，是否可用:0,不可用；1，可用
    String status;
    // 数量
    String num;
    // 道具类型: 0-续命卡；1-无限体力卡；2-体力点卡；3-跳关卡；4-金币卡；5-经验卡；6-金币双倍卡；7-经验双倍卡；8-攻击力双倍卡
    String type;
    // 道具提供的增幅
    int addNum;
    // 玩家等级限制
    String playerLevel;
    // 层级限制
    String gameLevel;
}
