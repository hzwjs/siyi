package siyi.game.controller;

import org.springframework.stereotype.Controller;
import siyi.game.utill.Dictionary;

import java.util.Map;

/**
 * description: BaseController <br>
 * date: 2020/2/19 21:45 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Controller
public class BaseController {

    public void getSuccessResult(Map<String, Object> resultMap) {
        resultMap.put("resCode", Dictionary.RES_SUCCESS);
        resultMap.put("resMsg", "操作成功");
    }

    public void getFailResult(Map<String, Object> resultMap, String msg) {
        resultMap.put("resCode", Dictionary.RES_FAIL);
        resultMap.put("resMsg", msg);
    }
}
