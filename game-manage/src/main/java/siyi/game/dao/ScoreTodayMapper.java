package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.ScoreToday;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ScoreTodayMapper extends Mapper<ScoreToday> {
}