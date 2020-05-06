package siyi.game.bo.functionbtn;

import lombok.Data;

/**
 * 武关当日成功闯关记录
 */
@Data
public class WuSuccessRecord {
    String openId;
    String avatarUrl;
    String nickName;
    Integer level;
}
