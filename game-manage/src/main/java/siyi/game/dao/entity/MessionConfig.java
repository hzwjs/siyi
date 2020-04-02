package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_mession_config")
public class MessionConfig implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 启用状态
     */
    private String onoff;

    /**
     * 限制等级
     */
    private String level;

    /**
     * 后续任务id
     */
    private String houxu;

    /**
     * 是否循环
     */
    private String xunhuan;

    /**
     * 冷却cd
     */
    private String cd;

    /**
     * 权重
     */
    private String weight;

    /**
     * 描述
     */
    private String tips;

    /**
     * 任务目标
     */
    private String target;

    /**
     * 任务目标-获取金币
     */
    @Column(name = "jinbi_get")
    private String jinbiGet;

    /**
     * 任务目标-获取经验
     */
    @Column(name = "jingyan_get")
    private String jingyanGet;

    /**
     * 任务目标-使用经验
     */
    @Column(name = "jingyan_use")
    private String jingyanUse;

    /**
     * 任务目标-获取道具
     */
    @Column(name = "item_get")
    private String itemGet;

    /**
     * 任务目标-使用道具
     */
    @Column(name = "item_use")
    private String itemUse;

    /**
     * 任务目标-成功通过文馆一定层数
     */
    @Column(name = "guoguan_OBJWEN")
    private String guoguanObjwen;

    /**
     * 任务目标-成功通过武馆一定层数
     */
    @Column(name = "guoguan_OBJWU")
    private String guoguanObjwu;

    /**
     * 任务目标-成功通过文馆关卡
     */
    @Column(name = "guoguan_numWEN")
    private String guoguanNumwen;

    /**
     * 任务目标-成功通过武馆关卡
     */
    @Column(name = "guoguan_numWU")
    private String guoguanNumwu;

    /**
     * 任务目标-填字类型的过关次数
     */
    @Column(name = "wenguan_TIANZI")
    private String wenguanTianzi;

    /**
     * 任务目标-选择类型过关次数
     */
    @Column(name = "wenguan_XUANZE")
    private String wenguanXuanze;

    /**
     * 任务目标-判断类型过关次数
     */
    @Column(name = "wenguan_PANDUAN")
    private String wenguanPanduan;

    /**
     * 任务目标-武关沙袋击杀数量
     */
    @Column(name = "wuguan1_SHADAI")
    private String wuguan1Shadai;

    /**
     * 任务目标-武关砖头击杀数量
     */
    @Column(name = "wuguan1_ZHUANTOU")
    private String wuguan1Zhuantou;

    /**
     * 任务目标-武关牌匾击杀数量
     */
    @Column(name = "wuguan1_PAIBIAN")
    private String wuguan1Paibian;

    /**
     * 任务目标-武关木桩击杀数量
     */
    @Column(name = "wuguan1_MUZHUANG")
    private String wuguan1Muzhuang;

    /**
     * 任务目标-武关木棍击杀数量
     */
    @Column(name = "wuguan1_MUGUN")
    private String wuguan1Mugun;

    /**
     * 任务目标-门板击杀数量
     */
    @Column(name = "wuguan1_MENBAN")
    private String wuguan1Menban;

    /**
     * 任务目标-击杀乒乓球数量
     */
    @Column(name = "wuguan2_PINGPANG")
    private String wuguan2Pingpang;

    /**
     * 任务目标-击杀飞镖数量
     */
    @Column(name = "wuguan2_FEIBIAO")
    private String wuguan2Feibiao;

    /**
     * 任务目标-击杀木桶数量
     */
    @Column(name = "wuguan2_MUTONG")
    private String wuguan2Mutong;

    /**
     * 任务目标-对对碰正确次数
     */
    @Column(name = "wuguan3_DUIDUIPENG")
    private String wuguan3Duiduipeng;

    /**
     * 任务目标-猜对球球的次数
     */
    @Column(name = "wuguan4_QIUQIU")
    private String wuguan4Qiuqiu;

    /**
     * 任务目标-打靶击中靶子数量
     */
    @Column(name = "wuguan5_MUBIAO")
    private String wuguan5Mubiao;

    /**
     * 任务目标-广告观看次数
     */
    @Column(name = "guanggao_dianji")
    private String guanggaoDianji;

    /**
     * 任务目标-X2广告观看次数
     */
    @Column(name = "guanggao_X2")
    private String guanggaoX2;

    /**
     * 任务目标-X3广告观看次数
     */
    @Column(name = "guanggao_X3")
    private String guanggaoX3;

    /**
     * 任务目标-X4广告观看次数
     */
    @Column(name = "guanggao_X4")
    private String guanggaoX4;

    /**
     * 任务目标-分享次数
     */
    @Column(name = "share_num")
    private String shareNum;

    /**
     * 任务奖励经验
     */
    private String jiangliexp;

    /**
     * 任务奖励金币
     */
    private String jianglijinbi;

    /**
     * 任务奖励道具
     */
    private String jiangliitem;

    /**
     * 任务奖励数量
     */
    private String itemnum;

    /**
     * 任务奖励概率
     */
    private String itemgailv;

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
     * 获取启用状态
     *
     * @return onoff - 启用状态
     */
    public String getOnoff() {
        return onoff;
    }

    /**
     * 设置启用状态
     *
     * @param onoff 启用状态
     */
    public void setOnoff(String onoff) {
        this.onoff = onoff == null ? null : onoff.trim();
    }

    /**
     * 获取限制等级
     *
     * @return level - 限制等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置限制等级
     *
     * @param level 限制等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 获取后续任务id
     *
     * @return houxu - 后续任务id
     */
    public String getHouxu() {
        return houxu;
    }

    /**
     * 设置后续任务id
     *
     * @param houxu 后续任务id
     */
    public void setHouxu(String houxu) {
        this.houxu = houxu == null ? null : houxu.trim();
    }

    /**
     * 获取是否循环
     *
     * @return xunhuan - 是否循环
     */
    public String getXunhuan() {
        return xunhuan;
    }

    /**
     * 设置是否循环
     *
     * @param xunhuan 是否循环
     */
    public void setXunhuan(String xunhuan) {
        this.xunhuan = xunhuan == null ? null : xunhuan.trim();
    }

    /**
     * 获取冷却cd
     *
     * @return cd - 冷却cd
     */
    public String getCd() {
        return cd;
    }

    /**
     * 设置冷却cd
     *
     * @param cd 冷却cd
     */
    public void setCd(String cd) {
        this.cd = cd == null ? null : cd.trim();
    }

    /**
     * 获取权重
     *
     * @return weight - 权重
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置权重
     *
     * @param weight 权重
     */
    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    /**
     * 获取描述
     *
     * @return tips - 描述
     */
    public String getTips() {
        return tips;
    }

    /**
     * 设置描述
     *
     * @param tips 描述
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }

    /**
     * 获取任务目标
     *
     * @return target - 任务目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置任务目标
     *
     * @param target 任务目标
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    /**
     * 获取任务目标-获取金币
     *
     * @return jinbi_get - 任务目标-获取金币
     */
    public String getJinbiGet() {
        return jinbiGet;
    }

    /**
     * 设置任务目标-获取金币
     *
     * @param jinbiGet 任务目标-获取金币
     */
    public void setJinbiGet(String jinbiGet) {
        this.jinbiGet = jinbiGet == null ? null : jinbiGet.trim();
    }

    /**
     * 获取任务目标-获取经验
     *
     * @return jingyan_get - 任务目标-获取经验
     */
    public String getJingyanGet() {
        return jingyanGet;
    }

    /**
     * 设置任务目标-获取经验
     *
     * @param jingyanGet 任务目标-获取经验
     */
    public void setJingyanGet(String jingyanGet) {
        this.jingyanGet = jingyanGet == null ? null : jingyanGet.trim();
    }

    /**
     * 获取任务目标-使用经验
     *
     * @return jingyan_use - 任务目标-使用经验
     */
    public String getJingyanUse() {
        return jingyanUse;
    }

    /**
     * 设置任务目标-使用经验
     *
     * @param jingyanUse 任务目标-使用经验
     */
    public void setJingyanUse(String jingyanUse) {
        this.jingyanUse = jingyanUse == null ? null : jingyanUse.trim();
    }

    /**
     * 获取任务目标-获取道具
     *
     * @return item_get - 任务目标-获取道具
     */
    public String getItemGet() {
        return itemGet;
    }

    /**
     * 设置任务目标-获取道具
     *
     * @param itemGet 任务目标-获取道具
     */
    public void setItemGet(String itemGet) {
        this.itemGet = itemGet == null ? null : itemGet.trim();
    }

    /**
     * 获取任务目标-使用道具
     *
     * @return item_use - 任务目标-使用道具
     */
    public String getItemUse() {
        return itemUse;
    }

    /**
     * 设置任务目标-使用道具
     *
     * @param itemUse 任务目标-使用道具
     */
    public void setItemUse(String itemUse) {
        this.itemUse = itemUse == null ? null : itemUse.trim();
    }

    /**
     * 获取任务目标-成功通过文馆一定层数
     *
     * @return guoguan_OBJWEN - 任务目标-成功通过文馆一定层数
     */
    public String getGuoguanObjwen() {
        return guoguanObjwen;
    }

    /**
     * 设置任务目标-成功通过文馆一定层数
     *
     * @param guoguanObjwen 任务目标-成功通过文馆一定层数
     */
    public void setGuoguanObjwen(String guoguanObjwen) {
        this.guoguanObjwen = guoguanObjwen == null ? null : guoguanObjwen.trim();
    }

    /**
     * 获取任务目标-成功通过武馆一定层数
     *
     * @return guoguan_OBJWU - 任务目标-成功通过武馆一定层数
     */
    public String getGuoguanObjwu() {
        return guoguanObjwu;
    }

    /**
     * 设置任务目标-成功通过武馆一定层数
     *
     * @param guoguanObjwu 任务目标-成功通过武馆一定层数
     */
    public void setGuoguanObjwu(String guoguanObjwu) {
        this.guoguanObjwu = guoguanObjwu == null ? null : guoguanObjwu.trim();
    }

    /**
     * 获取任务目标-成功通过文馆关卡
     *
     * @return guoguan_numWEN - 任务目标-成功通过文馆关卡
     */
    public String getGuoguanNumwen() {
        return guoguanNumwen;
    }

    /**
     * 设置任务目标-成功通过文馆关卡
     *
     * @param guoguanNumwen 任务目标-成功通过文馆关卡
     */
    public void setGuoguanNumwen(String guoguanNumwen) {
        this.guoguanNumwen = guoguanNumwen == null ? null : guoguanNumwen.trim();
    }

    /**
     * 获取任务目标-成功通过武馆关卡
     *
     * @return guoguan_numWU - 任务目标-成功通过武馆关卡
     */
    public String getGuoguanNumwu() {
        return guoguanNumwu;
    }

    /**
     * 设置任务目标-成功通过武馆关卡
     *
     * @param guoguanNumwu 任务目标-成功通过武馆关卡
     */
    public void setGuoguanNumwu(String guoguanNumwu) {
        this.guoguanNumwu = guoguanNumwu == null ? null : guoguanNumwu.trim();
    }

    /**
     * 获取任务目标-填字类型的过关次数
     *
     * @return wenguan_TIANZI - 任务目标-填字类型的过关次数
     */
    public String getWenguanTianzi() {
        return wenguanTianzi;
    }

    /**
     * 设置任务目标-填字类型的过关次数
     *
     * @param wenguanTianzi 任务目标-填字类型的过关次数
     */
    public void setWenguanTianzi(String wenguanTianzi) {
        this.wenguanTianzi = wenguanTianzi == null ? null : wenguanTianzi.trim();
    }

    /**
     * 获取任务目标-选择类型过关次数
     *
     * @return wenguan_XUANZE - 任务目标-选择类型过关次数
     */
    public String getWenguanXuanze() {
        return wenguanXuanze;
    }

    /**
     * 设置任务目标-选择类型过关次数
     *
     * @param wenguanXuanze 任务目标-选择类型过关次数
     */
    public void setWenguanXuanze(String wenguanXuanze) {
        this.wenguanXuanze = wenguanXuanze == null ? null : wenguanXuanze.trim();
    }

    /**
     * 获取任务目标-判断类型过关次数
     *
     * @return wenguan_PANDUAN - 任务目标-判断类型过关次数
     */
    public String getWenguanPanduan() {
        return wenguanPanduan;
    }

    /**
     * 设置任务目标-判断类型过关次数
     *
     * @param wenguanPanduan 任务目标-判断类型过关次数
     */
    public void setWenguanPanduan(String wenguanPanduan) {
        this.wenguanPanduan = wenguanPanduan == null ? null : wenguanPanduan.trim();
    }

    /**
     * 获取任务目标-武关沙袋击杀数量
     *
     * @return wuguan1_SHADAI - 任务目标-武关沙袋击杀数量
     */
    public String getWuguan1Shadai() {
        return wuguan1Shadai;
    }

    /**
     * 设置任务目标-武关沙袋击杀数量
     *
     * @param wuguan1Shadai 任务目标-武关沙袋击杀数量
     */
    public void setWuguan1Shadai(String wuguan1Shadai) {
        this.wuguan1Shadai = wuguan1Shadai == null ? null : wuguan1Shadai.trim();
    }

    /**
     * 获取任务目标-武关砖头击杀数量
     *
     * @return wuguan1_ZHUANTOU - 任务目标-武关砖头击杀数量
     */
    public String getWuguan1Zhuantou() {
        return wuguan1Zhuantou;
    }

    /**
     * 设置任务目标-武关砖头击杀数量
     *
     * @param wuguan1Zhuantou 任务目标-武关砖头击杀数量
     */
    public void setWuguan1Zhuantou(String wuguan1Zhuantou) {
        this.wuguan1Zhuantou = wuguan1Zhuantou == null ? null : wuguan1Zhuantou.trim();
    }

    /**
     * 获取任务目标-武关牌匾击杀数量
     *
     * @return wuguan1_PAIBIAN - 任务目标-武关牌匾击杀数量
     */
    public String getWuguan1Paibian() {
        return wuguan1Paibian;
    }

    /**
     * 设置任务目标-武关牌匾击杀数量
     *
     * @param wuguan1Paibian 任务目标-武关牌匾击杀数量
     */
    public void setWuguan1Paibian(String wuguan1Paibian) {
        this.wuguan1Paibian = wuguan1Paibian == null ? null : wuguan1Paibian.trim();
    }

    /**
     * 获取任务目标-武关木桩击杀数量
     *
     * @return wuguan1_MUZHUANG - 任务目标-武关木桩击杀数量
     */
    public String getWuguan1Muzhuang() {
        return wuguan1Muzhuang;
    }

    /**
     * 设置任务目标-武关木桩击杀数量
     *
     * @param wuguan1Muzhuang 任务目标-武关木桩击杀数量
     */
    public void setWuguan1Muzhuang(String wuguan1Muzhuang) {
        this.wuguan1Muzhuang = wuguan1Muzhuang == null ? null : wuguan1Muzhuang.trim();
    }

    /**
     * 获取任务目标-武关木棍击杀数量
     *
     * @return wuguan1_MUGUN - 任务目标-武关木棍击杀数量
     */
    public String getWuguan1Mugun() {
        return wuguan1Mugun;
    }

    /**
     * 设置任务目标-武关木棍击杀数量
     *
     * @param wuguan1Mugun 任务目标-武关木棍击杀数量
     */
    public void setWuguan1Mugun(String wuguan1Mugun) {
        this.wuguan1Mugun = wuguan1Mugun == null ? null : wuguan1Mugun.trim();
    }

    /**
     * 获取任务目标-门板击杀数量
     *
     * @return wuguan1_MENBAN - 任务目标-门板击杀数量
     */
    public String getWuguan1Menban() {
        return wuguan1Menban;
    }

    /**
     * 设置任务目标-门板击杀数量
     *
     * @param wuguan1Menban 任务目标-门板击杀数量
     */
    public void setWuguan1Menban(String wuguan1Menban) {
        this.wuguan1Menban = wuguan1Menban == null ? null : wuguan1Menban.trim();
    }

    /**
     * 获取任务目标-击杀乒乓球数量
     *
     * @return wuguan2_PINGPANG - 任务目标-击杀乒乓球数量
     */
    public String getWuguan2Pingpang() {
        return wuguan2Pingpang;
    }

    /**
     * 设置任务目标-击杀乒乓球数量
     *
     * @param wuguan2Pingpang 任务目标-击杀乒乓球数量
     */
    public void setWuguan2Pingpang(String wuguan2Pingpang) {
        this.wuguan2Pingpang = wuguan2Pingpang == null ? null : wuguan2Pingpang.trim();
    }

    /**
     * 获取任务目标-击杀飞镖数量
     *
     * @return wuguan2_FEIBIAO - 任务目标-击杀飞镖数量
     */
    public String getWuguan2Feibiao() {
        return wuguan2Feibiao;
    }

    /**
     * 设置任务目标-击杀飞镖数量
     *
     * @param wuguan2Feibiao 任务目标-击杀飞镖数量
     */
    public void setWuguan2Feibiao(String wuguan2Feibiao) {
        this.wuguan2Feibiao = wuguan2Feibiao == null ? null : wuguan2Feibiao.trim();
    }

    /**
     * 获取任务目标-击杀木桶数量
     *
     * @return wuguan2_MUTONG - 任务目标-击杀木桶数量
     */
    public String getWuguan2Mutong() {
        return wuguan2Mutong;
    }

    /**
     * 设置任务目标-击杀木桶数量
     *
     * @param wuguan2Mutong 任务目标-击杀木桶数量
     */
    public void setWuguan2Mutong(String wuguan2Mutong) {
        this.wuguan2Mutong = wuguan2Mutong == null ? null : wuguan2Mutong.trim();
    }

    /**
     * 获取任务目标-对对碰正确次数
     *
     * @return wuguan3_DUIDUIPENG - 任务目标-对对碰正确次数
     */
    public String getWuguan3Duiduipeng() {
        return wuguan3Duiduipeng;
    }

    /**
     * 设置任务目标-对对碰正确次数
     *
     * @param wuguan3Duiduipeng 任务目标-对对碰正确次数
     */
    public void setWuguan3Duiduipeng(String wuguan3Duiduipeng) {
        this.wuguan3Duiduipeng = wuguan3Duiduipeng == null ? null : wuguan3Duiduipeng.trim();
    }

    /**
     * 获取任务目标-猜对球球的次数
     *
     * @return wuguan4_QIUQIU - 任务目标-猜对球球的次数
     */
    public String getWuguan4Qiuqiu() {
        return wuguan4Qiuqiu;
    }

    /**
     * 设置任务目标-猜对球球的次数
     *
     * @param wuguan4Qiuqiu 任务目标-猜对球球的次数
     */
    public void setWuguan4Qiuqiu(String wuguan4Qiuqiu) {
        this.wuguan4Qiuqiu = wuguan4Qiuqiu == null ? null : wuguan4Qiuqiu.trim();
    }

    /**
     * 获取任务目标-打靶击中靶子数量
     *
     * @return wuguan5_MUBIAO - 任务目标-打靶击中靶子数量
     */
    public String getWuguan5Mubiao() {
        return wuguan5Mubiao;
    }

    /**
     * 设置任务目标-打靶击中靶子数量
     *
     * @param wuguan5Mubiao 任务目标-打靶击中靶子数量
     */
    public void setWuguan5Mubiao(String wuguan5Mubiao) {
        this.wuguan5Mubiao = wuguan5Mubiao == null ? null : wuguan5Mubiao.trim();
    }

    /**
     * 获取任务目标-广告观看次数
     *
     * @return guanggao_dianji - 任务目标-广告观看次数
     */
    public String getGuanggaoDianji() {
        return guanggaoDianji;
    }

    /**
     * 设置任务目标-广告观看次数
     *
     * @param guanggaoDianji 任务目标-广告观看次数
     */
    public void setGuanggaoDianji(String guanggaoDianji) {
        this.guanggaoDianji = guanggaoDianji == null ? null : guanggaoDianji.trim();
    }

    /**
     * 获取任务目标-X2广告观看次数
     *
     * @return guanggao_X2 - 任务目标-X2广告观看次数
     */
    public String getGuanggaoX2() {
        return guanggaoX2;
    }

    /**
     * 设置任务目标-X2广告观看次数
     *
     * @param guanggaoX2 任务目标-X2广告观看次数
     */
    public void setGuanggaoX2(String guanggaoX2) {
        this.guanggaoX2 = guanggaoX2 == null ? null : guanggaoX2.trim();
    }

    /**
     * 获取任务目标-X3广告观看次数
     *
     * @return guanggao_X3 - 任务目标-X3广告观看次数
     */
    public String getGuanggaoX3() {
        return guanggaoX3;
    }

    /**
     * 设置任务目标-X3广告观看次数
     *
     * @param guanggaoX3 任务目标-X3广告观看次数
     */
    public void setGuanggaoX3(String guanggaoX3) {
        this.guanggaoX3 = guanggaoX3 == null ? null : guanggaoX3.trim();
    }

    /**
     * 获取任务目标-X4广告观看次数
     *
     * @return guanggao_X4 - 任务目标-X4广告观看次数
     */
    public String getGuanggaoX4() {
        return guanggaoX4;
    }

    /**
     * 设置任务目标-X4广告观看次数
     *
     * @param guanggaoX4 任务目标-X4广告观看次数
     */
    public void setGuanggaoX4(String guanggaoX4) {
        this.guanggaoX4 = guanggaoX4 == null ? null : guanggaoX4.trim();
    }

    /**
     * 获取任务目标-分享次数
     *
     * @return share_num - 任务目标-分享次数
     */
    public String getShareNum() {
        return shareNum;
    }

    /**
     * 设置任务目标-分享次数
     *
     * @param shareNum 任务目标-分享次数
     */
    public void setShareNum(String shareNum) {
        this.shareNum = shareNum == null ? null : shareNum.trim();
    }

    /**
     * 获取任务奖励经验
     *
     * @return jiangliexp - 任务奖励经验
     */
    public String getJiangliexp() {
        return jiangliexp;
    }

    /**
     * 设置任务奖励经验
     *
     * @param jiangliexp 任务奖励经验
     */
    public void setJiangliexp(String jiangliexp) {
        this.jiangliexp = jiangliexp == null ? null : jiangliexp.trim();
    }

    /**
     * 获取任务奖励金币
     *
     * @return jianglijinbi - 任务奖励金币
     */
    public String getJianglijinbi() {
        return jianglijinbi;
    }

    /**
     * 设置任务奖励金币
     *
     * @param jianglijinbi 任务奖励金币
     */
    public void setJianglijinbi(String jianglijinbi) {
        this.jianglijinbi = jianglijinbi == null ? null : jianglijinbi.trim();
    }

    /**
     * 获取任务奖励道具
     *
     * @return jiangliitem - 任务奖励道具
     */
    public String getJiangliitem() {
        return jiangliitem;
    }

    /**
     * 设置任务奖励道具
     *
     * @param jiangliitem 任务奖励道具
     */
    public void setJiangliitem(String jiangliitem) {
        this.jiangliitem = jiangliitem == null ? null : jiangliitem.trim();
    }

    /**
     * 获取任务奖励数量
     *
     * @return itemnum - 任务奖励数量
     */
    public String getItemnum() {
        return itemnum;
    }

    /**
     * 设置任务奖励数量
     *
     * @param itemnum 任务奖励数量
     */
    public void setItemnum(String itemnum) {
        this.itemnum = itemnum == null ? null : itemnum.trim();
    }

    /**
     * 获取任务奖励概率
     *
     * @return itemgailv - 任务奖励概率
     */
    public String getItemgailv() {
        return itemgailv;
    }

    /**
     * 设置任务奖励概率
     *
     * @param itemgailv 任务奖励概率
     */
    public void setItemgailv(String itemgailv) {
        this.itemgailv = itemgailv == null ? null : itemgailv.trim();
    }
}