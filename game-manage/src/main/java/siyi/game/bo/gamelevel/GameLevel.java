package siyi.game.bo.gamelevel;

import lombok.Data;

/**
 * 关卡业务对象
 *
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
    // 选择关卡的题目
    private QuestionXuanze questionXuanze;
    // 选择关卡的答案
    private AnswerXuanze answerXuanze;
    // 选择关卡的提示
    private String tips ;
    // 对错关卡题目
    private QuestionDuicuo questionDuicuo;
    // 对错关卡答案
    private String answerDuicuo;

    // 武关配置信息
    private ConfigWu configWu;
}
