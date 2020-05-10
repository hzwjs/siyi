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
        } else {
            physicalPower.setHp(0);
        }
        physicalPowerMapper.updateByPrimaryKeySelective(physicalPower);

    }

    @Override
    public int calculateHp(String playerId) {
        PhysicalPower physicalPower = new PhysicalPower();
        physicalPower.setPlayerId(playerId);
        physicalPower = physicalPowerMapper.selectOne(physicalPower);
        Instant startTime = physicalPower.getUpdatedTime().toInstant();
        if (startTime == null) { // 表示当前是满体力
            return physicalPower.getHp();
        } else {
            Instant currentTime = Instant.now();
            long minute = Duration.between(startTime, currentTime).toMinutes();
            int addHpNum = (int) (minute/5); // 每五分钟恢复1点体力
            physicalPower = addHp(playerId, addHpNum, physicalPower);
        }
        return physicalPower.getHp();
    }

    @Override
    public PhysicalPower addHp(String playerId, int hpNum, PhysicalPower physicalPower) {
        if (hpNum > 0) {
            physicalPower.setPlayerId(playerId);
            physicalPower = physicalPowerMapper.selectOne(physicalPower);
            if (physicalPower != null) {
                int hp = physicalPower.getHp() + hpNum;
                if (hp > physicalPower.getMaxHp()) {
                    hp = physicalPower.getMaxHp();
                    physicalPower.setUpdatedTime(null);
                }
                physicalPower.setHp(hp);
                physicalPowerMapper.updateByPrimaryKeySelective(physicalPower);
            }
        }
        return physicalPower;
    }
}
