package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.LoginLog;
import siyi.game.dao.model.LoginLogInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LoginLogMapper extends Mapper<LoginLog> {

    /**
     * description: 根据玩家姓名，玩家ID，游戏编码查询登录信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 22:11 <br>
     * author: zhengzhiqiang <br>
     *
     * @param playerId
     * @param playerName
     * @param gameCode
     * @return java.util.List<siyi.game.dao.model.LoginLogInfo>
     */
    List<LoginLogInfo> getLoginLogPageInfo(String playerId, String playerName, String gameCode);
}
