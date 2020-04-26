package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import siyi.game.bo.TaskInfo;
import siyi.game.bo.gamelevel.MissionItem;
import siyi.game.dao.MessionConfigMapper;
import siyi.game.dao.entity.*;
import siyi.game.manager.excel.read.MessionConfigDataListener;
import siyi.game.manager.scheduled.DynamicTask;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.service.mission.MessionBlankService;
import siyi.game.service.mission.MessionConfigService;
import siyi.game.service.mission.PlayerMessionRecordService;
import siyi.game.service.mission.PlayerMessionRelationService;
import siyi.game.service.player.PlayerService;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: MessionConfigController 任务相关接口服务 <br>
 * date: 2020/4/2 11:44 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("idiom/mession")
public class MessionConfigController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessionConfigController.class);

    @Autowired
    private MessionConfigService messionConfigService;

    @Autowired
    private MessionConfigMapper messionConfigMapper;

    @Autowired
    private PlayerMessionRelationService playerMessionRelationService;

    @Autowired
    private MessionBlankService messionBlankService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;

    @Autowired
    private DynamicTask dynamicTask;

    @Autowired
    private PlayerMessionRecordService playerMessionRecordService;

    /**
     * description: 解析任务配置文件 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 15:31 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return void
     */
    @GetMapping(value = "fileAnalysis")
    public void fileAnalysis() {
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "Quest.xlsx";
            EasyExcel.read(filePath, MessionConfig.class, new MessionConfigDataListener(messionConfigMapper)).sheet().doRead();

        } catch (Exception e) {
            LOGGER.error("读取道具配置信息错误：{}", e);
        }
    }

    /**
     * description: 获取玩家任务信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 15:41 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @return java.util.List<siyi.game.dao.entity.PlayerMessionRelation>
     */
    @GetMapping("getPlayerMession")
    public List<PlayerMessionRelation> getPlayerMession(String playerId) {
        return playerMessionRelationService.selectByPlayerId(playerId);
    }

    /**
     * description: 查询玩家任务栏信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 15:45 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @return java.util.List<siyi.game.dao.entity.MessionBlank>
     */
    @GetMapping("getMessionBlank")
    public MessionBlank getMessionBlank(String playerId) {
        MessionBlank findMessionBlank = messionBlankService.selectByPlayerId(playerId);
        if (findMessionBlank == null) {
            MessionBlank messionBlank = new MessionBlank();
            messionBlank.setPlayerId(playerId);
            messionBlankService.insertSelective(messionBlank);
        }
        return messionBlankService.selectByPlayerId(playerId);
    }

    /**
     * description: 更新游戏任务进度 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 17:01 <br>
     * author: zhengzhiqiang <br>
     *
     * @param messionList
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @PostMapping("updateProcess")
    public Map<String, Object> updateProcess(@RequestBody List<MissionItem> messionList) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            LOGGER.info("开始更新玩家任务进度，方法入参：{}", messionList.toString());
            for (MissionItem missionItem : messionList) {
                String missionId = missionItem.getMessionId();
                String playerId = missionItem.getPlayerId();
                String processNum = missionItem.getProcessNum();
                PlayerMessionRelation selectRelation = new PlayerMessionRelation();
                selectRelation.setPlayerId(playerId);
                selectRelation.setMessionId(missionId);
                PlayerMessionRelation relation = playerMessionRelationService.selectByBean(selectRelation);
                if (relation == null) {
                    resultMap.put("errCode", "000010");
                    resultMap.put("errMsg", "该玩家无任务信息");
                    return resultMap;
                }
                LOGGER.info("获取玩家对应任务关系：{}", relation.toString());
                String target = relation.getTarget();
                String process = relation.getProcess();
                Integer targetInt = Integer.valueOf(target);
                Integer processInt = Integer.valueOf(process);
                Integer addProcessInt = Integer.valueOf(processNum);
                // 进度赋值，当总进度大于任务目标时，总进度等于任务目标数
                if (processInt + addProcessInt > targetInt) {
                    process = target;
                } else {
                    // 若总进度小于等于任务目标，则总进度为原进度数量 + 新进度数量
                    process = String.valueOf(processInt + addProcessInt);
                }
                relation.setProcess(process);
                // 如果任务进度等于任务目标，则完成状态为已完成
                if (Integer.valueOf(process) == Integer.valueOf(target)) {
                    relation.setCompleteStatus("1");
                }
                playerMessionRelationService.updateByIdSelective(relation);
            }

            resultMap.put("errCode", "000000");
            resultMap.put("errMsg", "更新成功");
            return resultMap;
        } catch (Exception e) {
            LOGGER.error("更新任务进度异常：{}", e);
            resultMap.put("errCode", "999999");
            resultMap.put("errMsg", "更新任务进度异常");
            return resultMap;
        }
    }

    /**
     * description: 刷新任务 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 17:09 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @param messionId
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("fresh")
    public Map<String, Object> freshMession(String playerId, String messionId) {
        Map<String, Object> resultMap = new HashMap<>();
        // 获取对应任务关系
        PlayerMessionRelation selectRelation = new PlayerMessionRelation();
        selectRelation.setPlayerId(playerId);
        selectRelation.setMessionId(messionId);
        selectRelation.setCompleteStatus("0");
        PlayerMessionRelation relation = playerMessionRelationService.selectByBean(selectRelation);
        if (relation == null) {
            resultMap.put("errCode", "000010");
            resultMap.put("errMsg", "玩家不存在该任务信息");
            return resultMap;
        }
        // 该任务所属任务栏
        String blankId = relation.getBlankId();
        // 删除原有任务
        playerMessionRelationService.deleteById(relation.getId());
        // 获取新任务，指定任务栏
        PlayerMessionRelation feederMission = messionConfigService.createFeederMission(playerId, blankId);
        resultMap.put("newMission", feederMission);
        return resultMap;
    }

    /**
     * description: 解锁任务栏 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 17:15 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("unLock")
    public Map<String, Object> unLockBlank(String playerId, String blankId) {
        LOGGER.info("开始解锁任务栏{}", new Date());
        Map<String, Object> resultMap = new HashMap<>();
        MessionBlank messionBlank = messionBlankService.selectByPlayerId(playerId);
        if ("four".equals(blankId)) {
            messionBlank.setBlankFourStatus("1");
        } else if ("five".equals(blankId)) {
            messionBlank.setBlankFiveStatus("1");
        } else if ("six".equals(blankId)) {
            messionBlank.setBlankSixStatus("1");
        }
        messionBlankService.updateByIdSelective(messionBlank);
        // 定义定时任务，将对应任务栏置为锁定状态
        Map<String, String> param = new HashMap<>();
        param.put("playerId", playerId);
        param.put("blankId", blankId);
        TaskInfo taskInfo = new TaskInfo();
        String taskName = playerId + blankId;
        taskInfo.setTaskName(taskName);
        taskInfo.setDelay(59000L);
        taskInfo.setClassName("siyi.game.manager.scheduled.MissionScheduled");
        taskInfo.setMethod("unlockMissionBlank");
        dynamicTask.addTask(taskInfo, param);
        PlayerMessionRelation feederMission = messionConfigService.createFeederMission(playerId, blankId);
        resultMap.put("errCode", "000000");
        resultMap.put("newMission", feederMission);
        return resultMap;
    }

    /**
     * 完成任务
     *
     * @param playerId
     * @param messionId
     * @return
     */
    @PostMapping("complete")
    public Map<String, Object> completeMession(String playerId, String messionId) {
        Map<String, Object> result = new HashMap<>();
        PlayerMessionRelation selectParam = new PlayerMessionRelation();
        selectParam.setPlayerId(playerId);
        selectParam.setMessionId(messionId);
        selectParam.setCompleteStatus("1");
        List<PlayerMessionRelation> list = playerMessionRelationService.selectListByBean(selectParam);
        if (CollectionUtils.isEmpty(list)) {
            getFailResult(result, "玩家无该任务信息");
            return result;
        }
        // 根据id进行倒序排序
        list = list.stream()
                .sorted(Comparator.comparing(PlayerMessionRelation::getId).reversed())
                .collect(Collectors.toList());
        // 取出最新一条任务信息
        PlayerMessionRelation playerMessionRelation = list.get(0);
        String exp = playerMessionRelation.getExp();
        String gold = playerMessionRelation.getGold();
        String isItem = playerMessionRelation.getIsItem();
        if ("1".equals(isItem)) {
            // 包含道具奖励，需更新玩家道具信息
            String itemId = playerMessionRelation.getItemId();
            String itemNum = playerMessionRelation.getItemNum();
            ItemPlayerRelation selectRelation = new ItemPlayerRelation();
            selectRelation.setPlayerId(playerId);
            selectRelation.setItemNo(itemId);
            ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectRelation);
            if (itemPlayerRelation == null) {
                // 玩家没有该道具，则新增关联关系
                itemPlayerRelation = new ItemPlayerRelation();
                itemPlayerRelation.setItemNo(itemId);
                itemPlayerRelation.setPlayerId(playerId);
                itemPlayerRelation.setGameCode("game001");
                itemPlayerRelation.setQuantity(itemNum);
                itemPlayerRelationService.insertSelective(itemPlayerRelation);
            } else {
                // 玩家有该道具，更新该道具数量
                String quantity = itemPlayerRelation.getQuantity();
                long totalNum = Long.parseLong(quantity) + Long.parseLong(itemNum);
                itemPlayerRelation.setQuantity(String.valueOf(totalNum));
                itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
            }
        }
        // 根据玩家id进行玩家查询，更新玩家经验及金币值
        Player player = playerService.selectByPlayerId(playerId);
        String experience = player.getExperience();
        long totalExp = Long.parseLong(exp) + Long.parseLong(experience);
        player.setExperience(String.valueOf(totalExp));
        String playerGold = player.getGold();
        long totalGold = Long.parseLong(gold) + Long.parseLong(playerGold);
        player.setGold(String.valueOf(totalGold));
        playerService.updateByIdSelective(player);
        // 更新玩家任务栏状态
        String blankId = playerMessionRelation.getBlankId();
        MessionBlank messionBlank = messionBlankService.selectByPlayerId(playerId);
        switch (blankId) {
            case "one":
                messionBlank.setBlankOneStatus("2");
                break;
            case "two":
                messionBlank.setBlankTwoStatus("2");
                break;
            case "three":
                messionBlank.setBlankThreeStatus("2");
                break;
            case "four":
                messionBlank.setBlankFourStatus("2");
                break;
            case "five":
                messionBlank.setBlankFiveStatus("2");
                break;
            case "six":
                messionBlank.setBlankSixStatus("2");
                break;
        }
        messionBlankService.updateByIdSelective(messionBlank);
        // 根据玩家id、任务id查询任务记录，获取最新任务记录，更新完成时间
        List<PlayerMessionRecord> records = playerMessionRecordService.selectByPlayerIdAndMessionId(playerId, messionId);
        if (!CollectionUtils.isEmpty(records)) {
            PlayerMessionRecord record = records.get(0);
            record.setCompleteTime(new Date());
            playerMessionRecordService.updateByIdSelective(record);
        }
        // 删除任务
        playerMessionRelationService.deleteById(playerMessionRelation.getId());
        // 开启定时任务，进行任务冷却
        Map<String, String> param = new HashMap<>();
        param.put("playerId", playerId);
        param.put("blankId", blankId);
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskName(playerId + "complete" + blankId);
        taskInfo.setDelay(59000L);
        taskInfo.setClassName("siyi.game.manager.scheduled.MissionCompleteScheduled");
        taskInfo.setMethod("completeMission");
        dynamicTask.addTask(taskInfo, param);
        getSuccessResult(result);
        result.put("missionBlank", messionBlank);
        result.put("blankId", blankId);
        result.put("cd", "59");
        return result;
    }

    /**
     * 刷新任务栏
     *
     * @param playerId
     * @param blankId
     * @return
     */
    @PostMapping("freshBlank")
    public Map<String, Object> freshBlank(String playerId, String blankId) {
        // 根据玩家id查询任务栏信息
        MessionBlank messionBlank = messionBlankService.selectByPlayerId(playerId);
        // 更新对应任务栏状态
        switch (blankId) {
            case "one":
                messionBlank.setBlankOneStatus("1");
                break;
            case "two":
                messionBlank.setBlankTwoStatus("1");
                break;
            case "three":
                messionBlank.setBlankThreeStatus("1");
                break;
            case "four":
                messionBlank.setBlankFourStatus("1");
                break;
            case "five":
                messionBlank.setBlankFiveStatus("1");
                break;
            case "six":
                messionBlank.setBlankSixStatus("1");
                break;
        }
        messionBlankService.updateByIdSelective(messionBlank);
        // 生成支线任务
        messionConfigService.createFeederMission(playerId, blankId);
        // 删除对应定时任务
        dynamicTask.deleteTask(playerId + "complete" + blankId);
        Map<String, Object> result = new HashMap<>();
        getSuccessResult(result);
        return result;
    }

    /**
     * 获取任务栏cd时间，单位：秒
     *
     * @param playerId
     * @param blankId
     * @return
     */
    @GetMapping("getCd")
    public Map<String, Object> getCd(String playerId, String blankId) {
        Map<String, Object> result = new HashMap<>();
        List<PlayerMessionRecord> records = playerMessionRecordService.selectByPlayerIdAndBlankId(playerId, blankId);
        if (CollectionUtils.isEmpty(records)) {
            result.put("errCode", "111111");
            result.put("errMsg", "玩家无该任务栏记录");
            return result;
        }
        PlayerMessionRecord record = records.get(0);
        Date completeTime = record.getCompleteTime();
        Date currentTime = new Date();
        long timeSpace = currentTime.getTime() - completeTime.getTime();
        long secondSpace = timeSpace / 1000;
        result.put("cd", String.valueOf(secondSpace));
        result.put("blankId", blankId);
        return result;
    }

}
