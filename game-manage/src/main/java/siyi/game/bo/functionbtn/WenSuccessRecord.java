package siyi.game.bo.functionbtn;

import lombok.Data;

/**
 * 文关当日成功闯关记录
 */
@Data
public class WenSuccessRecord {
    String openId;
    String avatarUrl;
    String nickName;
    Integer level;
}
