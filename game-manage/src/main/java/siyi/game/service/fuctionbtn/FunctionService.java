package siyi.game.service.fuctionbtn;

import siyi.game.bo.functionbtn.ItemBo;
import siyi.game.dao.entity.LevelClearRecord;
import siyi.game.dao.entity.PlayerSign;

import java.util.List;
import java.util.Map;

/**
 * 功能按钮服务类
 */
public interface FunctionService {

    /**
     * 查询玩家天梯信息
     * @param playerId
     * @return
     */
    LevelClearRecord getTiantiInfo(String playerId);

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

    /**
     * 查询背包中的所有道具
     * @param playerId
     * @return
     */
    List<ItemBo> queryItemInPacksack(String playerId, String gameCode);

    /**
     * 查询玩家签到信息
     * @param playerId
     * @return
     */
    PlayerSign querySignInfo(String playerId);

    /**
     * 提交签到信息
     * @param playerId
     * @param signDays 签到日期（包含不签到），多个日期之间用";"隔开
     * @return
     */
    boolean submitSignInfo(String playerId, String signDays);
}
