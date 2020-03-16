package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_k_dianji_config")
public class DianJiConfig implements Serializable {

    /**
     * 种类
     */
    private String lei;

    /**
     * 数量
     */
    private String num;

    /**
     * hp
     */
    private String hp;


    /**
     * 规则1权重
     */
    private String guize1quanzhong;

    /**
     * 规则1时间
     */
    private String time1;

    /**
     * 规则1奖励
     */
    private String jiangli1time;


    private static final long serialVersionUID = 1L;

    public String getLei() {
        return lei;
    }

    public void setLei(String lei) {
        this.lei = lei;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    /**
     * 获取hp
     *
     * @return hp - hp
     */
    public String getHp() {
        return hp;
    }

    /**
     * 设置hp
     *
     * @param hp hp
     */
    public void setHp(String hp) {
        this.hp = hp == null ? null : hp.trim();
    }


    /**
     * 获取规则1权重
     *
     * @return guize1quanzhong - 规则1权重
     */
    public String getGuize1quanzhong() {
        return guize1quanzhong;
    }

    /**
     * 设置规则1权重
     *
     * @param guize1quanzhong 规则1权重
     */
    public void setGuize1quanzhong(String guize1quanzhong) {
        this.guize1quanzhong = guize1quanzhong == null ? null : guize1quanzhong.trim();
    }

    /**
     * 获取规则1时间
     *
     * @return time1 - 规则1时间
     */
    public String getTime1() {
        return time1;
    }

    /**
     * 设置规则1时间
     *
     * @param time1 规则1时间
     */
    public void setTime1(String time1) {
        this.time1 = time1 == null ? null : time1.trim();
    }

    /**
     * 获取规则1奖励
     *
     * @return jiangli1time - 规则1奖励
     */
    public String getJiangli1time() {
        return jiangli1time;
    }

    /**
     * 设置规则1奖励
     *
     * @param jiangli1time 规则1奖励
     */
    public void setJiangli1time(String jiangli1time) {
        this.jiangli1time = jiangli1time == null ? null : jiangli1time.trim();
    }

}
