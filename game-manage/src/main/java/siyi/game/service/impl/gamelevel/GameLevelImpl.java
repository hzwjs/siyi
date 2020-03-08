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
    QuXuanzeMapper quXuanzeMapper;
    @Autowired
    QuDuicuoMapper quDuicuoMapper;
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

    private static String[] qType = {"tianzi", "duicuo", "xuanze"};
    private static final String STATUS_VALID = "1";

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
        ConfigWen configWen = handleWenConfigInfo(gamelevelConfig);
        gameLevel.setConfigWen(configWen);
        if (qType[0].equals(configWen.getQType())) {
            /* 读取题目和答案 */
            QuTianzi quTianzi = new QuTianzi();
            quTianzi.setQuId("Q_tianzi_1");
            quTianzi.setQuStatus(STATUS_VALID);
            quTianzi = quTianziMapper.selectOne(quTianzi);
            /* 读取题目和答案 */
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
        }
        if (qType[1].equals(configWen.getQType())) {
            QuDuicuo quDuicuo = new QuDuicuo();
            quDuicuo.setQuId("Q_duicuo_1");
            quDuicuo.setQuStatus(STATUS_VALID);
            quDuicuo = quDuicuoMapper.selectOne(quDuicuo);
            /* 提取题目和答案 */
            QuestionDuicuo question = new QuestionDuicuo();
            BeanCopier copier = BeanCopier.create(quDuicuo.getClass(), question.getClass(), false);
            copier.copy(quDuicuo, question, null);
            String answer = quDuicuo.getAnswer();
            gameLevel.setQuestionDuicuo(question);
            gameLevel.setAnswerDuicuo(answer);

        }
        if (qType[2].equals(configWen.getQType())) {
            /* 读取题库配置 */
            QuXuanze quXuanze = new QuXuanze();
            quXuanze.setQuId("Q_xuanze_2");
            quXuanze.setQuStatus(STATUS_VALID);
            quXuanze = quXuanzeMapper.selectOne(quXuanze);
            /* 提取题目和答案 */
            QuestionXuanze question = new QuestionXuanze();
            BeanCopier copier = BeanCopier.create(quXuanze.getClass(), question.getClass(), false);
            copier.copy(quXuanze, question, null);

            AnswerXuanze answerXuanze = new AnswerXuanze();
            String[] answer = quXuanze.getAnswer().split(",");

            answerXuanze.setAnswer(answer);
            gameLevel.setAnswerXuanze(answerXuanze);
            gameLevel.setQuestionXuanze(question);
            gameLevel.setTips(quXuanze.getTips());
        }
        return gameLevel;
    }

    @Override
    public GameLevel queryWuGameLevelInfo(String qType) {
        // 根据权重获取最终命中的武关关卡类型
        ConfigWu configWu = new ConfigWu();
        configWu.setLevelType(qType);
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
     * description: 坠落类型武关配置信息 <br>
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
        configWu.setBaseNum(baseNum);

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
        if (isDouble) {
            // 判断是双倍、三倍还是四倍

        }
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
        Double timeInt = Double.valueOf(time) * Integer.valueOf(baseNum);
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

        // 杯子数量权重
        String beizinumStr = qiuConfig.getBeizinum();
        String beiziquanzhongStr = qiuConfig.getBeiziquanzhong();
        String[] beiziNumArray = beizinumStr.split(";");
        String[] beiziQuanZhongArray = beiziquanzhongStr.split(";");
        Map<String, String> weightMap = new HashMap<>();

        for (int i = 0; i < beiziNumArray.length; i++) {
            String beiziNum = beiziNumArray[i];
            String quanZhong = beiziQuanZhongArray[i];
            weightMap.put(beiziNum, quanZhong);
        }
        // 根据规则的权重判断命中杯子数量
        String beiziNum = selectRuleByWeight(weightMap);
        configWu.setBeiziNum(beiziNum);
        // 球数量权重
        String qiunumStr = qiuConfig.getQiunum();
        String qiuquanzhongStr = qiuConfig.getQiuquanzhong();
        String[] qiunumArray = qiunumStr.split(";");
        String[] qiuquanzhongArray = qiuquanzhongStr.split(";");
        Map<String, String> qiuWeight = new HashMap<>();
        for (int i = 0; i < qiunumArray.length; i++) {
            String quanZhong = qiuquanzhongArray[i];
            String qiunum = qiunumArray[i];
            qiuWeight.put(qiunum, quanZhong);
        }
        // 根据规则的权重判断命中球数量
        String qiuNum = selectRuleByWeight(qiuWeight);
        configWu.setQiuNum(qiuNum);
        // 速度原值
        String spend = qiuConfig.getSpend();
        configWu.setSpeed(spend);
        // 交换次数
        String jiaohuannum = qiuConfig.getJiaohuannum();
        String[] jiaohuannumArray = jiaohuannum.split(";");
        int limitjiaohuannum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jiaohuannumArray[0]), Integer.parseInt(jiaohuannumArray[1]));
        configWu.setJiaohuanNum(String.valueOf(limitjiaohuannum));
        // cd
        String cd = qiuConfig.getCd();
        configWu.setCd(cd);
        // 奖励金币
        String baseGold = qiuConfig.getGold();
        String[] limitGoldArray = baseGold.split(";");
        int limitGold = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitGoldArray[0]), Integer.parseInt(limitGoldArray[1]));
        configWu.setGold(String.valueOf(limitGold));
        // 奖励经验
        String baseExp = qiuConfig.getExp();
        String[] limitExpArray = baseExp.split(";");
        int limitExp = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitExpArray[0]), Integer.parseInt(limitExpArray[1]));
        configWu.setExp(String.valueOf(limitExp));
        // 总时间
        String zongTime = qiuConfig.getZongtime();
        configWu.setZongTime(zongTime);
        // 是否有道具奖励
        String itemPercent = qiuConfig.getItemgailv();
        boolean isHaveItem  = RandomUtil.isHit(itemPercent);
        configWu.setHaveItem(isHaveItem);
        if (isHaveItem) {
            // 道具
            String item = qiuConfig.getItem();
            configWu.setItem(item);
            // 道具数量
            String baseItemNum = qiuConfig.getItemnum();
            String[] limitItemArray = baseItemNum.split(";");
            int limitItem = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitItemArray[0]), Integer.parseInt(limitItemArray[1]));
            configWu.setItemNum(String.valueOf(limitItem));
        } else {
            configWu.setItem("-1");
            configWu.setItemNum("-1");
        }
        // 是否展示广告
        String guanggaoPercent = qiuConfig.getGuanggao();
        boolean isGuanggao = RandomUtil.isHit(guanggaoPercent);
        configWu.setGuangGao(isGuanggao);
        // 奖励是否翻倍
        String doubleRate = qiuConfig.getDoubleRate();
        boolean isDouble = RandomUtil.isHit(doubleRate);
        configWu.setDouble(isDouble);
        // 奖励时间
        String jianglitime = qiuConfig.getJianglitime();
        configWu.setJiangliTime(jianglitime);
        // 错误次数
        String wrong = qiuConfig.getWrong();
        configWu.setWrong(wrong);
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
        // 获取翻牌数集合
        String fanpaiNumStr = fanPaiConfig.getNum();
        String[] fanpainumArray = fanpaiNumStr.split(";");
        // 获取翻牌数权重集合
        String quanzhongStr = fanPaiConfig.getQuanzhong();
        String[] quanzhongNumArray = quanzhongStr.split(";");
        // 获取翻牌数及权重对应关系
        Map<String, String> fanpaiWeight = new HashMap<>();
        for (int i = 0; i < fanpainumArray.length; i++) {
            String fanpaiNum = fanpainumArray[i];
            String quanzhong = quanzhongNumArray[i];
            fanpaiWeight.put(fanpaiNum, quanzhong);
        }
        // 根据权重获取最终翻牌数
        String finalFanpaiNum = selectRuleByWeight(fanpaiWeight);
        configWu.setFanpainum(finalFanpaiNum);
        // 游戏时间
        String time = fanPaiConfig.getTime();
        String finalTime = String.valueOf(Integer.valueOf(finalFanpaiNum) * Integer.valueOf(time));
        configWu.setTotalTime(finalTime);
        // 奖励时间
        String jianglitime = fanPaiConfig.getJianglitime();
        configWu.setJiangliTime(jianglitime);


        // 奖励金币
        String baseGold = fanPaiConfig.getGold();
        String[] limitGoldArray = baseGold.split(";");
        int limitGold = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitGoldArray[0]), Integer.parseInt(limitGoldArray[1]));
        configWu.setGold(String.valueOf(limitGold));
        // 奖励经验
        String baseExp = fanPaiConfig.getExp();
        String[] limitExpArray = baseExp.split(";");
        int limitExp = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitExpArray[0]), Integer.parseInt(limitExpArray[1]));
        configWu.setExp(String.valueOf(limitExp));
        // 总时间
        String zongTime = fanPaiConfig.getZongtime();
        configWu.setZongTime(zongTime);
        // 是否有道具奖励
        String itemPercent = fanPaiConfig.getItemgailv();
        boolean isHaveItem  = RandomUtil.isHit(itemPercent);
        configWu.setHaveItem(isHaveItem);
        if (isHaveItem) {
            // 道具
            String item = fanPaiConfig.getItem();
            configWu.setItem(item);
            // 道具数量
            String baseItemNum = fanPaiConfig.getItemnum();
            String[] limitItemArray = baseItemNum.split(";");
            int limitItem = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitItemArray[0]), Integer.parseInt(limitItemArray[1]));
            configWu.setItemNum(String.valueOf(limitItem));
        } else {
            configWu.setItem("-1");
            configWu.setItemNum("-1");
        }
        // 是否展示广告
        String guanggaoPercent = fanPaiConfig.getGuanggao();
        boolean isGuanggao = RandomUtil.isHit(guanggaoPercent);
        configWu.setGuangGao(isGuanggao);
        // 奖励是否翻倍
        String doubleRate = fanPaiConfig.getDoubleRate();
        boolean isDouble = RandomUtil.isHit(doubleRate);
        configWu.setDouble(isDouble);

        // 获取最终翻牌文字
        List<String> wordList = getWordList(fanPaiConfig);
        // 根据权重判断结果返回对应数量的文字信息
        int wordNum = Integer.valueOf(finalFanpaiNum) / 2;
        List<String> randomList = RandomUtil.getRandomList(wordList, wordNum);
        configWu.setWordList(randomList);
    }

    private List<String> getWordList(FanPaiConfig fanPaiConfig) {
        List<String> wordList = new ArrayList<>();
        wordList.add(fanPaiConfig.getZi1());
        wordList.add(fanPaiConfig.getZi2());
        wordList.add(fanPaiConfig.getZi3());
        wordList.add(fanPaiConfig.getZi4());
        wordList.add(fanPaiConfig.getZi5());
        wordList.add(fanPaiConfig.getZi6());
        wordList.add(fanPaiConfig.getZi7());
        wordList.add(fanPaiConfig.getZi8());
        wordList.add(fanPaiConfig.getZi9());
        wordList.add(fanPaiConfig.getZi10());
        wordList.add(fanPaiConfig.getZi11());
        wordList.add(fanPaiConfig.getZi12());
        wordList.add(fanPaiConfig.getZi13());
        wordList.add(fanPaiConfig.getZi14());
        wordList.add(fanPaiConfig.getZi15());
        wordList.add(fanPaiConfig.getZi16());
        wordList.add(fanPaiConfig.getZi17());
        wordList.add(fanPaiConfig.getZi18());
        wordList.add(fanPaiConfig.getZi19());
        wordList.add(fanPaiConfig.getZi20());
        return wordList;
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
        // 规则1权重
        String guize1quanzhong = dianJiConfig.getGuize1quanzhong();

        // 根据规则的权重判断命中哪一个规则
        Map<String, String> guizeWeight = new HashMap<>();
        guizeWeight.put("rule1", guize1quanzhong);
        String rule = selectRuleByWeight(guizeWeight);
        // 数量给具体值
        String baseNumStr = dianJiConfig.getNum();
        String[] limit = baseNumStr.split(";");
        int limitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limit[0]), Integer.parseInt(limit[1]));
        String baseNum = String.valueOf(limitNum);
        configWu.setBaseNum(baseNum);
        Integer num = 0;
        // hp
        String hp = dianJiConfig.getHp();
        configWu.setHp(hp);

        // 奖励金币
        String baseGold = dianJiConfig.getGold();
        String[] limitGoldArray = baseGold.split(";");
        int limitGold = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitGoldArray[0]), Integer.parseInt(limitGoldArray[1]));
        configWu.setGold(String.valueOf(limitGold));
        // 奖励经验
        String baseExp = dianJiConfig.getExp();
        String[] limitExpArray = baseExp.split(";");
        int limitExp = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitExpArray[0]), Integer.parseInt(limitExpArray[1]));
        configWu.setExp(String.valueOf(limitExp));
        // 总时间
        String zongTime = dianJiConfig.getZongtime();
        configWu.setZongTime(zongTime);
        // 是否有道具奖励
        String itemPercent = dianJiConfig.getItemgailv();
        boolean isHaveItem  = RandomUtil.isHit(itemPercent);
        configWu.setHaveItem(isHaveItem);
        if (isHaveItem) {
            // 道具
            String item = dianJiConfig.getItem();
            configWu.setItem(item);
            // 道具数量
            String baseItemNum = dianJiConfig.getItemnum();
            String[] limitItemArray = baseItemNum.split(";");
            int limitItem = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitItemArray[0]), Integer.parseInt(limitItemArray[1]));
            configWu.setItemNum(String.valueOf(limitItem));
        } else {
            configWu.setItem("-1");
            configWu.setItemNum("-1");
        }
        // 是否展示广告
        String guanggaoPercent = dianJiConfig.getGuanggao();
        boolean isGuanggao = RandomUtil.isHit(guanggaoPercent);
        configWu.setGuangGao(isGuanggao);
        // 奖励是否翻倍
        String doubleRate = dianJiConfig.getDoubleRate();
        boolean isDouble = RandomUtil.isHit(doubleRate);
        configWu.setDouble(isDouble);

        String time = "";
        String jiangliTime = "";
        if ("rule1".equals(rule)) {
            // 采用规则1
            String guize1num = dianJiConfig.getGuize1num();
            String[] limitguize1numArray = guize1num.split(";");
            int limitguize1num = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitguize1numArray[0]), Integer.parseInt(limitguize1numArray[1]));
            configWu.setTotalNum(String.valueOf(limitguize1num));
            // 总时间
            time = dianJiConfig.getTime1();
            // 奖励时间
            jiangliTime = dianJiConfig.getJiangli1time();
        }
        // 总时间
        configWu.setTotalTime(String.valueOf(time));
        // 奖励时间
        configWu.setJiangliTime(jiangliTime);
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
        // 规则1权重
        String guize1quanzhong = daBaConfig.getGuize1quanzhong();
        // 规则2权重
        String guize2quanzhong = daBaConfig.getGuize2quanzhong();
        // 根据规则的权重判断命中哪一个规则
        Map<String, String> guizeWeight = new HashMap<>();
        guizeWeight.put("rule1", guize1quanzhong);
        guizeWeight.put("rule2", guize2quanzhong);
        String rule = selectRuleByWeight(guizeWeight);
        // 数量给具体值
        String baseNumStr = daBaConfig.getNum();
        String[] limit = baseNumStr.split(";");
        int limitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limit[0]), Integer.parseInt(limit[1]));
        String baseNum = String.valueOf(limitNum);
        Integer num = 0;
        // 出现时间
        String chuxiantime = daBaConfig.getChuxiantime();
        configWu.setChuxianTime(chuxiantime);
        // 出现次数
        String cishu = daBaConfig.getCishu();
        configWu.setCishu(cishu);
        // cd
        String cd = daBaConfig.getCd();
        configWu.setCd(cd);
        // 错误次数
        String wrong = daBaConfig.getWrong();
        configWu.setWrong(wrong);
        // 奖励金币
        String baseGold = daBaConfig.getGold();
        String[] limitGoldArray = baseGold.split(";");
        int limitGold = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitGoldArray[0]), Integer.parseInt(limitGoldArray[1]));
        configWu.setGold(String.valueOf(limitGold));
        // 奖励经验
        String baseExp = daBaConfig.getExp();
        String[] limitExpArray = baseExp.split(";");
        int limitExp = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitExpArray[0]), Integer.parseInt(limitExpArray[1]));
        configWu.setExp(String.valueOf(limitExp));
        // 总时间
        String zongTime = daBaConfig.getZongtime();
        configWu.setZongTime(zongTime);
        // 是否有道具奖励
        String itemPercent = daBaConfig.getItemgailv();
        boolean isHaveItem  = RandomUtil.isHit(itemPercent);
        configWu.setHaveItem(isHaveItem);
        if (isHaveItem) {
            // 道具
            String item = daBaConfig.getItem();
            configWu.setItem(item);
            // 道具数量
            String baseItemNum = daBaConfig.getItemnum();
            String[] limitItemArray = baseItemNum.split(";");
            int limitItem = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limitItemArray[0]), Integer.parseInt(limitItemArray[1]));
            configWu.setItemNum(String.valueOf(limitItem));
        } else {
            configWu.setItem("-1");
            configWu.setItemNum("-1");
        }
        // 是否展示广告
        String guanggaoPercent = daBaConfig.getGuanggao();
        boolean isGuanggao = RandomUtil.isHit(guanggaoPercent);
        configWu.setGuangGao(isGuanggao);
        // 奖励是否翻倍
        String doubleRate = daBaConfig.getDoubleRate();
        boolean isDouble = RandomUtil.isHit(doubleRate);
        configWu.setDouble(isDouble);

        String time = "";
        String jiangliTime = "";
        if ("rule1".equals(rule)) {
            // 采用规则1
            // 出现道具数量
            String guize1num = daBaConfig.getGuize1num();

            num = Integer.valueOf(baseNum) - Integer.valueOf(guize1num);
            // 总时间
            time = daBaConfig.getTime1();
            // 奖励时间
            jiangliTime = daBaConfig.getJiangli1time();
        } else if ("rule2".equals(rule)) {
            // 采用规则2
            // 出现道具数量
            String guize2num = daBaConfig.getGuize2num();
            num = Integer.valueOf(baseNum) - Integer.valueOf(guize2num);
            // 总时间
            time = daBaConfig.getTime2();
            // 奖励时间
            jiangliTime = daBaConfig.getJiangli2time();
        }

        Double timeInt = Double.valueOf(time) * Integer.valueOf(baseNum);
        // 道具破坏数量
        configWu.setTotalNum(String.valueOf(num.intValue()));
        // 总时间
        configWu.setTotalTime(String.valueOf(timeInt));
        // 奖励时间
        configWu.setJiangliTime(jiangliTime);
    }

    private ConfigWu selectWuLevelByWeight(Map<String, String> weightLevel) {
        Map<String, String> weightMapTemp = new HashMap<>();
        ConfigWu configWu = new ConfigWu();
        double amount = 0;
        // 检查是否有权重为100的，有则直接返回
        Set<String> keys = weightLevel.keySet();
        for (String key : keys) {
            String value = weightLevel.get(key);
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

    public ConfigWen handleWenConfigInfo(GamelevelConfig config) {
        ConfigWen configWen = new ConfigWen();
        /* 属性复制 */
        BeanCopier copier = BeanCopier.create(config.getClass(), configWen.getClass(), false);
        copier.copy(config, configWen, null);
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
        /* 金币 */
        String gold = config.getJiangliGold();
        String[] goldArr = gold.split(";");
        int jiangliGold = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(goldArr[0]), Integer.parseInt(goldArr[1]));
        configWen.setJiangliGold(jiangliGold + "");
        /* 经验 */
        String exp = config.getJiangliExp();
        String[] expArr = exp.split(";");
        int jiangliExp = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(expArr[0]), Integer.parseInt(expArr[1]));
        configWen.setJiangliExp(jiangliExp + "");
        /* CD */
        String cd = config.getCd();
        String[] cdArr = cd.split(";");
        int cdInt = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(cdArr[0]), Integer.parseInt(cdArr[1]));
        configWen.setCd(cdInt + "");
        /* 速度 */
        String speed = config.getSpeed();
        String[] speedArr = speed.split(";");
        int speedInt = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(speedArr[0]), Integer.parseInt(speedArr[1]));
        configWen.setSpeed(speedInt + "");
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
