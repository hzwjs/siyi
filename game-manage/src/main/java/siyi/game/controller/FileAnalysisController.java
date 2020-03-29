package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.*;
import siyi.game.dao.entity.*;
import siyi.game.manager.excel.read.*;

import java.io.File;

@RestController
@RequestMapping("idiom/file/analysis")
public class FileAnalysisController {

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
    @Autowired
    Tianzi5Mapper  tianzi5Mapper;
    @Autowired
    Tianzi7Mapper  tianzi7Mapper;

    @RequestMapping("guanqiaConfig")
    public void guanqiaConfigAnalysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "guanqia20200322.xlsx";
            EasyExcel.read(filePath, GamelevelConfig.class, new GameLevelConfigDataListener(gamelevelConfigMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("tianzi")
    public void tianziAnalysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_tianzi.xlsx";
            EasyExcel.read(filePath, QuTianzi.class, new QuTianziDataListener(quTianziMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("duicuo2")
    public void duicuo2Analysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_duicuo2.xlsx";
            EasyExcel.read(filePath, IdiomWrong.class, new QuDuicuo2DataListener(idiomWrongMapper, idiomMapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("tianzi4")
    public void tianzi4Analysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_tianzi4.xlsx";
            EasyExcel.read(filePath, Tianzi4.class, new QuTianzi4DataListener(tianzi4Mapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("tianzi5")
    public void tianzi5Analysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_tianzi5.xlsx";
            EasyExcel.read(filePath, Tianzi5.class, new QuTianzi5DataListener(tianzi5Mapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("tianzi7")
    public void tianzi7Analysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Q_tianzi7.xlsx";
            EasyExcel.read(filePath, Tianzi7.class, new QuTianzi7DataListener(tianzi7Mapper)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
