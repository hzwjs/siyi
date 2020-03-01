package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_level_up_config")
public class LevelUpConfig implements Serializable {
    /**
     * 等级
     */
    @Column(name = "LEVEL")
    private String level;

    /**
     * 角色攻击力
     */
    private String jueseap;

    /**
     * 武器攻击力
     */
    private String wuqiap;

    /**
     * 文称号
     */
    private String chenghao;

    /**
     * 文关加成
     */
    private String jiahceng1;

    /**
     * 武称号
     */
    private String chenghao2;

    /**
     * 武关加成
     */
    private String jiacheng2;

    /**
     * 所需经验
     */
    private String exp;

    /**
     * 体力上限值
     */
    private String tilimax;

    /**
     * 体力恢复值
     */
    private String tilihuifu;

    private String tap;

    private static final long serialVersionUID = 1L;

    /**
     * 获取等级
     *
     * @return LEVEL - 等级
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置等级
     *
     * @param level 等级
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * 获取角色攻击力
     *
     * @return jueseap - 角色攻击力
     */
    public String getJueseap() {
        return jueseap;
    }

    /**
     * 设置角色攻击力
     *
     * @param jueseap 角色攻击力
     */
    public void setJueseap(String jueseap) {
        this.jueseap = jueseap == null ? null : jueseap.trim();
    }

    /**
     * 获取武器攻击力
     *
     * @return wuqiap - 武器攻击力
     */
    public String getWuqiap() {
        return wuqiap;
    }

    /**
     * 设置武器攻击力
     *
     * @param wuqiap 武器攻击力
     */
    public void setWuqiap(String wuqiap) {
        this.wuqiap = wuqiap == null ? null : wuqiap.trim();
    }

    /**
     * 获取文称号
     *
     * @return chenghao - 文称号
     */
    public String getChenghao() {
        return chenghao;
    }

    /**
     * 设置文称号
     *
     * @param chenghao 文称号
     */
    public void setChenghao(String chenghao) {
        this.chenghao = chenghao == null ? null : chenghao.trim();
    }

    /**
     * 获取文关加成
     *
     * @return jiahceng1 - 文关加成
     */
    public String getJiahceng1() {
        return jiahceng1;
    }

    /**
     * 设置文关加成
     *
     * @param jiahceng1 文关加成
     */
    public void setJiahceng1(String jiahceng1) {
        this.jiahceng1 = jiahceng1 == null ? null : jiahceng1.trim();
    }

    /**
     * 获取武称号
     *
     * @return chenghao2 - 武称号
     */
    public String getChenghao2() {
        return chenghao2;
    }

    /**
     * 设置武称号
     *
     * @param chenghao2 武称号
     */
    public void setChenghao2(String chenghao2) {
        this.chenghao2 = chenghao2 == null ? null : chenghao2.trim();
    }

    /**
     * 获取武关加成
     *
     * @return jiacheng2 - 武关加成
     */
    public String getJiacheng2() {
        return jiacheng2;
    }

    /**
     * 设置武关加成
     *
     * @param jiacheng2 武关加成
     */
    public void setJiacheng2(String jiacheng2) {
        this.jiacheng2 = jiacheng2 == null ? null : jiacheng2.trim();
    }

    /**
     * 获取所需经验
     *
     * @return exp - 所需经验
     */
    public String getExp() {
        return exp;
    }

    /**
     * 设置所需经验
     *
     * @param exp 所需经验
     */
    public void setExp(String exp) {
        this.exp = exp == null ? null : exp.trim();
    }

    /**
     * 获取体力上限值
     *
     * @return tilimax - 体力上限值
     */
    public String getTilimax() {
        return tilimax;
    }

    /**
     * 设置体力上限值
     *
     * @param tilimax 体力上限值
     */
    public void setTilimax(String tilimax) {
        this.tilimax = tilimax == null ? null : tilimax.trim();
    }

    /**
     * 获取体力恢复值
     *
     * @return tilihuifu - 体力恢复值
     */
    public String getTilihuifu() {
        return tilihuifu;
    }

    /**
     * 设置体力恢复值
     *
     * @param tilihuifu 体力恢复值
     */
    public void setTilihuifu(String tilihuifu) {
        this.tilihuifu = tilihuifu == null ? null : tilihuifu.trim();
    }

    /**
     * @return tap
     */
    public String getTap() {
        return tap;
    }

    /**
     * @param tap
     */
    public void setTap(String tap) {
        this.tap = tap == null ? null : tap.trim();
    }
}