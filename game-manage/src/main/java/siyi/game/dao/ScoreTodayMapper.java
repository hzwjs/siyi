package siyi.game.dao;

import siyi.game.bo.functionbtn.WenSuccessRecord;
import siyi.game.bo.functionbtn.WuSuccessRecord;
import siyi.game.dao.entity.ScoreToday;
import tk.mybatis.mapper.common.Mapper;

import java.time.LocalDate;
import java.util.List;

public interface ScoreTodayMapper extends Mapper<ScoreToday> {
    List<WenSuccessRecord> queryWenRanking(String date);
    List<WuSuccessRecord> queryWuRanking(String date);
}