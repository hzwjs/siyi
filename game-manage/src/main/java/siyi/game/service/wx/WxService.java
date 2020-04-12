package siyi.game.service.wx;

import java.util.Map;

/**
 * 微信服务接口
 */
public interface WxService {

    /**
     * 生成电子签名
     * @param data 需要加签的数据
     * @param key 加签的密钥
     * @param signType 加签方式
     * @return
     */
    String generateSignature(final String data, String key, String signType);

    /**
     * 将信息上送微信托管
     * @param data       待托管的数据
     * @param sessionKey 登录返回的session_key
     * @param platformId 微信的openId
     */
    boolean setUserStorage(Map data, String sessionKey, String platformId);
}
