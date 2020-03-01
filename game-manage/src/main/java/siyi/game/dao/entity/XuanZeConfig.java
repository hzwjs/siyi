package siyi.game.dao.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "tb_q_xuanze_config")
public class XuanZeConfig implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String onoff;

    private String point1;

    private String point2;

    private String point3;

    private String point4;

    private String point5;

    private String point6;

    private String answer;

    private String tips;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return onoff
     */
    public String getOnoff() {
        return onoff;
    }

    /**
     * @param onoff
     */
    public void setOnoff(String onoff) {
        this.onoff = onoff == null ? null : onoff.trim();
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

    /**
     * @return tips
     */
    public String getTips() {
        return tips;
    }

    /**
     * @param tips
     */
    public void setTips(String tips) {
        this.tips = tips == null ? null : tips.trim();
    }
}