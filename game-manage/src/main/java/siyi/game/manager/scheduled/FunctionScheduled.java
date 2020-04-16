package siyi.game.manager.scheduled;

import com.github.pagehelper.PageHelper;
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
    private void getAccessToken() {
//        int size = 2;
//        int total = playerMapper.selectCount(null);
//        int pages = total/2 + (total % 2 == 0?0:1);
//        for (int i = 1; i <= pages; i++) {
//
//        }
        playerMapper.updateLotteryNumBatch();
    }
}
