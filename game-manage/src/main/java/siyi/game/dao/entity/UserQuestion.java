package siyi.game.dao.entity;

import tk.mybatis.mapper.annotation.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_user_question")
public class UserQuestion implements Serializable {
    /**
     * 流水号
     */
    @Order(value = "DESC")
    @Id
    @Column(name = "serie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serieId;

    /**
     * 玩家ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 题目编号
     */
    @Column(name = "question_id")
    private String questionId;

    /**
     * 题目类型
     */
    @Column(name = "question_type")
    private String questionType;

    /**
     * 答题次数
     */
    @Column(name = "answer_num")
    private Integer answerNum;

    /**
     * 答题成功次数
     */
    @Column(name = "answer_success_num")
    private Integer answerSuccessNum;

    /**
     * 答题失败次数
     */
    @Column(name = "answer_fail_num")
    private Integer answerFailNum;

    /**
     * 状态：0-成功；1-失败
     */
    private String status;

    /**
     * 乐观锁
     */
    @Column(name = "REVISION")
    private Integer revision;

    /**
     * 创建人
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取流水号
     *
     * @return serie_id - 流水号
     */
    public Integer getSerieId() {
        return serieId;
    }

    /**
     * 设置流水号
     *
     * @param serieId 流水号
     */
    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    /**
     * 获取玩家ID
     *
     * @return user_id - 玩家ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置玩家ID
     *
     * @param userId 玩家ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取题目编号
     *
     * @return questioin_id - 题目编号
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置题目编号
     *
     * @param questionId 题目编号
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    /**
     * 获取题目类型
     *
     * @return question_type - 题目类型
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * 设置题目类型
     *
     * @param questionType 题目类型
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    /**
     * 获取答题次数
     *
     * @return answer_num - 答题次数
     */
    public Integer getAnswerNum() {
        return answerNum;
    }

    /**
     * 设置答题次数
     *
     * @param answerNum 答题次数
     */
    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    /**
     * 获取答题成功次数
     *
     * @return answer_success_num - 答题成功次数
     */
    public Integer getAnswerSuccessNum() {
        return answerSuccessNum;
    }

    /**
     * 设置答题成功次数
     *
     * @param answerSuccessNum 答题成功次数
     */
    public void setAnswerSuccessNum(Integer answerSuccessNum) {
        this.answerSuccessNum = answerSuccessNum;
    }

    /**
     * 获取答题失败次数
     *
     * @return answer_fail_num - 答题失败次数
     */
    public Integer getAnswerFailNum() {
        return answerFailNum;
    }

    /**
     * 设置答题失败次数
     *
     * @param answerFailNum 答题失败次数
     */
    public void setAnswerFailNum(Integer answerFailNum) {
        this.answerFailNum = answerFailNum;
    }

    /**
     * 获取状态：0-成功；1-失败
     *
     * @return status - 状态：0-成功；1-失败
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-成功；1-失败
     *
     * @param status 状态：0-成功；1-失败
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取乐观锁
     *
     * @return REVISION - 乐观锁
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * 设置乐观锁
     *
     * @param revision 乐观锁
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * 获取创建人
     *
     * @return CREATED_BY - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CREATED_TIME - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新人
     *
     * @return UPDATED_BY - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * 获取更新时间
     *
     * @return UPDATED_TIME - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}