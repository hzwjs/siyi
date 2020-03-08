package siyi.game.service.gamelevel;

import siyi.game.dao.entity.LevelUpConfig;

/**
 * description: LevelUpService <br>
 * date: 2020/3/8 23:48 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface LevelUpService {

    /**
     * description: 根据等级查询信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 23:52 <br>
     * author: zhengzhiqiang <br>
     *
     * @param level
     * @return siyi.game.dao.entity.LevelUpConfig
     */
    LevelUpConfig selectByLevel(String level);
}
