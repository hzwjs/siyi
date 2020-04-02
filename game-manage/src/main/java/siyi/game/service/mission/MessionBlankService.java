package siyi.game.service.mission;

import siyi.game.dao.entity.MessionBlank;

import java.util.List;

/**
 * description: MessionBlankService <br>
 * date: 2020/4/2 15:43 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface MessionBlankService {

    /**
     * description: 根据玩家ID查询任务栏信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 15:46 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @return java.util.List<siyi.game.dao.entity.MessionBlank>
     */
    List<MessionBlank> selectByPlayerId(String playerId);

    /**
     * description: 根据主键进行更新任务栏信息 <br>
     * version: 1.0 <br>
     * date: 2020/4/2 17:19 <br>
     * author: zhengzhiqiang <br>
     *
     * @param messionBlank
     * @return void
     */
    void updateByIdSelective(MessionBlank messionBlank);
}
