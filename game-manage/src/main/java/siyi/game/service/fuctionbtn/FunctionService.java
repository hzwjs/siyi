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

    /**
     * 保存获取到的奖励信息
     * @param playerId
     * @param itemId
     * @param num
     * @param gameCode
     * @return
     */
    boolean saveLotteryInfo(String playerId, String itemId, int num, String gameCode);
}
