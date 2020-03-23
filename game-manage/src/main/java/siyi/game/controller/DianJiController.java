package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.bo.gamelevel.GameLevel;
import siyi.game.dao.DianJiConfigMapper;
import siyi.game.dao.GamelevelConfigMapper;
import siyi.game.dao.QuTianziMapper;
import siyi.game.dao.entity.DianJiConfig;
import siyi.game.dao.entity.GamelevelConfig;
import siyi.game.manager.excel.read.DianJiConfigDataListener;
import siyi.game.manager.excel.read.GameLevelConfigDataListener;
import siyi.game.service.gamelevel.GameLevelService;

import java.io.File;

/**
 * description: 跟游戏关卡相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping(value = "idiom/dianji")
public class DianJiController {
    @Autowired
    private DianJiConfigMapper dianJiConfigMapper;

    @GetMapping(value = "fileAnalysis")
    public void fileAnalysis(){
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "K_dianji.xlsx";
            System.out.println(filePath);
            EasyExcel.read(filePath, DianJiConfig.class, new DianJiConfigDataListener(dianJiConfigMapper)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
