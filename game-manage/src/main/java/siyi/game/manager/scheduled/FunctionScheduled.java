package siyi.game.manager.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import siyi.game.dao.PlayerMapper;

@Slf4j
@Component
public class FunctionScheduled {
    @Autowired
    private PlayerMapper playerMapper;

    /**
     * 每天12点执行，给玩家增加一次抽奖机会
     */
    @Scheduled(cron = "0 59 23 * * *")
    private void addLotteryNum() {
        playerMapper.updateLotteryNumBatch();
    }
}
