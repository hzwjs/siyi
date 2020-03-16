package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_k_zhuiluo_config")
public class ZhuiLuoConfig implements Serializable {
    /**
     * 数量
     */
    private String num;

    /**
     * 速度
     */
    private String spend;

    /**
     * 冷却时间
     */
    private String cd;

    /**
     * hp
     */
    private String hp;

    /**
     * 规则1数量
     */
    private String guize1num;

    /**
     * 规则1权重
     */
    private String guize1quanzhong;

    /**
     * 规则1消除单个单位所需最长时间
     */
    private String time1;

    /**
     * 规则1奖励时间
     */
    private String jiangli1time;

    /**
     * 规则2数量
     */
    private String guize2num;

    /**
     * 规则2权重
     */
    private String guize2quanzhong;

    /**
     * 规则2消除单个单位所需最长时间
     */
    private String time2;

    /**
     * 规则2奖励时间
     */
    private String jiangli2time;

    /**
     * 规则3数量
     */
    private String guize3num;

    /**
     * 规则3权重
     */
    private String guize3quanzhong;

    /**
     * 规则3消除单个单位所需最长时间
     */
    private String time3;

    /**
     * 规则3奖励时间
     */
    private String jiangli3time;


    private static final long serialVersionUID = 1L;

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
     * 获取速度
     *
     * @return spend - 速度
     */
    public String getSpend() {
        return spend;
    }

    /**
     * 设置速度
     *
     * @param spend 速度
     */
    public void setSpend(String spend) {
        this.spend = spend == null ? null : spend.trim();
    }

    /**
     * 获取冷却时间
     *
     * @return cd - 冷却时间
     */
    public String getCd() {
        return cd;
    }

    /**
     * 设置冷却时间
     *
     * @param cd 冷却时间
     */
    public void setCd(String cd) {
        this.cd = cd == null ? null : cd.trim();
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
     * 获取规则1数量
     *
     * @return guize1num - 规则1数量
     */
    public String getGuize1num() {
        return guize1num;
    }

    /**
     * 设置规则1数量
     *
     * @param guize1num 规则1数量
     */
    public void setGuize1num(String guize1num) {
        this.guize1num = guize1num == null ? null : guize1num.trim();
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
     * 获取规则1消除单个单位所需最长时间
     *
     * @return time1 - 规则1消除单个单位所需最长时间
     */
    public String getTime1() {
        return time1;
    }

    /**
     * 设置规则1消除单个单位所需最长时间
     *
     * @param time1 规则1消除单个单位所需最长时间
     */
    public void setTime1(String time1) {
        this.time1 = time1 == null ? null : time1.trim();
    }

    /**
     * 获取规则1奖励时间
     *
     * @return jiangli1time - 规则1奖励时间
     */
    public String getJiangli1time() {
        return jiangli1time;
    }

    /**
     * 设置规则1奖励时间
     *
     * @param jiangli1time 规则1奖励时间
     */
    public void setJiangli1time(String jiangli1time) {
        this.jiangli1time = jiangli1time == null ? null : jiangli1time.trim();
    }

    /**
     * 获取规则2数量
     *
     * @return guize2num - 规则2数量
     */
    public String getGuize2num() {
        return guize2num;
    }

    /**
     * 设置规则2数量
     *
     * @param guize2num 规则2数量
     */
    public void setGuize2num(String guize2num) {
        this.guize2num = guize2num == null ? null : guize2num.trim();
    }

    /**
     * 获取规则2权重
     *
     * @return guize2quanzhong - 规则2权重
     */
    public String getGuize2quanzhong() {
        return guize2quanzhong;
    }

    /**
     * 设置规则2权重
     *
     * @param guize2quanzhong 规则2权重
     */
    public void setGuize2quanzhong(String guize2quanzhong) {
        this.guize2quanzhong = guize2quanzhong == null ? null : guize2quanzhong.trim();
    }

    /**
     * 获取规则2消除单个单位所需最长时间
     *
     * @return time2 - 规则2消除单个单位所需最长时间
     */
    public String getTime2() {
        return time2;
    }

    /**
     * 设置规则2消除单个单位所需最长时间
     *
     * @param time2 规则2消除单个单位所需最长时间
     */
    public void setTime2(String time2) {
        this.time2 = time2 == null ? null : time2.trim();
    }

    /**
     * 获取规则2奖励时间
     *
     * @return jiangli2time - 规则2奖励时间
     */
    public String getJiangli2time() {
        return jiangli2time;
    }

    /**
     * 设置规则2奖励时间
     *
     * @param jiangli2time 规则2奖励时间
     */
    public void setJiangli2time(String jiangli2time) {
        this.jiangli2time = jiangli2time == null ? null : jiangli2time.trim();
    }

    /**
     * 获取规则3数量
     *
     * @return guize3num - 规则3数量
     */
    public String getGuize3num() {
        return guize3num;
    }

    /**
     * 设置规则3数量
     *
     * @param guize3num 规则3数量
     */
    public void setGuize3num(String guize3num) {
        this.guize3num = guize3num == null ? null : guize3num.trim();
    }

    /**
     * 获取规则3权重
     *
     * @return guize3quanzhong - 规则3权重
     */
    public String getGuize3quanzhong() {
        return guize3quanzhong;
    }

    /**
     * 设置规则3权重
     *
     * @param guize3quanzhong 规则3权重
     */
    public void setGuize3quanzhong(String guize3quanzhong) {
        this.guize3quanzhong = guize3quanzhong == null ? null : guize3quanzhong.trim();
    }

    /**
     * 获取规则3消除单个单位所需最长时间
     *
     * @return time3 - 规则3消除单个单位所需最长时间
     */
    public String getTime3() {
        return time3;
    }

    /**
     * 设置规则3消除单个单位所需最长时间
     *
     * @param time3 规则3消除单个单位所需最长时间
     */
    public void setTime3(String time3) {
        this.time3 = time3 == null ? null : time3.trim();
    }

    /**
     * 获取规则3奖励时间
     *
     * @return jiangli3time - 规则3奖励时间
     */
    public String getJiangli3time() {
        return jiangli3time;
    }

    /**
     * 设置规则3奖励时间
     *
     * @param jiangli3time 规则3奖励时间
     */
    public void setJiangli3time(String jiangli3time) {
        this.jiangli3time = jiangli3time == null ? null : jiangli3time.trim();
    }

}
