package siyi.game.service.gamelevel;

import siyi.game.bo.gamelevel.GameLevel;

/**
 * 与关卡相关的服务类
 * @Author hzw
 * @Date 2020=02-28
 */
public interface GameLevelService {

    GameLevel queryGameLevelInfo(String userId);

}
