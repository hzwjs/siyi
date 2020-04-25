package siyi.game.service.player;

/**
 * 体力服务类
 */
public interface PhysicalPowerService {
    /**
     * 扣除体力
     * @param playerId
     * @param hpNum
     */
    void deduct(String playerId, int hpNum);

    /**
     * 根据时间恢复体力
     */
    int calculateHp(String playerId);

    /**
     * 增加体力（使用体力点卡的时候会涉及）
     * @param playerId
     * @param hpNum
     */
    void addHp(String playerId, int hpNum);

}
