package siyi.game.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "tb_qu_xuanze")
public class QuXuanze implements Serializable {
    /**
     * 题目ID
     */
    @Column(name = "qu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String quId;

    /**
     * 状态
     */
    @Column(name = "qu_status")
    private String quStatus;

    /**
     * 选项1
     */
    private String option1;

    /**
     * 选项2
     */
    private String option2;

    /**
     * 选项3
     */
    private String option3;

    /**
     * 选项4
     */
    private String option4;

    /**
     * 选项5
     */
    private String option5;

    /**
     * 选项6
     */
    private String option6;

    /**
     * 答案
     */
    private String answer;

    /**
     * 提示
     */
    private String tips;

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