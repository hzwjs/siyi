package siyi.game.service.mission;

import siyi.game.dao.entity.PlayerMessionRelation;

import java.util.List;

/**
 * description: MessionConfigService <br>
 * date: 2020/4/2 11:46 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface MessionConfigService {

    /**
     * 玩家创建新任务
     *
     * @param playerId
     * @param relations
     * @return
     */
    List<PlayerMessionRelation> createNewMession(String playerId, List<PlayerMessionRelation> relations);

    /**
     * 创建新支线任务
     *
     * @param playerId
     */
    void createFeederMission(String playerId);

    /**
     * 创建新支线任务，指定任务栏
     *
     * @param playerId
     * @param blankId
     */
    PlayerMessionRelation createFeederMission(String playerId, String blankId);
}
