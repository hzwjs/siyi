package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_idiom_wrong")
public class IdiomWrong implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "WRONG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String wrongId;

    /**
     * 正确成语
     */
    @Column(name = "IDIOM")
    private String idiom;

    /**
     * 错误成语
     */
    @Column(name = "WRONG_IDIOM")
    private String wrongIdiom;

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
     * 获取ID
     *
     * @return WRONG_ID - ID
     */
    public String getWrongId() {
        return wrongId;
    }

    /**
     * 设置ID
     *
     * @param wrongId ID
     */
    public void setWrongId(String wrongId) {
        this.wrongId = wrongId == null ? null : wrongId.trim();
    }

    /**
     * 获取错误成语
     *
     * @return WRONG_IDIOM - 错误成语
     */
    public String getWrongIdiom() {
        return wrongIdiom;
    }

    /**
     * 设置错误成语
     *
     * @param wrongIdiom 错误成语
     */
    public void setWrongIdiom(String wrongIdiom) {
        this.wrongIdiom = wrongIdiom == null ? null : wrongIdiom.trim();
    }

    /**
     * 获取正确成语
     *
     * @return IDIOM - 正确成语
     */
    public String getIdiom() {
        return idiom;
    }

    /**
     * 设置正确成语
     *
     * @param idiom 正确成语
     */
    public void setIdiom(String idiom) {
        this.idiom = idiom == null ? null : idiom.trim();
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