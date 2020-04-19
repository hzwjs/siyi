package siyi.game.manager.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import siyi.game.dao.entity.MessionBlank;
import siyi.game.dao.entity.PlayerMessionRelation;
import siyi.game.service.mission.MessionBlankService;
import siyi.game.service.mission.MessionConfigService;
import siyi.game.service.mission.PlayerMessionRelationService;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class MissionCompleteScheduled {

    @Autowired
    private MessionBlankService messionBlankService;

    @Autowired
    private MessionConfigService messionConfigService;

    @Autowired
    private DynamicTask dynamicTask;



    public void completeMission(Map<String, String> param){
        log.info("=== 执行任务冷却后定时任务：{}", new Date());
        String playerId = param.get("playerId");
        String blankId = param.get("blankId");
        log.info("=== playerId:{}", playerId);
        log.info("=== blankId:{}", blankId);
        // 根据玩家id查询玩家任务栏信息
        MessionBlank messionBlank = messionBlankService.selectByPlayerId(playerId);
        // 将对应任务栏置为打开状态，并生成对应任务栏的任务
        switch (blankId) {
            case "one":
                messionBlank.setBlankOneStatus("1");
                messionConfigService.createFeederMission(playerId, "one");
                break;
            case "two":
                messionBlank.setBlankTwoStatus("1");
                messionConfigService.createFeederMission(playerId, "two");
                break;
            case "three":
                messionBlank.setBlankThreeStatus("1");
                messionConfigService.createFeederMission(playerId, "three");
                break;
            case "four":
                if ("2".equals(messionBlank.getBlankFourStatus())) {
                    messionBlank.setBlankFourStatus("1");
                    messionConfigService.createFeederMission(playerId, "four");
                }
                break;
            case "five":
                if ("2".equals(messionBlank.getBlankFiveStatus())) {
                    messionBlank.setBlankFiveStatus("1");
                    messionConfigService.createFeederMission(playerId, "five");
                }
                break;
            case "six":
                if ("2".equals(messionBlank.getBlankSixStatus())) {
                    messionBlank.setBlankSixStatus("1");
                    messionConfigService.createFeederMission(playerId, "six");
                }
                break;
            default:
                break;
        }
        messionBlankService.updateByIdSelective(messionBlank);

        // 删除对应任务栏的定时任务
        dynamicTask.deleteTask(playerId + "complete" + blankId);
    }
}
