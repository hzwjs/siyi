package siyi.game.service.player;

/**
 * 体力服务类
 */
public interface PhysicalPowerService {
    /**
     * 扣除体力
     * @param hpNum
     */
    void deduct(int hpNum);

    /**
     * 根据时间恢复体力
     */
    void recover();

    /**
     * 增加体力（使用体力点卡的时候会涉及）
     * @param hpNum
     */
    void addHp(int hpNum);

}
