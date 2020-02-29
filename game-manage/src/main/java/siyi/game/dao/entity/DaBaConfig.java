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
     * 双倍
     */
    @Column(name = "double_rate")
    private String doubleRate;

    /**
     * 广告
     */
    private String guanggao;

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
     * 获取双倍
     *
     * @return double_rate - 双倍
     */
    public String getDoubleRate() {
        return doubleRate;
    }

    /**
     * 设置双倍
     *
     * @param doubleRate 双倍
     */
    public void setDoubleRate(String doubleRate) {
        this.doubleRate = doubleRate == null ? null : doubleRate.trim();
    }

    /**
     * 获取广告
     *
     * @return guanggao - 广告
     */
    public String getGuanggao() {
        return guanggao;
    }

    /**
     * 设置广告
     *
     * @param guanggao 广告
     */
    public void setGuanggao(String guanggao) {
        this.guanggao = guanggao == null ? null : guanggao.trim();
    }
}