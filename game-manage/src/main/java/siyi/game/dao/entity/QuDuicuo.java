package siyi.game.dao.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_qu_duicuo")
@Data
public class QuDuicuo implements Serializable {
    @Id
    @Column(name = "qu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String quId;

    @Column(name = "qu_status")
    private String quStatus;

    private String point0;

    private String point1;

    private String point2;

    private String point3;

    private String point4;

    private String point5;

    private String point6;

    private String point7;

    private String point8;

    private String point9;

    private String point10;

    private String point11;

    private String point12;

    private String point13;

    private String point14;

    private String point15;

    private String point16;

    private String point17;

    private String point18;

    private String point19;

    private String point20;

    private String point21;

    private String point22;

    private String point23;

    private String point24;

    private String point25;

    private String point26;

    private String point27;

    private String point28;

    private String point29;

    private String point30;

    private String point31;

    private String point32;

    private String point33;

    private String point34;

    private String point35;

    private String point36;

    private String point37;

    private String point38;

    private String point39;

    private String point40;

    private String point41;

    private String point42;

    private String point43;

    private String point44;

    private String point45;

    private String point46;

    private String point47;

    private String point48;

    private String point49;

    private String point50;

    private String point51;

    private String point52;

    private String point53;

    private String point54;

    private String point55;

    private String point56;

    private String point57;

    private String point58;

    private String point59;

    private String point60;

    private String point61;

    private String point62;

    private String point63;

    private String point64;

    private String point65;

    private String point66;

    private String point67;

    private String point68;

    private String point69;

    private String point70;

    private String point71;

    private String point72;

    private String point73;

    private String point74;

    private String point75;

    private String point76;

    private String point77;

    private String point78;

    private String point79;

    private String point80;

    private String point81;

    private String point82;

    private String point83;

    private String point84;

    private String point85;

    private String point86;

    private String point87;

    private String point88;

    private String point89;

    private String point90;

    private String point91;

    private String point92;

    private String point93;

    private String point94;

    private String point95;

    private String point96;

    private String point97;

    private String point98;

    private String point99;

    private String answer;

    private static final long serialVersionUID = 1L;

    /**
     * @return qu_id
     */
    public String getQuId() {
        return quId;
    }

    /**
     * @param quId
     */
    public void setQuId(String quId) {
        this.quId = quId == null ? null : quId.trim();
    }

    /**
     * @return qu_status
     */
    public String getQuStatus() {
        return quStatus;
    }

    /**
     * @param quStatus
     */
    public void setQuStatus(String quStatus) {
        this.quStatus = quStatus == null ? null : quStatus.trim();
    }

    /**
     * @return point0
     */
    public String getPoint0() {
        return point0;
    }

    /**
     * @param point0
     */
    public void setPoint0(String point0) {
        this.point0 = point0 == null ? null : point0.trim();
    }

    /**
     * @return point1
     */
    public String getPoint1() {
        return point1;
    }

    /**
     * @param point1
     */
    public void setPoint1(String point1) {
        this.point1 = point1 == null ? null : point1.trim();
    }

    /**
     * @return point2
     */
    public String getPoint2() {
        return point2;
    }

    /**
     * @param point2
     */
    public void setPoint2(String point2) {
        this.point2 = point2 == null ? null : point2.trim();
    }

    /**
     * @return point3
     */
    public String getPoint3() {
        return point3;
    }

    /**
     * @param point3
     */
    public void setPoint3(String point3) {
        this.point3 = point3 == null ? null : point3.trim();
    }

    /**
     * @return point4
     */
    public String getPoint4() {
        return point4;
    }

    /**
     * @param point4
     */
    public void setPoint4(String point4) {
        this.point4 = point4 == null ? null : point4.trim();
    }

    /**
     * @return point5
     */
    public String getPoint5() {
        return point5;
    }

    /**
     * @param point5
     */
    public void setPoint5(String point5) {
        this.point5 = point5 == null ? null : point5.trim();
    }

    /**
     * @return point6
     */
    public String getPoint6() {
        return point6;
    }

    /**
     * @param point6
     */
    public void setPoint6(String point6) {
        this.point6 = point6 == null ? null : point6.trim();
    }

    /**
     * @return point7
     */
    public String getPoint7() {
        return point7;
    }

    /**
     * @param point7
     */
    public void setPoint7(String point7) {
        this.point7 = point7 == null ? null : point7.trim();
    }

    /**
     * @return point8
     */
    public String getPoint8() {
        return point8;
    }

    /**
     * @param point8
     */
    public void setPoint8(String point8) {
        this.point8 = point8 == null ? null : point8.trim();
    }

    /**
     * @return point9
     */
    public String getPoint9() {
        return point9;
    }

    /**
     * @param point9
     */
    public void setPoint9(String point9) {
        this.point9 = point9 == null ? null : point9.trim();
    }

    /**
     * @return point10
     */
    public String getPoint10() {
        return point10;
    }

    /**
     * @param point10
     */
    public void setPoint10(String point10) {
        this.point10 = point10 == null ? null : point10.trim();
    }

    /**
     * @return point11
     */
    public String getPoint11() {
        return point11;
    }

    /**
     * @param point11
     */
    public void setPoint11(String point11) {
        this.point11 = point11 == null ? null : point11.trim();
    }

    /**
     * @return point12
     */
    public String getPoint12() {
        return point12;
    }

    /**
     * @param point12
     */
    public void setPoint12(String point12) {
        this.point12 = point12 == null ? null : point12.trim();
    }

    /**
     * @return point13
     */
    public String getPoint13() {
        return point13;
    }

    /**
     * @param point13
     */
    public void setPoint13(String point13) {
        this.point13 = point13 == null ? null : point13.trim();
    }

    /**
     * @return point14
     */
    public String getPoint14() {
        return point14;
    }

    /**
     * @param point14
     */
    public void setPoint14(String point14) {
        this.point14 = point14 == null ? null : point14.trim();
    }

    /**
     * @return point15
     */
    public String getPoint15() {
        return point15;
    }

    /**
     * @param point15
     */
    public void setPoint15(String point15) {
        this.point15 = point15 == null ? null : point15.trim();
    }

    /**
     * @return point16
     */
    public String getPoint16() {
        return point16;
    }

    /**
     * @param point16
     */
    public void setPoint16(String point16) {
        this.point16 = point16 == null ? null : point16.trim();
    }

    /**
     * @return point17
     */
    public String getPoint17() {
        return point17;
    }

    /**
     * @param point17
     */
    public void setPoint17(String point17) {
        this.point17 = point17 == null ? null : point17.trim();
    }

    /**
     * @return point18
     */
    public String getPoint18() {
        return point18;
    }

    /**
     * @param point18
     */
    public void setPoint18(String point18) {
        this.point18 = point18 == null ? null : point18.trim();
    }

    /**
     * @return point19
     */
    public String getPoint19() {
        return point19;
    }

    /**
     * @param point19
     */
    public void setPoint19(String point19) {
        this.point19 = point19 == null ? null : point19.trim();
    }

    /**
     * @return point20
     */
    public String getPoint20() {
        return point20;
    }

    /**
     * @param point20
     */
    public void setPoint20(String point20) {
        this.point20 = point20 == null ? null : point20.trim();
    }

    /**
     * @return point21
     */
    public String getPoint21() {
        return point21;
    }

    /**
     * @param point21
     */
    public void setPoint21(String point21) {
        this.point21 = point21 == null ? null : point21.trim();
    }

    /**
     * @return point22
     */
    public String getPoint22() {
        return point22;
    }

    /**
     * @param point22
     */
    public void setPoint22(String point22) {
        this.point22 = point22 == null ? null : point22.trim();
    }

    /**
     * @return point23
     */
    public String getPoint23() {
        return point23;
    }

    /**
     * @param point23
     */
    public void setPoint23(String point23) {
        this.point23 = point23 == null ? null : point23.trim();
    }

    /**
     * @return point24
     */
    public String getPoint24() {
        return point24;
    }

    /**
     * @param point24
     */
    public void setPoint24(String point24) {
        this.point24 = point24 == null ? null : point24.trim();
    }

    /**
     * @return point25
     */
    public String getPoint25() {
        return point25;
    }

    /**
     * @param point25
     */
    public void setPoint25(String point25) {
        this.point25 = point25 == null ? null : point25.trim();
    }

    /**
     * @return point26
     */
    public String getPoint26() {
        return point26;
    }

    /**
     * @param point26
     */
    public void setPoint26(String point26) {
        this.point26 = point26 == null ? null : point26.trim();
    }

    /**
     * @return point27
     */
    public String getPoint27() {
        return point27;
    }

    /**
     * @param point27
     */
    public void setPoint27(String point27) {
        this.point27 = point27 == null ? null : point27.trim();
    }

    /**
     * @return point28
     */
    public String getPoint28() {
        return point28;
    }

    /**
     * @param point28
     */
    public void setPoint28(String point28) {
        this.point28 = point28 == null ? null : point28.trim();
    }

    /**
     * @return point29
     */
    public String getPoint29() {
        return point29;
    }

    /**
     * @param point29
     */
    public void setPoint29(String point29) {
        this.point29 = point29 == null ? null : point29.trim();
    }

    /**
     * @return point30
     */
    public String getPoint30() {
        return point30;
    }

    /**
     * @param point30
     */
    public void setPoint30(String point30) {
        this.point30 = point30 == null ? null : point30.trim();
    }

    /**
     * @return point31
     */
    public String getPoint31() {
        return point31;
    }

    /**
     * @param point31
     */
    public void setPoint31(String point31) {
        this.point31 = point31 == null ? null : point31.trim();
    }

    /**
     * @return point32
     */
    public String getPoint32() {
        return point32;
    }

    /**
     * @param point32
     */
    public void setPoint32(String point32) {
        this.point32 = point32 == null ? null : point32.trim();
    }

    /**
     * @return point33
     */
    public String getPoint33() {
        return point33;
    }

    /**
     * @param point33
     */
    public void setPoint33(String point33) {
        this.point33 = point33 == null ? null : point33.trim();
    }

    /**
     * @return point34
     */
    public String getPoint34() {
        return point34;
    }

    /**
     * @param point34
     */
    public void setPoint34(String point34) {
        this.point34 = point34 == null ? null : point34.trim();
    }

    /**
     * @return point35
     */
    public String getPoint35() {
        return point35;
    }

    /**
     * @param point35
     */
    public void setPoint35(String point35) {
        this.point35 = point35 == null ? null : point35.trim();
    }

    /**
     * @return point36
     */
    public String getPoint36() {
        return point36;
    }

    /**
     * @param point36
     */
    public void setPoint36(String point36) {
        this.point36 = point36 == null ? null : point36.trim();
    }

    /**
     * @return point37
     */
    public String getPoint37() {
        return point37;
    }

    /**
     * @param point37
     */
    public void setPoint37(String point37) {
        this.point37 = point37 == null ? null : point37.trim();
    }

    /**
     * @return point38
     */
    public String getPoint38() {
        return point38;
    }

    /**
     * @param point38
     */
    public void setPoint38(String point38) {
        this.point38 = point38 == null ? null : point38.trim();
    }

    /**
     * @return point39
     */
    public String getPoint39() {
        return point39;
    }

    /**
     * @param point39
     */
    public void setPoint39(String point39) {
        this.point39 = point39 == null ? null : point39.trim();
    }

    /**
     * @return point40
     */
    public String getPoint40() {
        return point40;
    }

    /**
     * @param point40
     */
    public void setPoint40(String point40) {
        this.point40 = point40 == null ? null : point40.trim();
    }

    /**
     * @return point41
     */
    public String getPoint41() {
        return point41;
    }

    /**
     * @param point41
     */
    public void setPoint41(String point41) {
        this.point41 = point41 == null ? null : point41.trim();
    }

    /**
     * @return point42
     */
    public String getPoint42() {
        return point42;
    }

    /**
     * @param point42
     */
    public void setPoint42(String point42) {
        this.point42 = point42 == null ? null : point42.trim();
    }

    /**
     * @return point43
     */
    public String getPoint43() {
        return point43;
    }

    /**
     * @param point43
     */
    public void setPoint43(String point43) {
        this.point43 = point43 == null ? null : point43.trim();
    }

    /**
     * @return point44
     */
    public String getPoint44() {
        return point44;
    }

    /**
     * @param point44
     */
    public void setPoint44(String point44) {
        this.point44 = point44 == null ? null : point44.trim();
    }

    /**
     * @return point45
     */
    public String getPoint45() {
        return point45;
    }

    /**
     * @param point45
     */
    public void setPoint45(String point45) {
        this.point45 = point45 == null ? null : point45.trim();
    }

    /**
     * @return point46
     */
    public String getPoint46() {
        return point46;
    }

    /**
     * @param point46
     */
    public void setPoint46(String point46) {
        this.point46 = point46 == null ? null : point46.trim();
    }

    /**
     * @return point47
     */
    public String getPoint47() {
        return point47;
    }

    /**
     * @param point47
     */
    public void setPoint47(String point47) {
        this.point47 = point47 == null ? null : point47.trim();
    }

    /**
     * @return point48
     */
    public String getPoint48() {
        return point48;
    }

    /**
     * @param point48
     */
    public void setPoint48(String point48) {
        this.point48 = point48 == null ? null : point48.trim();
    }

    /**
     * @return point49
     */
    public String getPoint49() {
        return point49;
    }

    /**
     * @param point49
     */
    public void setPoint49(String point49) {
        this.point49 = point49 == null ? null : point49.trim();
    }

    /**
     * @return point50
     */
    public String getPoint50() {
        return point50;
    }

    /**
     * @param point50
     */
    public void setPoint50(String point50) {
        this.point50 = point50 == null ? null : point50.trim();
    }

    /**
     * @return point51
     */
    public String getPoint51() {
        return point51;
    }

    /**
     * @param point51
     */
    public void setPoint51(String point51) {
        this.point51 = point51 == null ? null : point51.trim();
    }

    /**
     * @return point52
     */
    public String getPoint52() {
        return point52;
    }

    /**
     * @param point52
     */
    public void setPoint52(String point52) {
        this.point52 = point52 == null ? null : point52.trim();
    }

    /**
     * @return point53
     */
    public String getPoint53() {
        return point53;
    }

    /**
     * @param point53
     */
    public void setPoint53(String point53) {
        this.point53 = point53 == null ? null : point53.trim();
    }

    /**
     * @return point54
     */
    public String getPoint54() {
        return point54;
    }

    /**
     * @param point54
     */
    public void setPoint54(String point54) {
        this.point54 = point54 == null ? null : point54.trim();
    }

    /**
     * @return point55
     */
    public String getPoint55() {
        return point55;
    }

    /**
     * @param point55
     */
    public void setPoint55(String point55) {
        this.point55 = point55 == null ? null : point55.trim();
    }

    /**
     * @return point56
     */
    public String getPoint56() {
        return point56;
    }

    /**
     * @param point56
     */
    public void setPoint56(String point56) {
        this.point56 = point56 == null ? null : point56.trim();
    }

    /**
     * @return point57
     */
    public String getPoint57() {
        return point57;
    }

    /**
     * @param point57
     */
    public void setPoint57(String point57) {
        this.point57 = point57 == null ? null : point57.trim();
    }

    /**
     * @return point58
     */
    public String getPoint58() {
        return point58;
    }

    /**
     * @param point58
     */
    public void setPoint58(String point58) {
        this.point58 = point58 == null ? null : point58.trim();
    }

    /**
     * @return point59
     */
    public String getPoint59() {
        return point59;
    }

    /**
     * @param point59
     */
    public void setPoint59(String point59) {
        this.point59 = point59 == null ? null : point59.trim();
    }

    /**
     * @return point60
     */
    public String getPoint60() {
        return point60;
    }

    /**
     * @param point60
     */
    public void setPoint60(String point60) {
        this.point60 = point60 == null ? null : point60.trim();
    }

    /**
     * @return point61
     */
    public String getPoint61() {
        return point61;
    }

    /**
     * @param point61
     */
    public void setPoint61(String point61) {
        this.point61 = point61 == null ? null : point61.trim();
    }

    /**
     * @return point62
     */
    public String getPoint62() {
        return point62;
    }

    /**
     * @param point62
     */
    public void setPoint62(String point62) {
        this.point62 = point62 == null ? null : point62.trim();
    }

    /**
     * @return point63
     */
    public String getPoint63() {
        return point63;
    }

    /**
     * @param point63
     */
    public void setPoint63(String point63) {
        this.point63 = point63 == null ? null : point63.trim();
    }

    /**
     * @return point64
     */
    public String getPoint64() {
        return point64;
    }

    /**
     * @param point64
     */
    public void setPoint64(String point64) {
        this.point64 = point64 == null ? null : point64.trim();
    }

    /**
     * @return point65
     */
    public String getPoint65() {
        return point65;
    }

    /**
     * @param point65
     */
    public void setPoint65(String point65) {
        this.point65 = point65 == null ? null : point65.trim();
    }

    /**
     * @return point66
     */
    public String getPoint66() {
        return point66;
    }

    /**
     * @param point66
     */
    public void setPoint66(String point66) {
        this.point66 = point66 == null ? null : point66.trim();
    }

    /**
     * @return point67
     */
    public String getPoint67() {
        return point67;
    }

    /**
     * @param point67
     */
    public void setPoint67(String point67) {
        this.point67 = point67 == null ? null : point67.trim();
    }

    /**
     * @return point68
     */
    public String getPoint68() {
        return point68;
    }

    /**
     * @param point68
     */
    public void setPoint68(String point68) {
        this.point68 = point68 == null ? null : point68.trim();
    }

    /**
     * @return point69
     */
    public String getPoint69() {
        return point69;
    }

    /**
     * @param point69
     */
    public void setPoint69(String point69) {
        this.point69 = point69 == null ? null : point69.trim();
    }

    /**
     * @return point70
     */
    public String getPoint70() {
        return point70;
    }

    /**
     * @param point70
     */
    public void setPoint70(String point70) {
        this.point70 = point70 == null ? null : point70.trim();
    }

    /**
     * @return point71
     */
    public String getPoint71() {
        return point71;
    }

    /**
     * @param point71
     */
    public void setPoint71(String point71) {
        this.point71 = point71 == null ? null : point71.trim();
    }

    /**
     * @return point72
     */
    public String getPoint72() {
        return point72;
    }

    /**
     * @param point72
     */
    public void setPoint72(String point72) {
        this.point72 = point72 == null ? null : point72.trim();
    }

    /**
     * @return point73
     */
    public String getPoint73() {
        return point73;
    }

    /**
     * @param point73
     */
    public void setPoint73(String point73) {
        this.point73 = point73 == null ? null : point73.trim();
    }

    /**
     * @return point74
     */
    public String getPoint74() {
        return point74;
    }

    /**
     * @param point74
     */
    public void setPoint74(String point74) {
        this.point74 = point74 == null ? null : point74.trim();
    }

    /**
     * @return point75
     */
    public String getPoint75() {
        return point75;
    }

    /**
     * @param point75
     */
    public void setPoint75(String point75) {
        this.point75 = point75 == null ? null : point75.trim();
    }

    /**
     * @return point76
     */
    public String getPoint76() {
        return point76;
    }

    /**
     * @param point76
     */
    public void setPoint76(String point76) {
        this.point76 = point76 == null ? null : point76.trim();
    }

    /**
     * @return point77
     */
    public String getPoint77() {
        return point77;
    }

    /**
     * @param point77
     */
    public void setPoint77(String point77) {
        this.point77 = point77 == null ? null : point77.trim();
    }

    /**
     * @return point78
     */
    public String getPoint78() {
        return point78;
    }

    /**
     * @param point78
     */
    public void setPoint78(String point78) {
        this.point78 = point78 == null ? null : point78.trim();
    }

    /**
     * @return point79
     */
    public String getPoint79() {
        return point79;
    }

    /**
     * @param point79
     */
    public void setPoint79(String point79) {
        this.point79 = point79 == null ? null : point79.trim();
    }

    /**
     * @return point80
     */
    public String getPoint80() {
        return point80;
    }

    /**
     * @param point80
     */
    public void setPoint80(String point80) {
        this.point80 = point80 == null ? null : point80.trim();
    }

    /**
     * @return point81
     */
    public String getPoint81() {
        return point81;
    }

    /**
     * @param point81
     */
    public void setPoint81(String point81) {
        this.point81 = point81 == null ? null : point81.trim();
    }

    /**
     * @return point82
     */
    public String getPoint82() {
        return point82;
    }

    /**
     * @param point82
     */
    public void setPoint82(String point82) {
        this.point82 = point82 == null ? null : point82.trim();
    }

    /**
     * @return point83
     */
    public String getPoint83() {
        return point83;
    }

    /**
     * @param point83
     */
    public void setPoint83(String point83) {
        this.point83 = point83 == null ? null : point83.trim();
    }

    /**
     * @return point84
     */
    public String getPoint84() {
        return point84;
    }

    /**
     * @param point84
     */
    public void setPoint84(String point84) {
        this.point84 = point84 == null ? null : point84.trim();
    }

    /**
     * @return point85
     */
    public String getPoint85() {
        return point85;
    }

    /**
     * @param point85
     */
    public void setPoint85(String point85) {
        this.point85 = point85 == null ? null : point85.trim();
    }

    /**
     * @return point86
     */
    public String getPoint86() {
        return point86;
    }

    /**
     * @param point86
     */
    public void setPoint86(String point86) {
        this.point86 = point86 == null ? null : point86.trim();
    }

    /**
     * @return point87
     */
    public String getPoint87() {
        return point87;
    }

    /**
     * @param point87
     */
    public void setPoint87(String point87) {
        this.point87 = point87 == null ? null : point87.trim();
    }

    /**
     * @return point88
     */
    public String getPoint88() {
        return point88;
    }

    /**
     * @param point88
     */
    public void setPoint88(String point88) {
        this.point88 = point88 == null ? null : point88.trim();
    }

    /**
     * @return point89
     */
    public String getPoint89() {
        return point89;
    }

    /**
     * @param point89
     */
    public void setPoint89(String point89) {
        this.point89 = point89 == null ? null : point89.trim();
    }

    /**
     * @return point90
     */
    public String getPoint90() {
        return point90;
    }

    /**
     * @param point90
     */
    public void setPoint90(String point90) {
        this.point90 = point90 == null ? null : point90.trim();
    }

    /**
     * @return point91
     */
    public String getPoint91() {
        return point91;
    }

    /**
     * @param point91
     */
    public void setPoint91(String point91) {
        this.point91 = point91 == null ? null : point91.trim();
    }

    /**
     * @return point92
     */
    public String getPoint92() {
        return point92;
    }

    /**
     * @param point92
     */
    public void setPoint92(String point92) {
        this.point92 = point92 == null ? null : point92.trim();
    }

    /**
     * @return point93
     */
    public String getPoint93() {
        return point93;
    }

    /**
     * @param point93
     */
    public void setPoint93(String point93) {
        this.point93 = point93 == null ? null : point93.trim();
    }

    /**
     * @return point94
     */
    public String getPoint94() {
        return point94;
    }

    /**
     * @param point94
     */
    public void setPoint94(String point94) {
        this.point94 = point94 == null ? null : point94.trim();
    }

    /**
     * @return point95
     */
    public String getPoint95() {
        return point95;
    }

    /**
     * @param point95
     */
    public void setPoint95(String point95) {
        this.point95 = point95 == null ? null : point95.trim();
    }

    /**
     * @return point96
     */
    public String getPoint96() {
        return point96;
    }

    /**
     * @param point96
     */
    public void setPoint96(String point96) {
        this.point96 = point96 == null ? null : point96.trim();
    }

    /**
     * @return point97
     */
    public String getPoint97() {
        return point97;
    }

    /**
     * @param point97
     */
    public void setPoint97(String point97) {
        this.point97 = point97 == null ? null : point97.trim();
    }

    /**
     * @return point98
     */
    public String getPoint98() {
        return point98;
    }

    /**
     * @param point98
     */
    public void setPoint98(String point98) {
        this.point98 = point98 == null ? null : point98.trim();
    }

    /**
     * @return point99
     */
    public String getPoint99() {
        return point99;
    }

    /**
     * @param point99
     */
    public void setPoint99(String point99) {
        this.point99 = point99 == null ? null : point99.trim();
    }

    /**
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }
}