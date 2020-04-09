package siyi.game.dao.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_player_sign")
public class PlayerSign implements Serializable {
    /**
     * 文武双全游戏ID
     */
    @Id
    @Column(name = "player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String playerId;

    /**
     * 第一天
     */
    private String day1;

    /**
     * 第二天
     */
    private String day2;

    /**
     * 第三天
     */
    private String day3;

    private String day4;

    private String day5;

    private String day6;

    private String day7;

    private String day8;

    private String day9;

    private String day10;

    private String day11;

    private String day12;

    private String day13;

    private String day14;

    private String day15;

    private String day16;

    private String day17;

    private String day18;

    private String day19;

    private String day20;

    private String day21;

    private String day22;

    private String day23;

    private String day24;

    private String day25;

    private String day26;

    private String day27;

    private String day28;

    private String day29;

    private String day30;

    @Column(name = "start_date")
    private Date startDate;

    /**
     * 累计签到的天数
     */
    @Column(name = "accumulate_day_num")
    private Integer accumulateDayNum;

    /**
     * 是否可以获取累积签到奖励
     */
    @Column(name = "accumulate_award_flag")
    private String accumulateAwardFlag;

    /**
     * 乐观锁
     */
    @Column(name = "REVISION")
    private Integer revision;

    /**
     * 创建人
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    /**
     * 更新时间
     */
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取文武双全游戏ID
     *
     * @return player_id - 文武双全游戏ID
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * 设置文武双全游戏ID
     *
     * @param playerId 文武双全游戏ID
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId == null ? null : playerId.trim();
    }

    /**
     * 获取第一天
     *
     * @return day1 - 第一天
     */
    public String getDay1() {
        return day1;
    }

    /**
     * 设置第一天
     *
     * @param day1 第一天
     */
    public void setDay1(String day1) {
        this.day1 = day1 == null ? null : day1.trim();
    }

    /**
     * 获取第二天
     *
     * @return day2 - 第二天
     */
    public String getDay2() {
        return day2;
    }

    /**
     * 设置第二天
     *
     * @param day2 第二天
     */
    public void setDay2(String day2) {
        this.day2 = day2 == null ? null : day2.trim();
    }

    /**
     * 获取第三天
     *
     * @return day3 - 第三天
     */
    public String getDay3() {
        return day3;
    }

    /**
     * 设置第三天
     *
     * @param day3 第三天
     */
    public void setDay3(String day3) {
        this.day3 = day3 == null ? null : day3.trim();
    }

    /**
     * @return day4
     */
    public String getDay4() {
        return day4;
    }

    /**
     * @param day4
     */
    public void setDay4(String day4) {
        this.day4 = day4 == null ? null : day4.trim();
    }

    /**
     * @return day5
     */
    public String getDay5() {
        return day5;
    }

    /**
     * @param day5
     */
    public void setDay5(String day5) {
        this.day5 = day5 == null ? null : day5.trim();
    }

    /**
     * @return day6
     */
    public String getDay6() {
        return day6;
    }

    /**
     * @param day6
     */
    public void setDay6(String day6) {
        this.day6 = day6 == null ? null : day6.trim();
    }

    /**
     * @return day7
     */
    public String getDay7() {
        return day7;
    }

    /**
     * @param day7
     */
    public void setDay7(String day7) {
        this.day7 = day7 == null ? null : day7.trim();
    }

    /**
     * @return day8
     */
    public String getDay8() {
        return day8;
    }

    /**
     * @param day8
     */
    public void setDay8(String day8) {
        this.day8 = day8 == null ? null : day8.trim();
    }

    /**
     * @return day9
     */
    public String getDay9() {
        return day9;
    }

    /**
     * @param day9
     */
    public void setDay9(String day9) {
        this.day9 = day9 == null ? null : day9.trim();
    }

    /**
     * @return day10
     */
    public String getDay10() {
        return day10;
    }

    /**
     * @param day10
     */
    public void setDay10(String day10) {
        this.day10 = day10 == null ? null : day10.trim();
    }

    /**
     * @return day11
     */
    public String getDay11() {
        return day11;
    }

    /**
     * @param day11
     */
    public void setDay11(String day11) {
        this.day11 = day11 == null ? null : day11.trim();
    }

    /**
     * @return day12
     */
    public String getDay12() {
        return day12;
    }

    /**
     * @param day12
     */
    public void setDay12(String day12) {
        this.day12 = day12 == null ? null : day12.trim();
    }

    /**
     * @return day13
     */
    public String getDay13() {
        return day13;
    }

    /**
     * @param day13
     */
    public void setDay13(String day13) {
        this.day13 = day13 == null ? null : day13.trim();
    }

    /**
     * @return day14
     */
    public String getDay14() {
        return day14;
    }

    /**
     * @param day14
     */
    public void setDay14(String day14) {
        this.day14 = day14 == null ? null : day14.trim();
    }

    /**
     * @return day15
     */
    public String getDay15() {
        return day15;
    }

    /**
     * @param day15
     */
    public void setDay15(String day15) {
        this.day15 = day15 == null ? null : day15.trim();
    }

    /**
     * @return day16
     */
    public String getDay16() {
        return day16;
    }

    /**
     * @param day16
     */
    public void setDay16(String day16) {
        this.day16 = day16 == null ? null : day16.trim();
    }

    /**
     * @return day17
     */
    public String getDay17() {
        return day17;
    }

    /**
     * @param day17
     */
    public void setDay17(String day17) {
        this.day17 = day17 == null ? null : day17.trim();
    }

    /**
     * @return day18
     */
    public String getDay18() {
        return day18;
    }

    /**
     * @param day18
     */
    public void setDay18(String day18) {
        this.day18 = day18 == null ? null : day18.trim();
    }

    /**
     * @return day19
     */
    public String getDay19() {
        return day19;
    }

    /**
     * @param day19
     */
    public void setDay19(String day19) {
        this.day19 = day19 == null ? null : day19.trim();
    }

    /**
     * @return day20
     */
    public String getDay20() {
        return day20;
    }

    /**
     * @param day20
     */
    public void setDay20(String day20) {
        this.day20 = day20 == null ? null : day20.trim();
    }

    /**
     * @return day21
     */
    public String getDay21() {
        return day21;
    }

    /**
     * @param day21
     */
    public void setDay21(String day21) {
        this.day21 = day21 == null ? null : day21.trim();
    }

    /**
     * @return day22
     */
    public String getDay22() {
        return day22;
    }

    /**
     * @param day22
     */
    public void setDay22(String day22) {
        this.day22 = day22 == null ? null : day22.trim();
    }

    /**
     * @return day23
     */
    public String getDay23() {
        return day23;
    }

    /**
     * @param day23
     */
    public void setDay23(String day23) {
        this.day23 = day23 == null ? null : day23.trim();
    }

    /**
     * @return day24
     */
    public String getDay24() {
        return day24;
    }

    /**
     * @param day24
     */
    public void setDay24(String day24) {
        this.day24 = day24 == null ? null : day24.trim();
    }

    /**
     * @return day25
     */
    public String getDay25() {
        return day25;
    }

    /**
     * @param day25
     */
    public void setDay25(String day25) {
        this.day25 = day25 == null ? null : day25.trim();
    }

    /**
     * @return day26
     */
    public String getDay26() {
        return day26;
    }

    /**
     * @param day26
     */
    public void setDay26(String day26) {
        this.day26 = day26 == null ? null : day26.trim();
    }

    /**
     * @return day27
     */
    public String getDay27() {
        return day27;
    }

    /**
     * @param day27
     */
    public void setDay27(String day27) {
        this.day27 = day27 == null ? null : day27.trim();
    }

    /**
     * @return day28
     */
    public String getDay28() {
        return day28;
    }

    /**
     * @param day28
     */
    public void setDay28(String day28) {
        this.day28 = day28 == null ? null : day28.trim();
    }

    /**
     * @return day29
     */
    public String getDay29() {
        return day29;
    }

    /**
     * @param day29
     */
    public void setDay29(String day29) {
        this.day29 = day29 == null ? null : day29.trim();
    }

    /**
     * @return day30
     */
    public String getDay30() {
        return day30;
    }

    /**
     * @param day30
     */
    public void setDay30(String day30) {
        this.day30 = day30 == null ? null : day30.trim();
    }

    /**
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取累计签到的天数
     *
     * @return accumulate_day_num - 累计签到的天数
     */
    public Integer getAccumulateDayNum() {
        return accumulateDayNum;
    }

    /**
     * 设置累计签到的天数
     *
     * @param accumulateDayNum 累计签到的天数
     */
    public void setAccumulateDayNum(Integer accumulateDayNum) {
        this.accumulateDayNum = accumulateDayNum;
    }

    /**
     * 获取是否可以获取累积签到奖励
     *
     * @return accumulate_award_flag - 是否可以获取累积签到奖励
     */
    public String getAccumulateAwardFlag() {
        return accumulateAwardFlag;
    }

    /**
     * 设置是否可以获取累积签到奖励
     *
     * @param accumulateAwardFlag 是否可以获取累积签到奖励
     */
    public void setAccumulateAwardFlag(String accumulateAwardFlag) {
        this.accumulateAwardFlag = accumulateAwardFlag == null ? null : accumulateAwardFlag.trim();
    }

    /**
     * 获取乐观锁
     *
     * @return REVISION - 乐观锁
     */
    public Integer getRevision() {
        return revision;
    }

    /**
     * 设置乐观锁
     *
     * @param revision 乐观锁
     */
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    /**
     * 获取创建人
     *
     * @return CREATED_BY - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CREATED_TIME - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新人
     *
     * @return UPDATED_BY - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * 获取更新时间
     *
     * @return UPDATED_TIME - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}