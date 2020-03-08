package siyi.game.service.impl.gamelevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.LevelUpConfigMapper;
import siyi.game.dao.entity.LevelUpConfig;
import siyi.game.service.gamelevel.LevelUpService;

/**
 * description: LevelUpServiceImpl <br>
 * date: 2020/3/8 23:50 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class LevelUpServiceImpl implements LevelUpService {

    @Autowired
    private LevelUpConfigMapper levelUpConfigMapper;

    @Override
    public LevelUpConfig selectByLevel(String level) {
        LevelUpConfig levelUpConfig = new LevelUpConfig();
        levelUpConfig.setLevel(level);
        return levelUpConfigMapper.selectOne(levelUpConfig);
    }
}
