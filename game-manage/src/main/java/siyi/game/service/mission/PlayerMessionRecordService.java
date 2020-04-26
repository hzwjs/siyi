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

    /**
     * 根据玩家id、任务id查询任务记录信息，根据id倒序排列
     *
     * @param playerId
     * @param messionId
     * @return
     */
    List<PlayerMessionRecord> selectByPlayerIdAndMessionId(String playerId, String messionId);

    /**
     * 根据主键更新任务信息
     *
     * @param record
     */
    void updateByIdSelective(PlayerMessionRecord record);

    /**
     * 根据玩家id、任务栏id查询任务记录信息，id倒叙排列
     *
     * @param playerId
     * @param blankId
     * @return
     */
    List<PlayerMessionRecord> selectByPlayerIdAndBlankId(String playerId, String blankId);
}
