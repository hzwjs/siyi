package siyi.game.service.gamelevel;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import siyi.game.bo.GameLevel;

/**
 * 与关卡相关的服务类
 * @Author hzw
 * @Date 2020=02-28
 */
public interface GameLevelService {

    GameLevel queryGameLevelInfo(String userId);

}
