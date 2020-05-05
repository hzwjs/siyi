package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.bo.functionbtn.TiantiRanking;
import siyi.game.dao.entity.LevelClearRecord;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LevelClearRecordMapper extends Mapper<LevelClearRecord> {
    List<TiantiRanking> selectTiantiRanking();
}
