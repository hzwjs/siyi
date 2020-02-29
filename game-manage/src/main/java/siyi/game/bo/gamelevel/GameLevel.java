package siyi.game.bo.gamelevel;

/**
 * 关卡业务对象
 * @auther hzw
 * @date 2020-02-25 11：55
 */
public class GameLevel {
    private String level;
    private String questionType;
    private QuestionTianzi questionTianzi;
    private CandidateWordTianzi candidate;
    private AnswerTianzi answerTianzi;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public QuestionTianzi getQuestionTianzi() {
        return questionTianzi;
    }

    public void setQuestionTianzi(QuestionTianzi questionTianzi) {
        this.questionTianzi = questionTianzi;
    }

    public AnswerTianzi getAnswerTianzi() {
        return answerTianzi;
    }

    public void setAnswerTianzi(AnswerTianzi answerTianzi) {
        this.answerTianzi = answerTianzi;
    }

    public CandidateWordTianzi getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateWordTianzi candidate) {
        this.candidate = candidate;
    }
}
