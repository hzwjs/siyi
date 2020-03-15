package siyi.game.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "tb_user_question")
public class UserQuestion implements Serializable {
    /**
     * 流水号
     */
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

}