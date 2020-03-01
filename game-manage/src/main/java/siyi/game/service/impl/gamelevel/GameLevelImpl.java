package siyi.game.service.impl.gamelevel;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import siyi.game.bo.gamelevel.*;
import siyi.game.dao.*;
import siyi.game.dao.entity.*;
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

    @Autowired
    private DaBaConfigMapper daBaConfigMapper;

    @Autowired
    private DianJiConfigMapper dianJiConfigMapper;

    @Autowired
    private ZhuiLuoConfigMapper zhuiLuoConfigMapper;

    @Autowired
    private QiuConfigMapper qiuConfigMapper;

    @Autowired
    private FanPaiConfigMapper fanPaiConfigMapper;

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

    @Override
    public GameLevel queryWuGameLevelInfo() {
        // 获取武关关卡的权重配置
        Map<String, String> weightLevel = new HashMap<>();
        weightLevel.put("daba", "0");
        weightLevel.put("dianji", "0");
        weightLevel.put("fanpai", "0");
        weightLevel.put("qiu", "0");
        weightLevel.put("zhuiluo", "100");
        // 根据权重获取最终命中的武关关卡类型
        ConfigWu configWu = selectWuLevelByWeight(weightLevel);
        // 根据关卡类型获取对应关卡的配置信息
        if ("daba".equals(configWu.getLevelType())) {
            // 打靶类型
            List<DaBaConfig> daBaConfigs = daBaConfigMapper.selectAll();
            if (!CollectionUtils.isEmpty(daBaConfigs)) {
                DaBaConfig daBaConfig = daBaConfigs.get(0);
                setDabaTypeConfig(daBaConfig, configWu);
            }
        } else if ("dianji".equals(configWu.getLevelType())) {
            // 点击类型
            List<DianJiConfig> dianJiConfigs = dianJiConfigMapper.selectAll();
            if (!CollectionUtils.isEmpty(dianJiConfigs)) {
                DianJiConfig dianJiConfig = dianJiConfigs.get(0);
                setDianjiTypeConfig(dianJiConfig, configWu);
            }
        } else if ("fanpai".equals(configWu.getLevelType())) {
            // 翻牌类型
            List<FanPaiConfig> fanPaiConfigs = fanPaiConfigMapper.selectAll();
            if (!CollectionUtils.isEmpty(fanPaiConfigs)) {
                FanPaiConfig fanPaiConfig = fanPaiConfigs.get(0);
                setFanPaiTypeConfig(fanPaiConfig, configWu);
            }
        } else if ("qiu".equals(configWu.getLevelType())) {
            // 杯中藏球类型
            List<QiuConfig> qiuConfigs = qiuConfigMapper.selectAll();
            if (!CollectionUtils.isEmpty(qiuConfigs)) {
                QiuConfig qiuConfig = qiuConfigs.get(0);
                setQiuTypeConfig(qiuConfig, configWu);
            }
        } else if ("zhuiluo".equals(configWu.getLevelType())) {
            // 坠落类型
            List<ZhuiLuoConfig> zhuiLuoConfigs = zhuiLuoConfigMapper.selectAll();
            if (!CollectionUtils.isEmpty(zhuiLuoConfigs)) {
                ZhuiLuoConfig zhuiLuoConfig = zhuiLuoConfigs.get(0);
                setZhuiluoTypeConfig(zhuiLuoConfig, configWu);
            }
        }
        // 组装武关配置信息
        GameLevel gameLevel = new GameLevel();
        gameLevel.setConfigWu(configWu);
        return gameLevel;
    }

    /**
     * description: 杯坠落类型武关配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 15:28 <br>
     * author: zhengzhiqiang <br>
     *
     * @param zhuiLuoConfig
     * @param configWu
     * @return void
     */
    private void setZhuiluoTypeConfig(ZhuiLuoConfig zhuiLuoConfig, ConfigWu configWu) {
        // 规则1权重
        String guize1quanzhong = zhuiLuoConfig.getGuize1quanzhong();
        // 规则2权重
        String guize2quanzhong = zhuiLuoConfig.getGuize2quanzhong();
        // 规则3权重
        String guize3quanzhong = zhuiLuoConfig.getGuize3quanzhong();
        // 根据规则的权重判断命中哪一个规则
        Map<String, String> guizeWeight = new HashMap<>();
        guizeWeight.put("rule1", guize1quanzhong);
        guizeWeight.put("rule2", guize2quanzhong);
        guizeWeight.put("rule3", guize3quanzhong);
        String rule = selectRuleByWeight(guizeWeight);
        // 数量给具体值
        String baseNumStr = zhuiLuoConfig.getNum();
        String[] limit = baseNumStr.split(";");
        int limitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limit[0]), Integer.parseInt(limit[1]));
        String baseNum = String.valueOf(limitNum);
        Double num = 0D;
        // cd 原值
        String cd = zhuiLuoConfig.getCd();
        configWu.setCd(cd);
        // 速度原值
        String speed = zhuiLuoConfig.getSpend();
        configWu.setSpeed(speed);
        // hp 原值
        String hp = zhuiLuoConfig.getHp();
        configWu.setHp(hp);
        // 奖励金币
        String baseGold = zhuiLuoConfig.getGold();
        String[] limitGoldArray = baseGold.split(";");
        int limitGold = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitGoldArray[0]), Integer.parseInt(limitGoldArray[1]));
        configWu.setGold(String.valueOf(limitGold));
        // 奖励经验
        String baseExp = zhuiLuoConfig.getExp();
        String[] limitExpArray = baseExp.split(";");
        int limitExp = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitExpArray[0]), Integer.parseInt(limitExpArray[1]));
        configWu.setExp(String.valueOf(limitExp));
        // 总时间
        String zongTime = zhuiLuoConfig.getZongtime();
        configWu.setZongTime(zongTime);
        // 是否有道具奖励
        String itemPercent = zhuiLuoConfig.getItemgailv();
        boolean isHaveItem  = RandomUtil.isHit(itemPercent);
        configWu.setHaveItem(isHaveItem);
        if (isHaveItem) {
            // 道具
            String item = zhuiLuoConfig.getItem();
            configWu.setItem(item);
            // 道具数量
            String baseItemNum = zhuiLuoConfig.getItemnum();
            String[] limitItemArray = baseItemNum.split(";");
            int limitItem = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitItemArray[0]), Integer.parseInt(limitItemArray[1]));
            configWu.setItemNum(String.valueOf(limitItem));
        } else {
            configWu.setItem("-1");
            configWu.setItemNum("-1");
        }
        // 是否展示广告
        String guanggaoPercent = zhuiLuoConfig.getGuanggao();
        boolean isGuanggao = RandomUtil.isHit(guanggaoPercent);
        configWu.setGuangGao(isGuanggao);
        // 奖励是否翻倍
        String doubleRate = zhuiLuoConfig.getDoubleRate();
        boolean isDouble = RandomUtil.isHit(doubleRate);
        configWu.setDouble(isDouble);

        String time = "";
        String jiangliTime = "";
        if ("rule1".equals(rule)) {
            // 采用规则1
            // 出现道具数量
            String guize1num = zhuiLuoConfig.getGuize1num();
            if (guize1num.contains("%")) {
                guize1num = guize1num.substring(0, guize1num.length() - 1);
            }
            num = Integer.valueOf(baseNum) * Integer.valueOf(guize1num) * 0.01;
            // 总时间
            time = zhuiLuoConfig.getTime1();
            // 奖励时间
            jiangliTime = zhuiLuoConfig.getJiangli1time();
        } else if ("rule2".equals(rule)) {
            // 采用规则2
            // 出现道具数量
            String guize2num = zhuiLuoConfig.getGuize2num();
            if (guize2num.contains("%")) {
                guize2num = guize2num.substring(0, guize2num.length() - 1);
            }
            num = Integer.valueOf(baseNum) * Integer.valueOf(guize2num) * 0.01;
            // 总时间
            time = zhuiLuoConfig.getTime2();
            // 奖励时间
            jiangliTime = zhuiLuoConfig.getJiangli2time();
        } else if ("rule3".equals(rule)) {
            // 出现道具数量
            String guize3num = zhuiLuoConfig.getGuize3num();
            if (guize3num.contains("%")) {
                guize3num = guize3num.substring(0, guize3num.length() - 1);
            }
            num = Integer.valueOf(baseNum) * Integer.valueOf(guize3num) * 0.01;
            // 总时间
            time = zhuiLuoConfig.getTime3();
            // 奖励时间
            jiangliTime = zhuiLuoConfig.getJiangli3time();
        }

        Double timeInt = Double.valueOf(time) * num.intValue();
        // 道具出现数量
        configWu.setTotalNum(String.valueOf(num.intValue()));
        // 总时间
        configWu.setTotalTime(String.valueOf(timeInt));
        // 奖励时间
        configWu.setJiangliTime(jiangliTime);
    }

    /**
     * description: 根据权重获取武关规则 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 16:03 <br>
     * author: zhengzhiqiang <br>
     *
     * @param guizeWeight
     * @return java.lang.String
     */
    private String selectRuleByWeight(Map<String, String> guizeWeight) {
        Map<String, String> weightMapTemp = new HashMap<>();
        double amount = 0;
        // 检查是否有权重为100的，有则直接返回
        Set<String> keys = guizeWeight.keySet();
        for (String key : keys) {
            String value = guizeWeight.get(key);
            if ("100".equals(value)) {
                return key;
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
                return key;
            } else {
                if (index == keys2.size() - 1) {
                    return key;
                }
            }
            index++;
        }
        return "";
    }

    /**
     * description: 杯中藏球类型武关配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 15:28 <br>
     * author: zhengzhiqiang <br>
     *
     * @param qiuConfig
     * @param configWu
     * @return void
     */
    private void setQiuTypeConfig(QiuConfig qiuConfig, ConfigWu configWu) {
        // TODO 组装杯中藏球武关配置信息
    }

    /**
     * description: 翻牌类型武关参数组装 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 15:31 <br>
     * author: zhengzhiqiang <br>
     *
     * @param fanPaiConfig
     * @param configWu
     * @return void
     */
    private void setFanPaiTypeConfig(FanPaiConfig fanPaiConfig, ConfigWu configWu) {
        // TODO 组装翻牌武关配置信息
    }

    /**
     * description: 点击类型武关配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 15:28 <br>
     * author: zhengzhiqiang <br>
     *
     * @param dianJiConfig
     * @param configWu
     * @return void
     */
    private void setDianjiTypeConfig(DianJiConfig dianJiConfig, ConfigWu configWu) {
        // TODO 获取点击类型武关配置信息
    }

    /**
     * description: 打靶类型武关配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 15:25 <br>
     * author: zhengzhiqiang <br>
     *
     * @param daBaConfig
     * @param configWu
     * @return void
     */
    private void setDabaTypeConfig(DaBaConfig daBaConfig, ConfigWu configWu) {
        // TODO 获取打靶类型的武关配置信息
    }

    private ConfigWu selectWuLevelByWeight(Map<String, String> weightLevel) {
        Map<String, String> weightMapTemp = new HashMap<>();
        ConfigWu configWu = new ConfigWu();
        double amount = 0;
        // 检查是否有权重为100的，有则直接返回
        Set<String> keys = weightLevel.keySet();
        for (String key : keys) {
            String value = weightLevel.get(key);
            if ("100".equals(value)) {
                configWu.setLevelType(key);
                return configWu;
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
                configWu.setLevelType(key);
                return configWu;
            } else {
                if (index == keys2.size() - 1) {
                    configWu.setLevelType(key);
                    return configWu;
                }
            }
            index++;
        }
        return configWu;
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
