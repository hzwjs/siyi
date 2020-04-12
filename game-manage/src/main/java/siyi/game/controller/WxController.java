package siyi.game.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import siyi.game.bo.TaskInfo;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.entity.Player;
import siyi.game.manager.scheduled.DynamicTask;
import siyi.game.service.wx.WxService;
import siyi.game.utill.CacheClass;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供微信相关的接口
 */
@Slf4j
@RestController
@RequestMapping("idiom/wx")
public class WxController extends BaseController{
    @Autowired
    DynamicTask dynamicTask;

    @RequestMapping("addTask")
    @ResponseBody
    public void addTask() {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskName("task1");
        taskInfo.setCron("0/15 * * * * ? ");
        taskInfo.setClassName("siyi.game.manager.scheduled.WxScheduled");
        taskInfo.setMethod("testTask");
        dynamicTask.addTask(taskInfo);
    }

    @RequestMapping("deleteTask")
    @ResponseBody
    public void removeTask() {
        dynamicTask.deleteTask("task1");
    }

}
