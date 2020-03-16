package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_jiangliguanqia_config")
public class JiangliConfig implements Serializable {
    /**
     * 关卡
     */
    private String guanqia;

    /**
     * 关卡时长
     */
    private String time;

    /**
     * 打靶权重
     */
    @Column(name = "K_daba")
    private String kDaba;

    /**
     * 点击权重
     */
    @Column(name = "K_dianji")
    private String kDianji;

    /**
     * 翻牌权重
     */
    @Column(name = "K_fanpai")
    private String kFanpai;

    /**
     * 关卡权重
     */
    @Column(name = "K_guanqia")
    private String kGuanqia;

    /**
     * 球权重
     */
    @Column(name = "K_qiu")
    private String kQiu;

    /**
     * 坠落权重
     */
    @Column(name = "K_zhuiluo")
    private String kZhuiluo;

    /**
     * hp系数
     */
    @Column(name = "Hpxishu")
    private String hpxishu;

    /**
     * 奖励金币
     */
    private String jiangligold;

    /**
     * 奖励经验
     */
    private String jiangliexp;

    /**
     * 奖励道具
     */
    private String jiangliitem;

    /**
     * 奖励道具数量
     */
    private String itemshuliang;

    /**
     * 奖励概率
     */
    private String jiangligailv;

    /**
     * 广告概率
     */
    private String guanggao;

    /**
     * 复活概率
     */
    private String fuhuo;

    private static final long serialVersionUID = 1L;

    /**
     * 获取关卡
     *
     * @return guanqia - 关卡
     */
    public String getGuanqia() {
        return guanqia;
    }

    /**
     * 设置关卡
     *
     * @param guanqia 关卡
     */
    public void setGuanqia(String guanqia) {
        this.guanqia = guanqia == null ? null : guanqia.trim();
    }

    /**
     * 获取关卡时长
     *
     * @return time - 关卡时长
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置关卡时长
     *
     * @param time 关卡时长
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    /**
     * 获取打靶权重
     *
     * @return K_daba - 打靶权重
     */
    public String getkDaba() {
        return kDaba;
    }

    /**
     * 设置打靶权重
     *
     * @param kDaba 打靶权重
     */
    public void setkDaba(String kDaba) {
        this.kDaba = kDaba == null ? null : kDaba.trim();
    }

    /**
     * 获取点击权重
     *
     * @return K_dianji - 点击权重
     */
    public String getkDianji() {
        return kDianji;
    }

    /**
     * 设置点击权重
     *
     * @param kDianji 点击权重
     */
    public void setkDianji(String kDianji) {
        this.kDianji = kDianji == null ? null : kDianji.trim();
    }

    /**
     * 获取翻牌权重
     *
     * @return K_fanpai - 翻牌权重
     */
    public String getkFanpai() {
        return kFanpai;
    }

    /**
     * 设置翻牌权重
     *
     * @param kFanpai 翻牌权重
     */
    public void setkFanpai(String kFanpai) {
        this.kFanpai = kFanpai == null ? null : kFanpai.trim();
    }

    /**
     * 获取关卡权重
     *
     * @return K_guanqia - 关卡权重
     */
    public String getkGuanqia() {
        return kGuanqia;
    }

    /**
     * 设置关卡权重
     *
     * @param kGuanqia 关卡权重
     */
    public void setkGuanqia(String kGuanqia) {
        this.kGuanqia = kGuanqia == null ? null : kGuanqia.trim();
    }

    /**
     * 获取球权重
     *
     * @return K_qiu - 球权重
     */
    public String getkQiu() {
        return kQiu;
    }

    /**
     * 设置球权重
     *
     * @param kQiu 球权重
     */
    public void setkQiu(String kQiu) {
        this.kQiu = kQiu == null ? null : kQiu.trim();
    }

    /**
     * 获取坠落权重
     *
     * @return K_zhuiluo - 坠落权重
     */
    public String getkZhuiluo() {
        return kZhuiluo;
    }

    /**
     * 设置坠落权重
     *
     * @param kZhuiluo 坠落权重
     */
    public void setkZhuiluo(String kZhuiluo) {
        this.kZhuiluo = kZhuiluo == null ? null : kZhuiluo.trim();
    }

    /**
     * 获取hp系数
     *
     * @return Hpxishu - hp系数
     */
    public String getHpxishu() {
        return hpxishu;
    }

    /**
     * 设置hp系数
     *
     * @param hpxishu hp系数
     */
    public void setHpxishu(String hpxishu) {
        this.hpxishu = hpxishu == null ? null : hpxishu.trim();
    }

    /**
     * 获取奖励金币
     *
     * @return jiangligold - 奖励金币
     */
    public String getJiangligold() {
        return jiangligold;
    }

    /**
     * 设置奖励金币
     *
     * @param jiangligold 奖励金币
     */
    public void setJiangligold(String jiangligold) {
        this.jiangligold = jiangligold == null ? null : jiangligold.trim();
    }

    /**
     * 获取奖励经验
     *
     * @return jiangliexp - 奖励经验
     */
    public String getJiangliexp() {
        return jiangliexp;
    }

    /**
     * 设置奖励经验
     *
     * @param jiangliexp 奖励经验
     */
    public void setJiangliexp(String jiangliexp) {
        this.jiangliexp = jiangliexp == null ? null : jiangliexp.trim();
    }

    /**
     * 获取奖励道具
     *
     * @return jiangliitem - 奖励道具
     */
    public String getJiangliitem() {
        return jiangliitem;
    }

    /**
     * 设置奖励道具
     *
     * @param jiangliitem 奖励道具
     */
    public void setJiangliitem(String jiangliitem) {
        this.jiangliitem = jiangliitem == null ? null : jiangliitem.trim();
    }

    /**
     * 获取奖励道具数量
     *
     * @return itemshuliang - 奖励道具数量
     */
    public String getItemshuliang() {
        return itemshuliang;
    }

    /**
     * 设置奖励道具数量
     *
     * @param itemshuliang 奖励道具数量
     */
    public void setItemshuliang(String itemshuliang) {
        this.itemshuliang = itemshuliang == null ? null : itemshuliang.trim();
    }

    /**
     * 获取奖励概率
     *
     * @return jiangligailv - 奖励概率
     */
    public String getJiangligailv() {
        return jiangligailv;
    }

    /**
     * 设置奖励概率
     *
     * @param jiangligailv 奖励概率
     */
    public void setJiangligailv(String jiangligailv) {
        this.jiangligailv = jiangligailv == null ? null : jiangligailv.trim();
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

    /**
     * 获取复活概率
     *
     * @return fuhuo - 复活概率
     */
    public String getFuhuo() {
        return fuhuo;
    }

    /**
     * 设置复活概率
     *
     * @param fuhuo 复活概率
     */
    public void setFuhuo(String fuhuo) {
        this.fuhuo = fuhuo == null ? null : fuhuo.trim();
    }
}