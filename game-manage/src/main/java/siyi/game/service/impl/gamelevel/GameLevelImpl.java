package siyi.game.service.impl.gamelevel;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.bo.gamelevel.*;
import siyi.game.dao.GamelevelConfigMapper;
import siyi.game.dao.QuTianziMapper;
import siyi.game.dao.entity.GamelevelConfig;
import siyi.game.dao.entity.QuTianzi;
import siyi.game.service.gamelevel.GameLevelService;
import siyi.game.utill.RandomUtil;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class GameLevelImpl implements GameLevelService {
    @Autowired
    QuTianziMapper quTianziMapper;
    @Autowired
    GamelevelConfigMapper configMapper;

    @Override
    public GameLevel queryGameLevelInfo(String userId) {
        return null;
    }

    @Override
    public GameLevel queryWenGameLevelInfo() {
        GameLevel gameLevel = new GameLevel();
        /* 查询当前关数 */
        gameLevel.setLevel("1");
        /* 读取关卡配置信息&做相关的配置处理 */
        GamelevelConfig gamelevelConfig = configMapper.selectByPrimaryKey("1");
        gameLevel.setConfigWen(handleConfigInfo(gamelevelConfig));
        /* 读取题目和答案 */
        QuTianzi quTianzi = new QuTianzi();
        quTianzi.setQuId("Q_tianzi_1");
        quTianzi = quTianziMapper.selectOne(quTianzi);
        /* 拆分题目和答案 */
        QuestionTianzi question = new QuestionTianzi();
        CandidateWordTianzi candidate = new CandidateWordTianzi();
        AnswerTianzi answer = new AnswerTianzi();
        // 题目
        BeanCopier copier = BeanCopier.create(quTianzi.getClass(), question.getClass(), false);
        copier.copy(quTianzi, question, null);
        // 候选答案
        BeanCopier copier2 = BeanCopier.create(quTianzi.getClass(), candidate.getClass(), false);
        copier2.copy(quTianzi, candidate, null);
        // 拷贝答案
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        answer = mapper.map(candidate, AnswerTianzi.class);
        gameLevel.setCandidate((CandidateWordTianzi) padWord(candidate)); // 补充候选矩阵
        gameLevel.setQuestionTianzi(question);
        gameLevel.setAnswerTianzi(answer);
        return gameLevel;
    }

    public ConfigWen handleConfigInfo(GamelevelConfig config) {
        ConfigWen configWen = new ConfigWen();
        String[] qType = {"tianzi", "duicuo", "xuanze"};
        /* 选择题型 */
        List<Map<String, String>> weightList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put(qType[0], config.getQutionTianzi());
        map.put(qType[1], config.getQutionDuicuo());
        map.put(qType[2], config.getQutionXuanze());
        selectQuestionTypeByWeight(map, configWen);
        /* 关卡限时 */
        String time = config.getTime();
        String[] limit = time.split(";");
        int limitTime = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limit[0]), Integer.parseInt(limit[1]));
        configWen.setLimitTime(limitTime);
        /* 是否出现双倍按钮 */
        String doublePercent = config.getDoubleGaiLv();
        configWen.setDoubleBut(RandomUtil.isHit(doublePercent));
        /* 是否出现复活按钮 */
        String fhPercent = config.getFuHuo();
        configWen.setFuhouBut(RandomUtil.isHit(fhPercent));
        /* 是否出现奖励 */
        String jlPercent = config.getJiangLiGaiLv();
        configWen.setJiangli(RandomUtil.isHit(jlPercent));
        /* 是否出现武关 */
        String wgPercent = config.getWuGaiLv();
        configWen.setWuguan(RandomUtil.isHit(wgPercent));
        /* 如果当前关卡题型为填字 */
        if (qType[0].equals(configWen.getQType())) {
            /* 是否有旋转效果 */
            String xzPercent = config.getXuanZhuanGaiLv();
            configWen.setXuanZhuan(RandomUtil.isHit(xzPercent));
            /* 是否有交换效果 */
            String jhPercent = config.getJiaoHuanGaiLv();
            configWen.setJiaoHuan(RandomUtil.isHit(jhPercent));
            /* 是否有变黑效果 */
            String bhPercent = config.getBianHeiGaiLv();
            configWen.setBianHeiTianzi(RandomUtil.isHit(bhPercent));
            /* 是否有点击周围单元格变亮的效果 */
            String tapPercent = config.getTapGaiLv();
            configWen.setTap(RandomUtil.isHit(tapPercent));
        }
        if (qType[2].equals(configWen.getQType())) {
            /* 是否有变黑效果 */
            String bh2Percent = config.getBianHeiGaiLv2();
            configWen.setBianHeiXuanze(RandomUtil.isHit(bh2Percent));
        }
        /* 不需要处理的属性原样复制 */
        BeanCopier copier = BeanCopier.create(config.getClass(), configWen.getClass(), false);
        copier.copy(config, configWen, null);
        return configWen;
    }

    /**
     * 根据权重选择题型
     *
     * @param weightMap
     * @param configWen
     */
    public void selectQuestionTypeByWeight(Map<String, String> weightMap, ConfigWen configWen) {
        Map<String, String> weightMapTemp = new HashMap<>();
        double amount = 0;
        // 检查是否有权重为100的，有则直接返回
        Set<String> keys = weightMap.keySet();
        for (String key : keys) {
            String value = weightMap.get(key);
            if ("100".equals(value)) {
                configWen.setQType(key);
                return;
            }
            if (!"0".equals(value)) {
                weightMapTemp.put(key, value);
                amount += Integer.parseInt(value);
            }
        }
        // 对新的权重列表进行处理
        Set<String> keys2 = weightMapTemp.keySet();
        int index = 0;
        for (String key : keys2) {
            Double value = Double.valueOf(weightMapTemp.get(key));
            DecimalFormat df = new DecimalFormat("0.00");
            String percent = (int) (Double.valueOf(df.format(value / amount)) * 100) + "%";
            boolean hit = RandomUtil.isHit(percent);
            if (hit) {
                configWen.setQType(key);
                return;
            } else {
                if (index == keys2.size() - 1) {
                    configWen.setQType(key);
                    return;
                }
            }
            index++;
        }
    }


    /**
     * 补全待选矩阵：不足10，补到10;大于10小于20，补到20
     *
     * @param answer
     * @return
     */
    private Object padWord(Object answer) {
        Class<?> c = answer.getClass();
        Field[] fields = c.getDeclaredFields();
        int notNUllCount = 0;
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                if (fields[i].get(answer) != null && !"radom".equals(fields[i].get(answer).toString()))
                    notNUllCount++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        int index = 0;
        if (notNUllCount < 10) {
            index = 10;
        } else if (notNUllCount > 10 && notNUllCount < 20) {
            index = 20;
        }
        for (int j = notNUllCount; j < index; j++) {
            try {
                fields[j].setAccessible(true);
                fields[j].set(answer, RandomUtil.getRandomChar());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return answer;
    }
}
