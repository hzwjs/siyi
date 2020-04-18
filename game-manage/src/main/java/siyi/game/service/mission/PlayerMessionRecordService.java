package siyi.game.service.mission;

import siyi.game.dao.entity.PlayerMessionRecord;

import java.util.List;

public interface PlayerMessionRecordService {


    /**
     * 新增任务记录信息
     *
     * @param record
     */
    void insertSelective(PlayerMessionRecord record);

    /**
     * 获取玩家最近三次任务记录
     *
     * @param playerId
     * @return
     */
    List<PlayerMessionRecord> selectLastThreeRelationByPlayerId(String playerId);

    /**
     * 根据玩家id查询任务记录
     *
     * @param playerId
     * @return
     */
    List<PlayerMessionRecord> selectByPlayerId(String playerId);
}
