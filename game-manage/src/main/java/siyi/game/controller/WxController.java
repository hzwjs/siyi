package siyi.game.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.entity.Player;
import siyi.game.service.wx.WxService;
import siyi.game.utill.CacheClass;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供微信相关的接口
 */
@Slf4j
@Controller("wx")
public class WxController extends BaseController{
    @Autowired
    WxService wxService;
    @Autowired
    PlayerMapper playerMapper;
    @Autowired
    private RestTemplate restTemplate;

    private static final String SIGNTYPE = "HMACSHA256";
    private static final String SUCCESS_CODE = "0";

    @RequestMapping("setUserStorage")
    @ResponseBody
    public void setUserStorage(String playerId, String level, String sessionKey) {
        Map data = new HashMap();
        data.put("level", level);

        Player player = new Player();
        player.setPlayerId(playerId);
        player = playerMapper.selectOne(player);
        if (player != null) {
            String signature = wxService.generateSignature(data.toString(), sessionKey, SIGNTYPE);
            String accessToken = CacheClass.getCache("accessToken");
            String url = "https://api.weixin.qq.com/wxa/set_user_storage?access_token=" + accessToken + "&signature=" + signature + "&openid=" + player.getPlatformId() + "&sig_method=" + SIGNTYPE;
            Map response = restTemplate.postForObject(url, data, Map.class);
            if (SUCCESS_CODE.equals(response.get("errcode"))) {
                getSuccessResult(new HashMap<>());
            } else {
                getFailResult(new HashMap<>(), "=== 用户的关卡信息上送失败 ===");
            }
        } else {
            getFailResult(new HashMap<>(), "=== playerId非法 ===");
        }

    }

}
