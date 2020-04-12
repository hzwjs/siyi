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
    String generateSignature(String data, String key, String signType);
}
