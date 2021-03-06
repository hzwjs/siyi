package siyi.game.manager.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import siyi.game.utill.CacheClass;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Scope("prototype")
public class WxScheduled {

    @Value("${appid}")
    private String appid;
    @Value("${secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 每隔2个小时去获取一次微信的AccessToken
     */
    @Scheduled(cron = "0 0 0/1 * * *")
    public void getAccessToken() {
        log.info("=== 定时任务-getAccessToken start ===");
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        Map response = restTemplate.getForObject(url, HashMap.class);
        // 动态更新配置
        CacheClass.setCache("accessToken", (String) response.get("access_token"));
        log.info("=== access_token:{} ===",(String) response.get("access_token"));
    }

    public void testTask(Map param ){
        log.info("=== 测试定时任务：{}", new Date());
        log.info("=== name:{}", param.get("name"));
        log.info("=== sex:{}", param.get("sex"));
    }
}
