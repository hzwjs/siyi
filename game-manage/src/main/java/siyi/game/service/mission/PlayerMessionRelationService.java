package siyi.game.service.mission;

import siyi.game.dao.entity.PlayerMessionRelation;

import java.util.List;

/**
 * description: PlayerMessionRelationService <br>
 * date: 2020/4/2 15:36 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface PlayerMessionRelationService {

    /**
     * description: 根据玩家ID查询该玩家拥有任务关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 15:39 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @return java.util.List<siyi.game.dao.entity.PlayerMessionRelation>
     */
    List<PlayerMessionRelation> selectByPlayerId(String playerId);

    /**
     * description: 根据传入参数bean查询任务关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 15:53 <br>
     * author: zhengzhiqiang <br>
     *
     * @param selectRelation
     * @return siyi.game.dao.entity.PlayerMessionRelation
     */
    PlayerMessionRelation selectByBean(PlayerMessionRelation selectRelation);

    /**
     * description: 根据主键更新任务关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 16:59 <br>
     * author: zhengzhiqiang <br>
     *
     * @param relation
     * @return void
     */
    void updateByIdSelective(PlayerMessionRelation relation);

    /**
     * description: 玩家获取新任务 <br>
     * version: 1.0 <br>
     * date: 2020/4/6 21:12 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @param relations
     * @return void
     */
    List<PlayerMessionRelation> createNewMession(String playerId, List<PlayerMessionRelation> relations);

    /**
     * 根据玩家id查询玩家最近三次任务记录
     *
     * @param playerId
     * @return
     */
    List<PlayerMessionRelation> selectLastThreeRelationByPlayerId(String playerId);

    /**
     * 根据查询参数查询玩家任务信息
     *
     * @param selectParam
     * @return
     */
    List<PlayerMessionRelation> selectListByBean(PlayerMessionRelation selectParam);

    /**
     * 查询玩家执行中的任务
     *
     * @param playerId
     * @return
     */
    List<PlayerMessionRelation> selectExecutingMission(String playerId);

    /**
     * 插入任务关联信息
     *
     * @param relation
     */
    void insertSelective(PlayerMessionRelation relation);

    /**
     * 根据Id 删除任务关联信息
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据玩家id和任务栏id查询玩家该任务栏最后一次接受的任务
     *
     * @param playerId
     * @param blankId
     * @return
     */
    PlayerMessionRelation selectLastByPlayerIdAndBlankId(String playerId, String blankId);
}
