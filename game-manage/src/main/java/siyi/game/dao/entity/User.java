package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_user")
public class User implements Serializable {
    /**
     * 主键，自增
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名称
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "USER_PASSWORD")
    private String userPassword;

    /**
     * 盐
     */
    @Column(name = "SALT")
    private String salt;

    /**
     * 是否删除：0：否，1：是
     */
    @Column(name = "IS_DELETED")
    private String isDeleted;

    /**
     * 用户状态：0：启用，1：停用
     */
    @Column(name = "USER_STATUS")
    private String userStatus;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 邮箱
     */
    @Column(name = "USER_EMAIL")
    private String userEmail;

    /**
     * 手机号
     */
    @Column(name = "USER_PHONE")
    private String userPhone;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键，自增
     *
     * @return ID - 主键，自增
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键，自增
     *
     * @param id 主键，自增
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名称
     *
     * @return USER_NAME - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户密码
     *
     * @return USER_PASSWORD - 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户密码
     *
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 获取盐
     *
     * @return SALT - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取是否删除：0：否，1：是
     *
     * @return IS_DELETED - 是否删除：0：否，1：是
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除：0：否，1：是
     *
     * @param isDeleted 是否删除：0：否，1：是
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * 获取用户状态：0：启用，1：停用
     *
     * @return USER_STATUS - 用户状态：0：启用，1：停用
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态：0：启用，1：停用
     *
     * @param userStatus 用户状态：0：启用，1：停用
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取邮箱
     *
     * @return USER_EMAIL - 邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置邮箱
     *
     * @param userEmail 邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * 获取手机号
     *
     * @return USER_PHONE - 手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置手机号
     *
     * @param userPhone 手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }
}