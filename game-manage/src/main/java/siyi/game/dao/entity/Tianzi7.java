package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_tianzi7")
public class Tianzi7 implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String questionId;

    /**
     * 第一个字
     */
    @Column(name = "ITEM1")
    private String item1;

    /**
     * 第二个字
     */
    @Column(name = "ITEM2")
    private String item2;

    /**
     * 第三个字
     */
    @Column(name = "ITEM3")
    private String item3;

    /**
     * 第四个字
     */
    @Column(name = "ITEM4")
    private String item4;

    /**
     * 第五个字
     */
    @Column(name = "ITEM5")
    private String item5;

    /**
     * 第六个字
     */
    @Column(name = "ITEM6")
    private String item6;

    /**
     * 第七个字
     */
    @Column(name = "ITEM7")
    private String item7;

    /**
     * 空缺的数量
     */
    @Column(name = "EMPTY_NUM")
    private String emptyNum;

    /**
     * 空缺的权重
     */
    @Column(name = "EMPTY_NUM_RATIO")
    private String emptyNumRatio;

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
     * 获取id
     *
     * @return QUESTION_ID - id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置id
     *
     * @param questionId id
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    /**
     * 获取第一个字
     *
     * @return ITEM1 - 第一个字
     */
    public String getItem1() {
        return item1;
    }

    /**
     * 设置第一个字
     *
     * @param item1 第一个字
     */
    public void setItem1(String item1) {
        this.item1 = item1 == null ? null : item1.trim();
    }

    /**
     * 获取第二个字
     *
     * @return ITEM2 - 第二个字
     */
    public String getItem2() {
        return item2;
    }

    /**
     * 设置第二个字
     *
     * @param item2 第二个字
     */
    public void setItem2(String item2) {
        this.item2 = item2 == null ? null : item2.trim();
    }

    /**
     * 获取第三个字
     *
     * @return ITEM3 - 第三个字
     */
    public String getItem3() {
        return item3;
    }

    /**
     * 设置第三个字
     *
     * @param item3 第三个字
     */
    public void setItem3(String item3) {
        this.item3 = item3 == null ? null : item3.trim();
    }

    /**
     * 获取第四个字
     *
     * @return ITEM4 - 第四个字
     */
    public String getItem4() {
        return item4;
    }

    /**
     * 设置第四个字
     *
     * @param item4 第四个字
     */
    public void setItem4(String item4) {
        this.item4 = item4 == null ? null : item4.trim();
    }

    /**
     * 获取第五个字
     *
     * @return ITEM5 - 第五个字
     */
    public String getItem5() {
        return item5;
    }

    /**
     * 设置第五个字
     *
     * @param item5 第五个字
     */
    public void setItem5(String item5) {
        this.item5 = item5 == null ? null : item5.trim();
    }

    /**
     * 获取第六个字
     *
     * @return ITEM6 - 第六个字
     */
    public String getItem6() {
        return item6;
    }

    /**
     * 设置第六个字
     *
     * @param item6 第六个字
     */
    public void setItem6(String item6) {
        this.item6 = item6 == null ? null : item6.trim();
    }

    /**
     * 获取第七个字
     *
     * @return ITEM7 - 第七个字
     */
    public String getItem7() {
        return item7;
    }

    /**
     * 设置第七个字
     *
     * @param item7 第七个字
     */
    public void setItem7(String item7) {
        this.item7 = item7 == null ? null : item7.trim();
    }

    /**
     * 获取空缺的数量
     *
     * @return EMPTY_NUM - 空缺的数量
     */
    public String getEmptyNum() {
        return emptyNum;
    }

    /**
     * 设置空缺的数量
     *
     * @param emptyNum 空缺的数量
     */
    public void setEmptyNum(String emptyNum) {
        this.emptyNum = emptyNum == null ? null : emptyNum.trim();
    }

    /**
     * 获取空缺的权重
     *
     * @return EMPTY_NUM_RATIO - 空缺的权重
     */
    public String getEmptyNumRatio() {
        return emptyNumRatio;
    }

    /**
     * 设置空缺的权重
     *
     * @param emptyNumRatio 空缺的权重
     */
    public void setEmptyNumRatio(String emptyNumRatio) {
        this.emptyNumRatio = emptyNumRatio == null ? null : emptyNumRatio.trim();
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