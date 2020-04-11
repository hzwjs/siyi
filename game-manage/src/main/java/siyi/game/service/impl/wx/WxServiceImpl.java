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
    public String generateSignature(Map<String, String> data, String key, String signType) {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals("sign")) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        try {
            if ("MD5".equals(signType)) {
                return EncryptUtil.MD5(sb.toString()).toUpperCase();
            }
            else if ("HmacSHA256".equals(signType)) {
                return EncryptUtil.HMACSHA256(sb.toString(), key);
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
