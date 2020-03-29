package siyi.game.bo;

import lombok.Data;

@Data
public class WxLoginResponse {
    String openid;
    String session_key;
    String unionid;
    String errcode;
    String errmsg;
}
