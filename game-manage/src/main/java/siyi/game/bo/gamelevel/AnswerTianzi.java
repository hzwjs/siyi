package siyi.game.bo.gamelevel;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import lombok.Data;

/**
 * 答案对象
 */
@Data
public class AnswerTianzi {
    @JsonProperty(value = "A1")
    @Mapping("point100")
    private String A1;

    @JsonProperty(value = "A2")
    @Mapping("point101")
    private String A2;

    @JsonProperty(value = "A3")
    @Mapping("point102")
    private String A3;

    @JsonProperty(value = "A4")
    @Mapping("point103")
    private String A4;

    @JsonProperty(value = "A5")
    @Mapping("point104")
    private String A5;

    @JsonProperty(value = "A6")
    @Mapping("point105")
    private String A6;


    @JsonProperty(value = "A7")
    @Mapping("point106")
    private String A7;

    @JsonProperty(value = "A8")
    @Mapping("point107")
    private String A8;

    @JsonProperty(value = "A9")
    @Mapping("point108")
    private String A9;

    @JsonProperty(value = "A10")
    @Mapping("point109")
    private String A10;

    @JsonProperty(value = "A11")
    @Mapping("point110")
    private String A11;

    @JsonProperty(value = "A12")
    @Mapping("point111")
    private String A12;

    @JsonProperty(value = "A13")
    @Mapping("point112")
    private String A13;

    @JsonProperty(value = "A14")
    @Mapping("point113")
    private String A14;

    @JsonProperty(value = "A15")
    @Mapping("point114")
    private String A15;

    @JsonProperty(value = "A16")
    @Mapping("point115")
    private String A16;

    @JsonProperty(value = "A17")
    @Mapping("point116")
    private String A17;

    @JsonProperty(value = "A18")
    @Mapping("point117")
    private String A18;

    @JsonProperty(value = "A19")
    @Mapping("point118")
    private String A19;


    @Mapping("point119")
    @JsonProperty(value = "A20")
    private String A20;
}
