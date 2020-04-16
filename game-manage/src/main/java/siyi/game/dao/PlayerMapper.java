package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.Player;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayerMapper extends Mapper<Player> {

    /**
     * description: 根据游戏编码查询玩家的地域统计信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/27 22:56 <br>
     * author: zhengzhiqiang <br>
     *
     * @param gameCode
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String, Object>> selectAreaReportByGameCode(String gameCode);

    /**
     * description: 根据游戏编码计算游戏玩家数量 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 0:53 <br>
     * author: zhengzhiqiang <br>
     *
     * @param gameCode
     * @return long
     */
    long selectCountByGameCode(String gameCode);

    /**
     * 晚间定时任务自动更新抽奖机会
     */
    void updateLotteryNumBatch();
}
