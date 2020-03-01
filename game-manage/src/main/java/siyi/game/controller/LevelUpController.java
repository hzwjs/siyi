package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.bo.gamelevel.GameLevel;
import siyi.game.dao.GamelevelConfigMapper;
import siyi.game.dao.LevelUpConfigMapper;
import siyi.game.dao.QuTianziMapper;
import siyi.game.dao.entity.GamelevelConfig;
import siyi.game.dao.entity.LevelUpConfig;
import siyi.game.manager.excel.read.GameLevelConfigDataListener;
import siyi.game.manager.excel.read.LevelUpConfigDataListener;
import siyi.game.service.gamelevel.GameLevelService;
import siyi.game.utill.Constants;

import java.io.File;

/**
 * description: 跟游戏关卡相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "levelUp")
public class LevelUpController {
    private final Logger logger = LoggerFactory.getLogger(LevelUpController.class);

    @Autowired
    LevelUpConfigMapper configMapper;


    @RequestMapping(value = "fileAnalysis")
    @ResponseBody
    public void fileAnalysis(){
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "levelup.xlsx";
            EasyExcel.read(filePath, LevelUpConfig.class, new LevelUpConfigDataListener(configMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
