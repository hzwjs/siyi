package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_k_fanpai_config")
public class FanPaiConfig implements Serializable {

    /**
     * 主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 数量
     */
    private String num;

    /**
     * 权重
     */
    private String quanzhong;

    /**
     * 时间
     */
    private String time;

    /**
     * 奖励时间
     */
    private String jianglitime;

    private String zi1;

    private String zi2;

    private String zi3;

    private String zi4;

    private String zi5;

    private String zi6;

    private String zi7;

    private String zi8;

    private String zi9;

    private String zi10;

    private String zi11;

    private String zi12;

    private String zi13;

    private String zi14;

    private String zi15;

    private String zi16;

    private String zi17;

    private String zi18;

    private String zi19;

    private String zi20;
    private String zi21;
    private String zi22;
    private String zi23;
    private String zi24;
    private String zi25;
    private String zi26;
    private String zi27;
    private String zi28;
    private String zi29;
    private String zi30;
    private String zi31;
    private String zi32;
    private String zi33;
    private String zi34;
    private String zi35;
    private String zi36;
    private String zi37;
    private String zi38;
    private String zi39;
    private String zi40;
    private String zi41;
    private String zi42;
    private String zi43;
    private String zi44;
    private String zi45;
    private String zi46;
    private String zi47;
    private String zi48;
    private String zi49;
    private String zi50;

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
        this.id = id;
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
     * 获取权重
     *
     * @return quanzhong - 权重
     */
    public String getQuanzhong() {
        return quanzhong;
    }

    /**
     * 设置权重
     *
     * @param quanzhong 权重
     */
    public void setQuanzhong(String quanzhong) {
        this.quanzhong = quanzhong == null ? null : quanzhong.trim();
    }

    /**
     * 获取时间
     *
     * @return time - 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     *
     * @param time 时间
     */
    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
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
     * @return zi1
     */
    public String getZi1() {
        return zi1;
    }

    /**
     * @param zi1
     */
    public void setZi1(String zi1) {
        this.zi1 = zi1 == null ? null : zi1.trim();
    }

    /**
     * @return zi2
     */
    public String getZi2() {
        return zi2;
    }

    /**
     * @param zi2
     */
    public void setZi2(String zi2) {
        this.zi2 = zi2 == null ? null : zi2.trim();
    }

    /**
     * @return zi3
     */
    public String getZi3() {
        return zi3;
    }

    /**
     * @param zi3
     */
    public void setZi3(String zi3) {
        this.zi3 = zi3 == null ? null : zi3.trim();
    }

    /**
     * @return zi4
     */
    public String getZi4() {
        return zi4;
    }

    /**
     * @param zi4
     */
    public void setZi4(String zi4) {
        this.zi4 = zi4 == null ? null : zi4.trim();
    }

    /**
     * @return zi5
     */
    public String getZi5() {
        return zi5;
    }

    /**
     * @param zi5
     */
    public void setZi5(String zi5) {
        this.zi5 = zi5 == null ? null : zi5.trim();
    }

    /**
     * @return zi6
     */
    public String getZi6() {
        return zi6;
    }

    /**
     * @param zi6
     */
    public void setZi6(String zi6) {
        this.zi6 = zi6 == null ? null : zi6.trim();
    }

    /**
     * @return zi7
     */
    public String getZi7() {
        return zi7;
    }

    /**
     * @param zi7
     */
    public void setZi7(String zi7) {
        this.zi7 = zi7 == null ? null : zi7.trim();
    }

    /**
     * @return zi8
     */
    public String getZi8() {
        return zi8;
    }

    /**
     * @param zi8
     */
    public void setZi8(String zi8) {
        this.zi8 = zi8 == null ? null : zi8.trim();
    }

    /**
     * @return zi9
     */
    public String getZi9() {
        return zi9;
    }

    /**
     * @param zi9
     */
    public void setZi9(String zi9) {
        this.zi9 = zi9 == null ? null : zi9.trim();
    }

    /**
     * @return zi10
     */
    public String getZi10() {
        return zi10;
    }

    /**
     * @param zi10
     */
    public void setZi10(String zi10) {
        this.zi10 = zi10 == null ? null : zi10.trim();
    }

    /**
     * @return zi11
     */
    public String getZi11() {
        return zi11;
    }

    /**
     * @param zi11
     */
    public void setZi11(String zi11) {
        this.zi11 = zi11 == null ? null : zi11.trim();
    }

    /**
     * @return zi12
     */
    public String getZi12() {
        return zi12;
    }

    /**
     * @param zi12
     */
    public void setZi12(String zi12) {
        this.zi12 = zi12 == null ? null : zi12.trim();
    }

    /**
     * @return zi13
     */
    public String getZi13() {
        return zi13;
    }

    /**
     * @param zi13
     */
    public void setZi13(String zi13) {
        this.zi13 = zi13 == null ? null : zi13.trim();
    }

    /**
     * @return zi14
     */
    public String getZi14() {
        return zi14;
    }

    /**
     * @param zi14
     */
    public void setZi14(String zi14) {
        this.zi14 = zi14 == null ? null : zi14.trim();
    }

    /**
     * @return zi15
     */
    public String getZi15() {
        return zi15;
    }

    /**
     * @param zi15
     */
    public void setZi15(String zi15) {
        this.zi15 = zi15 == null ? null : zi15.trim();
    }

    /**
     * @return zi16
     */
    public String getZi16() {
        return zi16;
    }

    /**
     * @param zi16
     */
    public void setZi16(String zi16) {
        this.zi16 = zi16 == null ? null : zi16.trim();
    }

    /**
     * @return zi17
     */
    public String getZi17() {
        return zi17;
    }

    /**
     * @param zi17
     */
    public void setZi17(String zi17) {
        this.zi17 = zi17 == null ? null : zi17.trim();
    }

    /**
     * @return zi18
     */
    public String getZi18() {
        return zi18;
    }

    /**
     * @param zi18
     */
    public void setZi18(String zi18) {
        this.zi18 = zi18 == null ? null : zi18.trim();
    }

    /**
     * @return zi19
     */
    public String getZi19() {
        return zi19;
    }

    /**
     * @param zi19
     */
    public void setZi19(String zi19) {
        this.zi19 = zi19 == null ? null : zi19.trim();
    }

    /**
     * @return zi20
     */
    public String getZi20() {
        return zi20;
    }

    /**
     * @param zi20
     */
    public void setZi20(String zi20) {
        this.zi20 = zi20 == null ? null : zi20.trim();
    }

    public String getZi21() {
        return zi21;
    }

    public void setZi21(String zi21) {
        this.zi21 = zi21;
    }

    public String getZi22() {
        return zi22;
    }

    public void setZi22(String zi22) {
        this.zi22 = zi22;
    }

    public String getZi23() {
        return zi23;
    }

    public void setZi23(String zi23) {
        this.zi23 = zi23;
    }

    public String getZi24() {
        return zi24;
    }

    public void setZi24(String zi24) {
        this.zi24 = zi24;
    }

    public String getZi25() {
        return zi25;
    }

    public void setZi25(String zi25) {
        this.zi25 = zi25;
    }

    public String getZi26() {
        return zi26;
    }

    public void setZi26(String zi26) {
        this.zi26 = zi26;
    }

    public String getZi27() {
        return zi27;
    }

    public void setZi27(String zi27) {
        this.zi27 = zi27;
    }

    public String getZi28() {
        return zi28;
    }

    public void setZi28(String zi28) {
        this.zi28 = zi28;
    }

    public String getZi29() {
        return zi29;
    }

    public void setZi29(String zi29) {
        this.zi29 = zi29;
    }

    public String getZi30() {
        return zi30;
    }

    public void setZi30(String zi30) {
        this.zi30 = zi30;
    }

    public String getZi31() {
        return zi31;
    }

    public void setZi31(String zi31) {
        this.zi31 = zi31;
    }

    public String getZi32() {
        return zi32;
    }

    public void setZi32(String zi32) {
        this.zi32 = zi32;
    }

    public String getZi33() {
        return zi33;
    }

    public void setZi33(String zi33) {
        this.zi33 = zi33;
    }

    public String getZi34() {
        return zi34;
    }

    public void setZi34(String zi34) {
        this.zi34 = zi34;
    }

    public String getZi35() {
        return zi35;
    }

    public void setZi35(String zi35) {
        this.zi35 = zi35;
    }

    public String getZi36() {
        return zi36;
    }

    public void setZi36(String zi36) {
        this.zi36 = zi36;
    }

    public String getZi37() {
        return zi37;
    }

    public void setZi37(String zi37) {
        this.zi37 = zi37;
    }

    public String getZi38() {
        return zi38;
    }

    public void setZi38(String zi38) {
        this.zi38 = zi38;
    }

    public String getZi39() {
        return zi39;
    }

    public void setZi39(String zi39) {
        this.zi39 = zi39;
    }

    public String getZi40() {
        return zi40;
    }

    public void setZi40(String zi40) {
        this.zi40 = zi40;
    }

    public String getZi41() {
        return zi41;
    }

    public void setZi41(String zi41) {
        this.zi41 = zi41;
    }

    public String getZi42() {
        return zi42;
    }

    public void setZi42(String zi42) {
        this.zi42 = zi42;
    }

    public String getZi43() {
        return zi43;
    }

    public void setZi43(String zi43) {
        this.zi43 = zi43;
    }

    public String getZi44() {
        return zi44;
    }

    public void setZi44(String zi44) {
        this.zi44 = zi44;
    }

    public String getZi45() {
        return zi45;
    }

    public void setZi45(String zi45) {
        this.zi45 = zi45;
    }

    public String getZi46() {
        return zi46;
    }

    public void setZi46(String zi46) {
        this.zi46 = zi46;
    }

    public String getZi47() {
        return zi47;
    }

    public void setZi47(String zi47) {
        this.zi47 = zi47;
    }

    public String getZi48() {
        return zi48;
    }

    public void setZi48(String zi48) {
        this.zi48 = zi48;
    }

    public String getZi49() {
        return zi49;
    }

    public void setZi49(String zi49) {
        this.zi49 = zi49;
    }

    public String getZi50() {
        return zi50;
    }

    public void setZi50(String zi50) {
        this.zi50 = zi50;
    }
}
