package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import siyi.game.bo.GameLevel;
import siyi.game.dao.QuTianziMapper;
import siyi.game.dao.entity.QuTianzi;
import siyi.game.manager.excel.read.QutianciDataListener;
import siyi.game.service.gamelevel.GameLevelService;

import java.io.File;

/**
 * description: 跟游戏关卡相关的内容，关卡配置信息查询等 <br>
 * date: 2020/2/25 11:34 <br>
 * author: hzw <br>
 * version: 1.0 <br>
 */
@Controller
@RequestMapping(value = "gameLevel")
public class GameLevelController {
    @Autowired
    private QuTianziMapper quTianziMapper;

    @Autowired
    private GameLevelService gameLevelService;

    @RequestMapping(value = "queryGameLevelInfo")
    @ResponseBody
    public GameLevel queryGameLevelInfo(){
        try {
            return gameLevelService.queryGameLevelInfo("");

//            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
//            String filePath = fileDir + "question_bank" + File.separator + "question_01.xlsx";
//            System.out.println(filePath);
//            EasyExcel.read(filePath, QuTianzi.class, new QutianciDataListener(quTianziMapper)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "fileAnalysis")
    @ResponseBody
    public void fileAnalysis(){
        try {

            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "question_bank" + File.separator + "question_01.xlsx";
            System.out.println(filePath);
            EasyExcel.read(filePath, QuTianzi.class, new QutianciDataListener(quTianziMapper)).sheet().doRead();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
