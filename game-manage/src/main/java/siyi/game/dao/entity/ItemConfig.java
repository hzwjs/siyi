package siyi.game.dao.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_item_config")
public class ItemConfig implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 启用状态 0：启用，1：停用
     */
    private String onoff;

    /**
     * 权重
     */
    private Integer quanzhong;

    /**
     * 道具名称
     */
    private String name;

    /**
     * 最大值
     */
    private Long max;

    /**
     * 冷却时间
     */
    private Long cd;

    /**
     * 道具作用
     */
    private String tips;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * 获取id
     *
     * @return id - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取启用状态 0：启用，1：停用
     *
     * @return onoff - 启用状态 0：启用，1：停用
     */
    public String getOnoff() {
        return onoff;
    }

    /**
     * 设置启用状态 0：启用，1：停用
     *
     * @param onoff 启用状态 0：启用，1：停用
     */
    public void setOnoff(String onoff) {
        this.onoff = onoff == null ? null : onoff.trim();
    }

    /**
     * 获取权重
     *
     * @return quanzhong - 权重
     */
    public Integer getQuanzhong() {
        return quanzhong;
    }

    /**
     * 设置权重
     *
     * @param quanzhong 权重
     */
    public void setQuanzhong(Integer quanzhong) {
        this.quanzhong = quanzhong;
    }

    /**
     * 获取道具名称
     *
     * @return name - 道具名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置道具名称
     *
     * @param name 道具名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取最大值
     *
     * @return max - 最大值
     */
    public Long getMax() {
        return max;
    }

    /**
     * 设置最大值
     *
     * @param max 最大值
     */
    public void setMax(Long max) {
        this.max = max;
    }

    /**
     * 获取冷却时间
     *
     * @return cd - 冷却时间
     */
    public Long getCd() {
        return cd;
    }

    /**
     * 设置冷却时间
     *
     * @param cd 冷却时间
     */
    public void setCd(Long cd) {
        this.cd = cd;
    }

    /**
     * 获取道具作用
     *
     * @return tips - 道具作用
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置道具作用
     *
     * @param tips 道具作用
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
