package siyi.game.service.player;

import siyi.game.dao.entity.Player;

import java.util.List;
import java.util.Map;

/**
 * description: PlayerService <br>
 * date: 2020/2/26 22:08 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface PlayerService {

    /**
     * description: 通过playerId查询玩家信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/26 22:16 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @return siyi.game.dao.entity.Player
     */
    Player selectByPlayerId(String playerId);

    /**
     * description: 新增玩家信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/26 22:23 <br>
     * author: zhengzhiqiang <br>
     *
     * @param player
     * @return void
     */
    void insertSelective(Player player);

    /**
     * description: 分页获取玩家信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/26 23:14 <br>
     * author: zhengzhiqiang <br>
     *
     * @param player
     * @param pageNum
     * @param pageSize
     * @return java.util.List<siyi.game.dao.entity.Player>
     */
    List<Player> selectPlayerPageInfo(Player player, int pageNum, int pageSize);

    /**
     * description: 通过游戏编码查询玩家地域统计信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/27 22:55 <br>
     * author: zhengzhiqiang <br>
     *
     * @param gameCode
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> selectAreaReportByGameCode(String gameCode);

    /**
     * description: 根据游戏编码计算该游戏玩家人数 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 0:51 <br>
     * author: zhengzhiqiang <br>
     *
     * @param gameCode
     * @return long
     */
    long selectCountByGameCode(String gameCode);

    /**
     * description: 根据平台ID查询玩家信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/7 22:27 <br>
     * author: zhengzhiqiang <br>
     *
     * @param platformId
     * @return siyi.game.dao.entity.Player
     */
    Player selectByPlatFormId(String platformId);
}
