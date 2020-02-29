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

    /**
     * 金币
     */
    private String gold;

    /**
     * 经验
     */
    private String exp;

    /**
     * 总时间
     */
    private String zongtime;

    /**
     * 道具
     */
    private String item;

    /**
     * 道具数量
     */
    private String itemnum;

    /**
     * 道具概率
     */
    private String itemgailv;

    /**
     * 翻倍概率
     */
    @Column(name = "double_rate")
    private String doubleRate;

    /**
     * 广告概率
     */
    private String guanggao;

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

    /**
     * 获取金币
     *
     * @return gold - 金币
     */
    public String getGold() {
        return gold;
    }

    /**
     * 设置金币
     *
     * @param gold 金币
     */
    public void setGold(String gold) {
        this.gold = gold == null ? null : gold.trim();
    }

    /**
     * 获取经验
     *
     * @return exp - 经验
     */
    public String getExp() {
        return exp;
    }

    /**
     * 设置经验
     *
     * @param exp 经验
     */
    public void setExp(String exp) {
        this.exp = exp == null ? null : exp.trim();
    }

    /**
     * 获取总时间
     *
     * @return zongtime - 总时间
     */
    public String getZongtime() {
        return zongtime;
    }

    /**
     * 设置总时间
     *
     * @param zongtime 总时间
     */
    public void setZongtime(String zongtime) {
        this.zongtime = zongtime == null ? null : zongtime.trim();
    }

    /**
     * 获取道具
     *
     * @return item - 道具
     */
    public String getItem() {
        return item;
    }

    /**
     * 设置道具
     *
     * @param item 道具
     */
    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    /**
     * 获取道具数量
     *
     * @return itemnum - 道具数量
     */
    public String getItemnum() {
        return itemnum;
    }

    /**
     * 设置道具数量
     *
     * @param itemnum 道具数量
     */
    public void setItemnum(String itemnum) {
        this.itemnum = itemnum == null ? null : itemnum.trim();
    }

    /**
     * 获取道具概率
     *
     * @return itemgailv - 道具概率
     */
    public String getItemgailv() {
        return itemgailv;
    }

    /**
     * 设置道具概率
     *
     * @param itemgailv 道具概率
     */
    public void setItemgailv(String itemgailv) {
        this.itemgailv = itemgailv == null ? null : itemgailv.trim();
    }

    /**
     * 获取翻倍概率
     *
     * @return double_rate - 翻倍概率
     */
    public String getDoubleRate() {
        return doubleRate;
    }

    /**
     * 设置翻倍概率
     *
     * @param doubleRate 翻倍概率
     */
    public void setDoubleRate(String doubleRate) {
        this.doubleRate = doubleRate == null ? null : doubleRate.trim();
    }

    /**
     * 获取广告概率
     *
     * @return guanggao - 广告概率
     */
    public String getGuanggao() {
        return guanggao;
    }

    /**
     * 设置广告概率
     *
     * @param guanggao 广告概率
     */
    public void setGuanggao(String guanggao) {
        this.guanggao = guanggao == null ? null : guanggao.trim();
    }
}