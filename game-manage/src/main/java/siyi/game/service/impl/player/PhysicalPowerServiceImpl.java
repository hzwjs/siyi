package siyi.game.service.impl.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.PhysicalPowerMapper;
import siyi.game.dao.entity.PhysicalPower;
import siyi.game.service.player.PhysicalPowerService;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
@Service
public class PhysicalPowerServiceImpl implements PhysicalPowerService {
    @Autowired
    PhysicalPowerMapper physicalPowerMapper;

    @Override
    public void deduct(String playerId, int hpNum) {
        PhysicalPower physicalPower = new PhysicalPower();
        physicalPower.setPlayerId(playerId);
        physicalPower = physicalPowerMapper.selectOne(physicalPower);
        if (physicalPower.getHp() == physicalPower.getMaxHp()) {
            physicalPower.setUpdatedTime(new Date());
        }
        int hp = physicalPower.getHp() - hpNum;
        if (hp >= 0) {
            physicalPower.setHp(hp);
            physicalPowerMapper.updateByPrimaryKey(physicalPower);
        }

    }

    @Override
    public int calculateHp(String playerId) {
        PhysicalPower physicalPower = new PhysicalPower();
        physicalPower.setPlayerId(playerId);
        physicalPower = physicalPowerMapper.selectOne(physicalPower);
        Instant startTime = physicalPower.getUpdatedTime().toInstant();
        Instant currentTime = Instant.now();
        long minute = Duration.between(startTime, currentTime).toMinutes();
        int addHpNum = (int) (minute/5); // 每五分钟恢复1点体力
        addHp(playerId, addHpNum);
        physicalPower = physicalPowerMapper.selectOne(physicalPower);
        return physicalPower.getHp();
    }

    @Override
    public void addHp(String playerId, int hpNum) {
        if (hpNum > 0) {
            PhysicalPower physicalPower = new PhysicalPower();
            physicalPower.setPlayerId(playerId);
            physicalPower = physicalPowerMapper.selectOne(physicalPower);
            int hp = physicalPower.getHp() + hpNum;
            if (hp > physicalPower.getMaxHp()) {
                hp = physicalPower.getMaxHp();
                physicalPower.setUpdatedTime(null);
            }
            physicalPower.setHp(hp);
        }
    }
}
