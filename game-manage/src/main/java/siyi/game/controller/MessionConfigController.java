package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.MessionConfigMapper;
import siyi.game.dao.entity.MessionBlank;
import siyi.game.dao.entity.MessionConfig;
import siyi.game.dao.entity.PlayerMessionRelation;
import siyi.game.manager.excel.read.MessionConfigDataListener;
import siyi.game.service.mission.MessionBlankService;
import siyi.game.service.mission.MessionConfigService;
import siyi.game.service.mission.PlayerMessionRelationService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: MessionConfigController 任务相关接口服务 <br>
 * date: 2020/4/2 11:44 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("idiom/mession")
public class MessionConfigController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessionConfigController.class);

    @Autowired
    private MessionConfigService messionConfigService;

    @Autowired
    private MessionConfigMapper messionConfigMapper;

    @Autowired
    private PlayerMessionRelationService playerMessionRelationService;

    @Autowired
    private MessionBlankService messionBlankService;

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
            String filePath = fileDir + "biz_config" + File.separator + "item.xlsx";
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
        List<PlayerMessionRelation> list = playerMessionRelationService.selectLastThreeRelationByPlayerId(playerId);
        if (CollectionUtils.isEmpty(list)) {
            list = playerMessionRelationService.createNewMession(playerId, null);
        } else {
            list = playerMessionRelationService.createNewMession(playerId, list);
        }
        return list;
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
     * @param playerId
     * @param messionId
     * @param processNum
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
     */
    @PostMapping("updateProcess")
    public Map<String, Object> updateProcess(String playerId, String messionId, String processNum) {
        Map<String, Object> resultMap = new HashMap<>();
        try {

            LOGGER.info("开始更新玩家任务进度，方法入参：玩家ID：{}，任务ID：{}，任务进度数量：{}", playerId, messionId, processNum);
            PlayerMessionRelation selectRelation = new PlayerMessionRelation();
            selectRelation.setPlayerId(playerId);
            selectRelation.setMessionId(messionId);
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
     * @return java.util.Map<java.lang.String       ,       java.lang.Object>
     */
    @GetMapping("fresh")
    public Map<String, Object> freshMession(String playerId, String messionId) {
        Map<String, Object> resultMap = new HashMap<>();
        // 获取对应任务关系
        PlayerMessionRelation selectRelation = new PlayerMessionRelation();
        selectRelation.setPlayerId(playerId);
        selectRelation.setMessionId(messionId);
        PlayerMessionRelation relation = playerMessionRelationService.selectByBean(selectRelation);
        if (relation == null) {
            resultMap.put("errCode", "000010");
            resultMap.put("errMsg", "玩家不存在该任务信息");
            return resultMap;
        }
        // 该任务所属任务栏
        String blankId = relation.getBlankId();
        // TODO 获取新任务
        // TODO 将新任务保存到任务记录表，新任务与玩家关联，并设置任务栏id为原所属任务栏id
        return resultMap;
    }

    /**
     * description: 解锁任务栏 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 17:15 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.Map<java.lang.String , java.lang.Object>
     */
    @GetMapping("unLock")
    public Map<String, Object> unLockBlank(String playerId, String blankId) {
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
        resultMap.put("errCode", "000000");
        return resultMap;
    }

}
