package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_k_daba_config")
public class DaBaConfig implements Serializable {
    /**
     * 数量
     */
    private String num;

    /**
     * 出现时间
     */
    private String chuxiantime;

    /**
     * 出现次数
     */
    private String cishu;

    /**
     * 冷却时间
     */
    private String cd;

    /**
     * 错误次数
     */
    private String wrong;

    /**
     * 规则1数量
     */
    private String guize1num;

    /**
     * 规则1权重
     */
    private String guize1quanzhong;

    /**
     * 规则1时间
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
     * 规则2时间
     */
    private String time2;

    /**
     * 规则2奖励时间
     */
    private String jiangli2time;

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
     * 获取出现时间
     *
     * @return chuxiantime - 出现时间
     */
    public String getChuxiantime() {
        return chuxiantime;
    }

    /**
     * 设置出现时间
     *
     * @param chuxiantime 出现时间
     */
    public void setChuxiantime(String chuxiantime) {
        this.chuxiantime = chuxiantime == null ? null : chuxiantime.trim();
    }

    /**
     * 获取出现次数
     *
     * @return cishu - 出现次数
     */
    public String getCishu() {
        return cishu;
    }

    /**
     * 设置出现次数
     *
     * @param cishu 出现次数
     */
    public void setCishu(String cishu) {
        this.cishu = cishu == null ? null : cishu.trim();
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
     * 获取错误次数
     *
     * @return wrong - 错误次数
     */
    public String getWrong() {
        return wrong;
    }

    /**
     * 设置错误次数
     *
     * @param wrong 错误次数
     */
    public void setWrong(String wrong) {
        this.wrong = wrong == null ? null : wrong.trim();
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
     * 获取规则2时间
     *
     * @return time2 - 规则2时间
     */
    public String getTime2() {
        return time2;
    }

    /**
     * 设置规则2时间
     *
     * @param time2 规则2时间
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
}
