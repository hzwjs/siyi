package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.*;
import siyi.game.dao.entity.*;
import siyi.game.manager.excel.read.*;

import java.io.File;
import java.net.URISyntaxException;

@RestController
@RequestMapping("idiom/file/analysis")
public class FileAnalysisController {
    private static String fileDir = "";
    static {
        try {
            fileDir = ClassLoader.getSystemResource("").toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    GamelevelConfigMapper gamelevelConfigMapper;
    @Autowired
    QuTianziMapper quTianziMapper;
    @Autowired
    IdiomMapper idiomMapper;
    @Autowired
    IdiomWrongMapper idiomWrongMapper;
    @Autowired
    Tianzi4Mapper  tianzi4Mapper;

    @RequestMapping("guanqiaConfig")
    public void guanqiaConfigAnalysis() {
        try {
            String filePath = fileDir + "biz_config" + File.separator + "guanqia20200322.xlsx";
            EasyExcel.read(filePath, GamelevelConfig.class, new GameLevelConfigDataListener(gamelevelConfigMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("tianzi")
    public void tianziAnalysis() {
        try {
            String filePath = fileDir + "biz_config" + File.separator + "Q_tianzi.xlsx";
            EasyExcel.read(filePath, QuTianzi.class, new QuTianziDataListener(quTianziMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("duicuo2")
    public void duicuo2Analysis() {
        try {
            String filePath = fileDir + "biz_config" + File.separator + "Q_duicuo2.xlsx";
            EasyExcel.read(filePath, IdiomWrong.class, new QuDuicuo2DataListener(idiomWrongMapper, idiomMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("tianzi4")
    public void tianzi4Analysis() {
        try {
            String filePath = fileDir + "biz_config" + File.separator + "Q_tianzi4.xlsx";
            EasyExcel.read(filePath, Tianzi4.class, new QuTianzi4DataListener(tianzi4Mapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
