import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import siyi.game.bo.gamelevel.AnswerTianzi;
import siyi.game.bo.gamelevel.CandidateWordTianzi;
import siyi.game.dao.entity.Tianzi4;
import siyi.game.utill.RandomUtil;
import siyi.game.utill.ReflectOperate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestUnit {
    private static Logger log = LoggerFactory.getLogger(TestUnit.class);


    @Test
    public void test1() {
        String s = null;
        if (s == null) {
            System.out.println("s==");
        } else {
            System.out.println("===");
        }


    }

    @Test
    public void  test(){
        buildAnswer(buildTestData());
    }

    private Map<String, Object> buildAnswer(List<Tianzi4> itemList) {
        AnswerTianzi answerTianzi = new AnswerTianzi();
        CandidateWordTianzi candidate = new CandidateWordTianzi();
        int answerIndex = 1;
        for (Tianzi4 tianzi4 : itemList) {
            List<String> contents = digWord(tianzi4);
            for (String content : contents) {
                ReflectOperate.setValueByFieldName(answerTianzi, "A"+answerIndex, content);
                ReflectOperate.setValueByFieldName(candidate, "point"+(answerIndex + 99), content);
                answerIndex++;
            }
        }
        Map result = new HashMap();
        log.info("=== 挖去的字:{} ===", JSON.toJSONString(answerTianzi));
        result.put("answer", answerTianzi);
        result.put("candidate", candidate);
        return result;
    }

    private List<String> digWord(Tianzi4 tianzi4) {
        String[] emptyNum = tianzi4.getEmptyNum().split(";");
        String[] ratio = tianzi4.getEmptyNumRatio().split(";");
        List<String> content = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (emptyNum.length == ratio.length) {
            for (int i = 0; i < emptyNum.length; i++) {
                map.put(Integer.parseInt(emptyNum[i]), Integer.parseInt(ratio[i]));
            }
            int num = selectNumByWeight(map); // 挖字的数量
            List<Integer> points = new ArrayList<Integer>(); 
            for (int j = 1; j <= num; j++) {
                // 判断这个位置是否已经挖过
                int index = RandomUtil.getRandomNumInTwoIntNum(1, 4); // 挖字的位置
//                for (Integer point: points) {
//                    index = RandomUtil.getRandomNumInTwoIntNum(1, 4); // 挖字的位置
//                    if (point == index) {
//                        index = RandomUtil.getRandomNumInTwoIntNum(1, 4);
//                    }
//                    points.add(index);
//                }
                String indexValue = (String) ReflectOperate.getGetMethodValue(tianzi4, "Item" + index);
                content.add(indexValue);
            }
            return content;
        }
        return null;
    }



    private int selectNumByWeight(Map<Integer, Integer> map) {
        int index = 0;
        double amount = 0;
        int result = 0;
        Map<Integer, Integer> weightMapTemp = new HashMap<>();
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            Integer value = map.get(key);
            if (value != 0) {
                weightMapTemp.put(key, value);
                amount += value;
            }
        }
        Set<Integer> keys2 = weightMapTemp.keySet();
        for (Integer key : keys2) {
            int value = weightMapTemp.get(key);
            DecimalFormat df = new DecimalFormat("0.00");
            String percent = (int) (Double.valueOf(df.format(value / amount)) * 100) + "%";
            boolean hit = RandomUtil.isHit(percent);
            if (hit || index == keys2.size() - 1) {
                result = key;
                break;
            }
            index++;
        }
        return result;
    }

    public List<Tianzi4> buildTestData() {
        List<Tianzi4> list = new ArrayList<>();
        Tianzi4 question0 = new Tianzi4();
        question0.setQuestionId("6162");
        question0.setItem1("自");
        question0.setItem2("由");
        question0.setItem3("自");
        question0.setItem4("在");
        question0.setEmptyNum("1;2");
        question0.setEmptyNumRatio("0;100");

        Tianzi4 question1 = new Tianzi4();
        question1.setQuestionId("086");
        question1.setItem1("现");
        question1.setItem2("炒");
        question1.setItem3("现");
        question1.setItem4("卖");
        question1.setEmptyNum("1;2");
        question1.setEmptyNumRatio("0;100");

        Tianzi4 question2 = new Tianzi4();
        question2.setQuestionId("5508");
        question2.setItem1("可");
        question2.setItem2("以");
        question2.setItem3("燎");
        question2.setItem4("原");
        question2.setEmptyNum("1;2");
        question2.setEmptyNumRatio("0;100");

        Tianzi4 question3 = new Tianzi4();
        question3.setQuestionId("1510");
        question3.setItem1("急");
        question3.setItem2("中");
        question3.setItem3("生");
        question3.setItem4("智");
        question3.setEmptyNum("1;2");
        question3.setEmptyNumRatio("0;100");

        Tianzi4 question4 = new Tianzi4();
        question4.setQuestionId("916");
        question4.setItem1("高");
        question4.setItem2("高");
        question4.setItem3("在");
        question4.setItem4("上");
        question4.setEmptyNum("1;2");
        question4.setEmptyNumRatio("0;100");
        list.add(question0);
        list.add(question1);
        list.add(question2);
        list.add(question3);
        list.add(question4);
        return list;
    }
}
