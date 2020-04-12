package siyi.game.service.impl.wx;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import siyi.game.service.wx.WxService;
import siyi.game.utill.EncryptUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class WxServiceImpl implements WxService {

    @Override
    public String generateSignature(String data, String key, String signType) {
//        Set<String> keySet = data.keySet();
//        String[] keyArray = keySet.toArray(new String[keySet.size()]);
//        Arrays.sort(keyArray);
//        StringBuilder sb = new StringBuilder();
// ==============
        try {
            if ("MD5".equals(signType)) {
                //return EncryptUtil.MD5(sb.toString()).toUpperCase();
            }
            else if ("hmac_sha256".equals(signType)) {
                log.info("=== 开始生成微信的电子签名 ===");
                return EncryptUtil.HMACSHA256(data,key);
            }
            else {
                throw new Exception(String.format("Invalid sign_type: %s", signType));
            }
        } catch (Exception e) {
            log.error("=== 微信签名生成失败：{} ===", e);
        }
        return null;
    }
}
