package siyi.game.dao;

import siyi.game.dao.entity.UserQuestion;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface UserQuestionMapper extends Mapper<UserQuestion> {
    UserQuestion queryUserCurrentQuestion(Map param);
}