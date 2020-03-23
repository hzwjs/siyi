package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.dao.QuDuicuoMapper;
import siyi.game.dao.entity.QuDuicuo;
import siyi.game.manager.excel.read.QuDuicuoDataListener;

import java.io.File;

/**
 * description: 跟游戏关卡相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "idiom/duicuo")
public class DuiCuoController {
    private final Logger logger = LoggerFactory.getLogger(DuiCuoController.class);


    @Autowired
    QuDuicuoMapper quDuicuoMapper;

    @RequestMapping(value = "fileAnalysis")
    @ResponseBody
    public void fileAnalysis(){
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_duicuo.xlsx";
            EasyExcel.read(filePath, QuDuicuo.class, new QuDuicuoDataListener(quDuicuoMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
