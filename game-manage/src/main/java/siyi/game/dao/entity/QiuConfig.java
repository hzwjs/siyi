package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_k_qiu_config")
public class QiuConfig implements Serializable {
    /**
     * 杯子数量
     */
    private String beizinum;

    /**
     * 杯子权重
     */
    private String beiziquanzhong;

    /**
     * 球数量
     */
    private String qiunum;

    /**
     * 球权重
     */
    private String qiuquanzhong;

    /**
     * 速度
     */
    private String spend;

    /**
     * 交换次数
     */
    private String jiaohuannum;

    /**
     * 冷却时间
     */
    private String cd;

    /**
     * 错误次数
     */
    private String wrong;

    /**
     * 奖励时间
     */
    private String jianglitime;


    private static final long serialVersionUID = 1L;

    /**
     * 获取杯子数量
     *
     * @return beizinum - 杯子数量
     */
    public String getBeizinum() {
        return beizinum;
    }

    /**
     * 设置杯子数量
     *
     * @param beizinum 杯子数量
     */
    public void setBeizinum(String beizinum) {
        this.beizinum = beizinum == null ? null : beizinum.trim();
    }

    /**
     * 获取杯子权重
     *
     * @return beiziquanzhong - 杯子权重
     */
    public String getBeiziquanzhong() {
        return beiziquanzhong;
    }

    /**
     * 设置杯子权重
     *
     * @param beiziquanzhong 杯子权重
     */
    public void setBeiziquanzhong(String beiziquanzhong) {
        this.beiziquanzhong = beiziquanzhong == null ? null : beiziquanzhong.trim();
    }

    /**
     * 获取球数量
     *
     * @return qiunum - 球数量
     */
    public String getQiunum() {
        return qiunum;
    }

    /**
     * 设置球数量
     *
     * @param qiunum 球数量
     */
    public void setQiunum(String qiunum) {
        this.qiunum = qiunum == null ? null : qiunum.trim();
    }

    /**
     * 获取球权重
     *
     * @return qiuquanzhong - 球权重
     */
    public String getQiuquanzhong() {
        return qiuquanzhong;
    }

    /**
     * 设置球权重
     *
     * @param qiuquanzhong 球权重
     */
    public void setQiuquanzhong(String qiuquanzhong) {
        this.qiuquanzhong = qiuquanzhong == null ? null : qiuquanzhong.trim();
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
     * 获取交换次数
     *
     * @return jiaohuannum - 交换次数
     */
    public String getJiaohuannum() {
        return jiaohuannum;
    }

    /**
     * 设置交换次数
     *
     * @param jiaohuannum 交换次数
     */
    public void setJiaohuannum(String jiaohuannum) {
        this.jiaohuannum = jiaohuannum == null ? null : jiaohuannum.trim();
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
     * 获取奖励时间
     *
     * @return jianglitime - 奖励时间
     */
    public String getJianglitime() {
        return jianglitime;
    }

    /**
     * 设置奖励时间
     *
     * @param jianglitime 奖励时间
     */
    public void setJianglitime(String jianglitime) {
        this.jianglitime = jianglitime == null ? null : jianglitime.trim();
    }

}
