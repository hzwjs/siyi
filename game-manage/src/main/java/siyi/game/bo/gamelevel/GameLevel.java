package siyi.game.bo.gamelevel;

import lombok.Data;
import siyi.game.dao.entity.GamelevelConfig;

/**
 * 关卡业务对象
 * @auther hzw
 * @date 2020-02-25 11：55
 */
@Data
public class GameLevel {
    // 当前关卡的层级
    private String level;
    // 填字关卡题目
    private QuestionTianzi questionTianzi;
    // 填字关卡待选答案关卡
    private CandidateWordTianzi candidate;
    // 填字关卡的正确答案
    private AnswerTianzi answerTianzi;
    // 文关配置信息
    private ConfigWen configWen;
}
