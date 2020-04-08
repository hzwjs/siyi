package siyi.game.service.fuctionbtn;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 功能按钮服务类
 */
public interface FunctionService {

    /**
     * @param playerId 玩家id
     * @param flag  是否更新抽奖次数的标记
     * @return
     */
    Map getLotteryInfo(String playerId, boolean flag);
}
