package siyi.game.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.entity.LevelClearRecord;
import siyi.game.service.gamelevel.LevelClearRecordService;

import java.util.HashMap;
import java.util.Map;

/**
 * description: LevelClearRecordController <br>
 * date: 2020/3/21 23:06 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("idiom/levelClear")
public class LevelClearRecordController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(LevelClearRecordController.class);

    @Autowired
    private LevelClearRecordService levelClearRecordService;

    /**
     * description: 玩家游戏挑战记录提交 <br>
     * version: 1.0 <br>
     * date: 2020/3/21 23:22 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId     玩家id
     * @param gameCode     游戏编码
     * @param barrierCount 挑战次数
     * @param bestScore    最好成绩
     * @return java.util.Map<java.lang.String               ,               java.lang.Object>
     */
    @PostMapping("submitRecord")
    public Map<String, Object> submitRecord(String playerId, String gameCode, String barrierCount, String bestScore) {
        // 根据玩家id与游戏编码查询玩家挑战记录
        Map<String, Object> resultMap = new HashMap<>();
        try {
            LevelClearRecord selectParam = new LevelClearRecord();
            selectParam.setPlayerId(playerId);
            selectParam.setGameCode(gameCode);
            LevelClearRecord levelClearRecord = levelClearRecordService.selectByBean(selectParam);
            if (levelClearRecord == null) {
                // 如果不存在记录，则插入记录
                LevelClearRecord insertRecord = new LevelClearRecord();
                insertRecord.setGameCode(gameCode);
                insertRecord.setPlayerId(playerId);
                insertRecord.setBarrierCount(Integer.valueOf(barrierCount));
                insertRecord.setBestScore(Integer.valueOf(bestScore));
                levelClearRecordService.insertSelective(insertRecord);
            } else {
                // 如果存在记录，则需更新记录信息，首先更新挑战次数
                levelClearRecord.setBarrierCount(Integer.valueOf(barrierCount));
                levelClearRecordService.updateByIdSelective(levelClearRecord);
                // 比较当前该角色的挑战最好成绩与传入的最好成绩，若传入的最好成绩大于当前成绩，则更新
                Integer oldScore = levelClearRecord.getBestScore();
                if (Integer.valueOf(oldScore) < Integer.valueOf(bestScore)) {
                    levelClearRecord.setBestScore(Integer.valueOf(bestScore));
                    levelClearRecordService.updateByIdSelective(levelClearRecord);
                }
            }
            getSuccessResult(resultMap);
            return resultMap;
        } catch (NumberFormatException e) {
            logger.error("保存挑战记录信息失败：{}", e);
            getFailResult(resultMap, "提交异常");
            return resultMap;
        }
    }

    /**
     * description: 查询玩家挑战记录信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/21 23:52 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId 玩家id
     * @param gameCode 游戏编码
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @GetMapping("selectRecord")
    public Map<String, Object> selectRecord(String playerId, String gameCode) {
        Map<String, Object> resultMap = new HashMap<>();
        LevelClearRecord selectParam = new LevelClearRecord();
        selectParam.setPlayerId(playerId);
        selectParam.setGameCode(gameCode);
        LevelClearRecord levelClearRecord = levelClearRecordService.selectByBean(selectParam);
        resultMap.put("levelRecord", levelClearRecord);
        return resultMap;
    }

}
