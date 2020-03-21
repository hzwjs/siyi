package siyi.game.service.impl.gamelevel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.LevelClearRecordMapper;
import siyi.game.dao.entity.LevelClearRecord;
import siyi.game.service.gamelevel.LevelClearRecordService;

/**
 * description: LevelClearRecordServiceImpl <br>
 * date: 2020/3/21 23:11 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class LevelClearRecordServiceImpl implements LevelClearRecordService {

    @Autowired
    private LevelClearRecordMapper levelClearRecordMapper;

    @Override
    public LevelClearRecord selectByBean(LevelClearRecord selectParam) {
        return levelClearRecordMapper.selectOne(selectParam);
    }

    @Override
    public void insertSelective(LevelClearRecord insertRecord) {
        levelClearRecordMapper.insertSelective(insertRecord);
    }

    @Override
    public void updateByIdSelective(LevelClearRecord levelClearRecord) {
        levelClearRecordMapper.updateByPrimaryKeySelective(levelClearRecord);
    }
}
