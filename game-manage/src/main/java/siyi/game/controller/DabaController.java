package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.dao.DaBaConfigMapper;
import siyi.game.dao.entity.DaBaConfig;
import siyi.game.manager.excel.read.DabaConfigDataListener;

import java.io.File;

/**
 * description: 跟打靶相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "daba")
public class DabaController {

    @Autowired
    DaBaConfigMapper daBaConfigMapper;

    @RequestMapping(value = "fileAnalysis")
    @ResponseBody
    public void fileAnalysis(){
        try {

            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "K_daba.xlsx";
            System.out.println(filePath);
            EasyExcel.read(filePath, DaBaConfig.class, new DabaConfigDataListener(daBaConfigMapper)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
