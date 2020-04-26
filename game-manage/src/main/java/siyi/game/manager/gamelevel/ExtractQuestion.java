package siyi.game.manager.gamelevel;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.bo.gamelevel.AnswerTianzi;
import siyi.game.bo.gamelevel.CandidateWordTianzi;
import siyi.game.bo.gamelevel.QuestionTianzi;
import siyi.game.dao.Tianzi4Mapper;
import siyi.game.dao.Tianzi5Mapper;
import siyi.game.dao.Tianzi7Mapper;
import siyi.game.dao.entity.QuTianzi;
import siyi.game.dao.entity.Tianzi4;
import siyi.game.dao.entity.Tianzi5;
import siyi.game.dao.entity.Tianzi7;
import siyi.game.utill.BeanUtil;
import siyi.game.utill.RandomUtil;
import siyi.game.utill.ReflectOperate;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 从题库中提取题目
 *
 * @author hzw
 * @version v.1.0.0, 20/03/27
 */
@Service
public class ExtractQuestion {

    private final Logger log = LoggerFactory.getLogger(ExtractQuestion.class);

    /**
     * The Tianzi 4 mapper.
     */
    @Autowired
    private Tianzi4Mapper tianzi4Mapper;

    @Autowired
    private Tianzi5Mapper tianzi5Mapper;

    @Autowired
    private Tianzi7Mapper tianzi7Mapper;

    /**
     * 从tianzi4的题库中提取题目
     *
     * @throws Exception the exception
     */
    public Map<String, Object> extractTianzi4() {
        // 根据权重判断出该题有几个项目（1;2;3;4;5）对应权重（100;60;30;15;5）
        Map weight = new HashMap();
        weight.put(1, 1000); weight.put(2, 60); weight.put(3, 30); weight.put(4, 15); weight.put(5, 15);
        int itemNum = selectNumByWeight(weight);
        // 获取题库的总题数
        int count = tianzi4Mapper.selectCount(new Tianzi4());
        // 随机选择出对应个数的项目
        List<Tianzi4> itemList = new ArrayList<>();
        for (int i = 1; i <= itemNum; i++) {
            int questionId = RandomUtil.getRandomNumInTwoIntNum(1, count);
            Tianzi4 tianzi4 = new Tianzi4();
            tianzi4.setQuestionId(questionId + "");
            tianzi4 = tianzi4Mapper.selectByPrimaryKey(tianzi4);
            itemList.add(tianzi4);
        }
        /* 对每个项目进行布局、挖字、组装答案处理 */
        log.info("=== tianzi4原始题：{} ===", JSON.toJSONString(itemList));
        Map answerMap = buildAnswer(itemList);
        AnswerTianzi answerTianzi = (AnswerTianzi) answerMap.get("answer");
        CandidateWordTianzi candidate = (CandidateWordTianzi) answerMap.get("candidate");
        QuestionTianzi quTianzi = wordLayout(itemList, answerTianzi, 4);
        Map result = new HashMap();
        result.put("answer", answerTianzi);
        result.put("question", quTianzi);
        result.put("candidate", candidate);
        return result;
    }

    public Map<String, Object> extractTianzi5() {
        // 根据权重判断出该题有几个项目（1;2;3;4;5）对应权重（100;60;30;15;5）
        Map weight = new HashMap();
        weight.put(1, 1000); weight.put(2, 600); weight.put(3, 30); weight.put(4, 15); weight.put(5, 15);
        int itemNum = selectNumByWeight(weight);
        // 获取题库的总题数
        int count = tianzi5Mapper.selectCount(new Tianzi5());
        // 随机选择出对应个数的项目
        List<Tianzi5> itemList = new ArrayList<>();
        for (int i = 1; i <= itemNum; i++) {
            int questionId = RandomUtil.getRandomNumInTwoIntNum(1, count);
            Tianzi5 tianzi5 = new Tianzi5();
            tianzi5.setQuestionId(questionId + "");
            tianzi5 = tianzi5Mapper.selectByPrimaryKey(tianzi5);
            itemList.add(tianzi5);
        }
        /* 对每个项目进行布局、挖字、组装答案处理 */
        log.info("=== tianzi5原始题：{} ===", JSON.toJSONString(itemList));
        Map answerMap = buildAnswer5(itemList);
        AnswerTianzi answerTianzi = (AnswerTianzi) answerMap.get("answer");
        CandidateWordTianzi candidate = (CandidateWordTianzi) answerMap.get("candidate");
        QuestionTianzi quTianzi = wordLayout(itemList, answerTianzi, 5);
        Map result = new HashMap();
        result.put("answer", answerTianzi);
        result.put("question", quTianzi);
        result.put("candidate", candidate);
        return result;
    }

    public Map<String, Object> extractTianzi7() {
        // 根据权重判断出该题有几个项目（1;2;3;4;5）对应权重（100;60;30;15;5）
        Map weight = new HashMap();
        weight.put(1, 1000); weight.put(2, 600); weight.put(3, 30); weight.put(4, 15); weight.put(5, 15);
        int itemNum = selectNumByWeight(weight);
        // 获取题库的总题数
        int count = tianzi7Mapper.selectCount(new Tianzi7());
        // 随机选择出对应个数的项目
        List<Tianzi7> itemList = new ArrayList<>();
        for (int i = 1; i <= itemNum; i++) {
            int questionId = RandomUtil.getRandomNumInTwoIntNum(1, count);
            Tianzi7 tianzi7 = new Tianzi7();
            tianzi7.setQuestionId(questionId + "");
            tianzi7 = tianzi7Mapper.selectByPrimaryKey(tianzi7);
            itemList.add(tianzi7);
        }
        /* 对每个项目进行布局、挖字、组装答案处理 */
        log.info("=== tianzi7原始题：{} ===", JSON.toJSONString(itemList));
        Map answerMap = buildAnswer7(itemList);
        AnswerTianzi answerTianzi = (AnswerTianzi) answerMap.get("answer");
        CandidateWordTianzi candidate = (CandidateWordTianzi) answerMap.get("candidate");
        QuestionTianzi quTianzi = wordLayout(itemList, answerTianzi, 7);
        Map result = new HashMap();
        result.put("answer", answerTianzi);
        result.put("question", quTianzi);
        result.put("candidate", candidate);
        return result;
    }

    /**
     * Word layout qu tianzi.
     *
     * @param itemList the item list
     * @return the qu tianzi
     */
    private QuestionTianzi wordLayout(List itemList, AnswerTianzi answerTianzi, int rowNum) {
        QuestionTianzi quTianzi = new QuestionTianzi();
        // 同一列编号的个位数是相同的，相邻的两行同一列上cell的序号相差10
        int xNum = rowNum; int yNum = itemList.size();
        for (int i = 0; i < yNum; i++) {
            Map xMap = betweenAnalyze(xNum, 10); // 居中计算
            Map yMap = betweenAnalyze(yNum, 10); // 居中计算
            int yStart = (int) yMap.get("startIndex");
            boolean yIntervalFlag = (boolean) yMap.get("intervalFlag");
            int index0 = 0;
            if (yIntervalFlag) {
                index0 = (yStart + i*2) *10;
            } else {
                index0 = (yStart + i) *10;
            }
            Object item = itemList.get(i);
            int x_startIndex = (int) xMap.get("startIndex");
            boolean intervalFlag = (boolean) xMap.get("intervalFlag");
            // 将成语的每字填入题目矩阵中，point代办矩阵中的位置
            Map answer = BeanUtil.beanToMapRemoveNull(answerTianzi);
            for (int j = 1; j <= rowNum; j++) {
                String value = (String) ReflectOperate.getGetMethodValue(item, "item" + j);
                Set<String> keys = answer.keySet();
                for (String key: keys) {
                    if (value.equals(answer.get(key))) { // 如果成语中的字和答案一样，则将其挖掉用对应的key替换
                        value = key.toUpperCase();
                        answer.remove(key);
                        break;
                    }
                }
                int point = 0;
                if (intervalFlag) {
                    point = index0 + x_startIndex + (j-1)*2;
                } else {
                    point = index0 + x_startIndex + j;
                }
                ReflectOperate.setValueByFieldName(quTianzi, "point" + point, value);
            }
        }
        return quTianzi;
    }

    /**
     * Between analyze.
     * 居中计算
     * @param num           X/Y轴上需要布局的项目的数量
     * @param boundaryValue X/Y的边界值
     */
    private Map<String, Object> betweenAnalyze(int num, int boundaryValue) {
        Map result = new HashMap();
        int startIndex = 0;
        boolean flag = false;
        // 判断是否要插入空行或空格
        if (2 * num - 1 < boundaryValue) { // 可以插入一个空
            startIndex = (boundaryValue - (2 * num - 1))/2;
            flag = true;
        } else {
            startIndex = (boundaryValue - num)/2;
        }
        result.put("startIndex", startIndex);
        result.put("intervalFlag", flag);
        return result;
    }

    /**
     * Build answer answer tianzi.
     * 组装正确答案
     *
     * @param itemList the item list
     * @return the answer tianzi
     */
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

    private Map<String, Object> buildAnswer5(List<Tianzi5> itemList) {
        AnswerTianzi answerTianzi = new AnswerTianzi();
        CandidateWordTianzi candidate = new CandidateWordTianzi();
        int answerIndex = 1;
        for (Tianzi5 tianzi5 : itemList) {
            List<String> contents = digWord5(tianzi5);
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

    private Map<String, Object> buildAnswer7(List<Tianzi7> itemList) {
        AnswerTianzi answerTianzi = new AnswerTianzi();
        CandidateWordTianzi candidate = new CandidateWordTianzi();
        int answerIndex = 1;
        for (Tianzi7 tianzi7 : itemList) {
            List<String> contents = digWord7(tianzi7);
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

    /**
     * Dig word.
     * 挖字
     *
     * @param tianzi4 the tianzi 4
     * @return the list
     */
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
            Set<Integer> indexSet = RandomUtil.getRandomNumInTwoIntNumRepetition(1, 4, num); // 挖字的位置
            for (Integer index : indexSet) {
                String indexValue = (String) ReflectOperate.getGetMethodValue(tianzi4, "Item" + index);
                content.add(indexValue);
            }
            return content;
        }
        return null;
    }

    private List<String> digWord5(Tianzi5 tianzi5) {
        String[] emptyNum = tianzi5.getEmptyNum().split(";");
        String[] ratio = tianzi5.getEmptyNumRatio().split(";");
        List<String> content = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (emptyNum.length == ratio.length) {
            for (int i = 0; i < emptyNum.length; i++) {
                map.put(Integer.parseInt(emptyNum[i]), Integer.parseInt(ratio[i]));
            }
            int num = selectNumByWeight(map); // 挖字的数量
            Set<Integer> indexSet = RandomUtil.getRandomNumInTwoIntNumRepetition(1, 5, num); // 挖字的位置
            for (Integer index : indexSet) {
                String indexValue = (String) ReflectOperate.getGetMethodValue(tianzi5, "Item" + index);
                content.add(indexValue);
            }
            return content;
        }
        return null;
    }

    private List<String> digWord7(Tianzi7 tianzi7) {
        String[] emptyNum = tianzi7.getEmptyNum().split(";");
        String[] ratio = tianzi7.getEmptyNumRatio().split(";");
        List<String> content = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        if (emptyNum.length == ratio.length) {
            for (int i = 0; i < emptyNum.length; i++) {
                map.put(Integer.parseInt(emptyNum[i]), Integer.parseInt(ratio[i]));
            }
            int num = selectNumByWeight(map); // 挖字的数量
            Set<Integer> indexSet = RandomUtil.getRandomNumInTwoIntNumRepetition(1, 7, num); // 挖字的位置
            for (Integer index : indexSet) {
                String indexValue = (String) ReflectOperate.getGetMethodValue(tianzi7, "Item" + index);
                content.add(indexValue);
            }
            return content;
        }
        return null;
    }

    /**
     * Select num by weight int.
     * 根据权重选择对应的数字
     *
     * @param map map的key是待选的数字，value是对应的权重
     * @return the int
     */
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



}



