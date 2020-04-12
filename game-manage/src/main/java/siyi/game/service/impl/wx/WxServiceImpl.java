package siyi.game.service.impl.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import siyi.game.service.wx.WxService;
import siyi.game.utill.CacheClass;
import siyi.game.utill.EncryptUtil;

import java.util.*;

@Slf4j
@Service
public class WxServiceImpl implements WxService {

    private static final String SIGNTYPE = "hmac_sha256";
    private static final int SUCCESS_CODE = 0;
    @Value("${appid}")
    private String appid;
    @Value("${secret}")
    private String secret;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public boolean setUserStorage(Map data, String sessionKey, String platformId) {
        boolean flag = false;
        List list = new ArrayList();
        list.add(data);
        Map param = new HashMap();
        param.put("kv_list", list);
        String paramStr = JSON.toJSONString(param);

        String signature = generateSignature(paramStr, sessionKey, SIGNTYPE);
        String accessToken = CacheClass.getCache("accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
            Map response = restTemplate.getForObject(url, HashMap.class);
            accessToken = (String) response.get("access_token");
            log.info("=== accessToken:{} ===", accessToken);
            // 动态更新配置
            CacheClass.setCache("accessToken", accessToken);
        }
        String url = "https://api.weixin.qq.com/wxa/set_user_storage?access_token=" + accessToken + "&signature=" + signature +
                "&openid=" + platformId + "&sig_method=" + SIGNTYPE;
        log.info("=== 微信接口url：{} ===", url);
        Map response = restTemplate.postForObject(url, paramStr, Map.class);
        log.info("=== 微信set_user_storage接口返回值：{} ===", JSON.toJSONString(response));
        if ((int) response.get("errcode") == 0) {
            flag = true;
        } else {
            log.warn("==== 数据上送微信失败 ====");
            flag = false;
        }
        return flag;
    }

    @Override
    public String generateSignature(String data, String key, String signType) {
        try {
            if ("hmac_sha256".equals(signType)) {
                log.info("=== 开始生成微信的电子签名 ===");
                return EncryptUtil.HMACSHA256(data,key);
            }
        } catch (Exception e) {
            log.error("=== 微信签名生成失败：{} ===", e);
        }
        return null;
    }
}
