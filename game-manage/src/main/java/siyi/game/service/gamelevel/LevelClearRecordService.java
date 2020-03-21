package siyi.game.service.gamelevel;

import siyi.game.dao.entity.LevelClearRecord;

/**
 * description: LevelClearRecordService <br>
 * date: 2020/3/21 23:11 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface LevelClearRecordService {

    /**
     * description: 根据传入bean查询挑战记录 <br>
     * version: 1.0 <br>
     * date: 2020/3/21 23:34 <br>
     * author: zhengzhiqiang <br>
     *
     * @param selectParam
     * @return siyi.game.dao.entity.LevelClearRecord
     */
    LevelClearRecord selectByBean(LevelClearRecord selectParam);

    /**
     * description: 插入挑战记录信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/21 23:37 <br>
     * author: zhengzhiqiang <br>
     *
     * @param insertRecord
     * @return void
     */
    void insertSelective(LevelClearRecord insertRecord);

    /**
     * description: 根据主键更新挑战记录信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/21 23:39 <br>
     * author: zhengzhiqiang <br>
     *
     * @param levelClearRecord
     * @return void
     */
    void updateByIdSelective(LevelClearRecord levelClearRecord);
}
