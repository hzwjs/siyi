package siyi.game.service.gamelevel;

import siyi.game.bo.gamelevel.GameLevel;

/**
 * 与关卡相关的服务类
 * @Author hzw
 * @Date 2020=02-28
 */
public interface GameLevelService {

    GameLevel queryGameLevelInfo(String userId);

    GameLevel queryWenGameLevelInfo(String userId, String preQType, String preQID, String preStatus);

    /**
     * description: 获取武关关卡信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 14:37 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return siyi.game.bo.gamelevel.GameLevel
     */
    GameLevel queryWuGameLevelInfo(String qType);
}
