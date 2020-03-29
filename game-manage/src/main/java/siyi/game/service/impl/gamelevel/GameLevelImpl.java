package siyi.game.service.impl.gamelevel;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import siyi.game.bo.gamelevel.*;
import siyi.game.dao.*;
import siyi.game.dao.entity.*;
import siyi.game.manager.gamelevel.ExtractQuestion;
import siyi.game.manager.gamelevel.GameLevelManage;
import siyi.game.service.config.JiangliConfigService;
import siyi.game.service.gamelevel.GameLevelService;
import siyi.game.utill.RandomUtil;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class GameLevelImpl implements GameLevelService {
    private final Logger log = LoggerFactory.getLogger(GameLevelImpl.class);
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private QuTianziMapper quTianziMapper;
    @Autowired
    private QuXuanzeMapper quXuanzeMapper;
    @Autowired
    private QuDuicuoMapper quDuicuoMapper;
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
    @Autowired
    private GameLevelManage gameLevelManage;
    @Autowired
    private ExtractQuestion extractQuestion;

    @Autowired
    private JiangliConfigService jiangliConfigService;

    private static String[] qTypes = {"tianzi", "duicuo", "xuanze", "tianzi4"};
    private static final String STATUS_VALID = "1";

    @Override
    public GameLevel queryGameLevelInfo(String userId) {
        return null;
    }

    @Override
    public GameLevel queryWenGameLevelInfo(String userId, String preQType, String preQID, String preStatus) {
        log.info("=== 关卡查询请求参数 userId:{}, preQType:{}, preQID:{}, preStatus:{} ===", userId, preQType, preQID, preStatus);
        GameLevel gameLevel = new GameLevel();

        if (!StringUtils.isEmpty(preQID)) {
            /* 保存玩家答题记录 */
            gameLevelManage.saveUserWenGameLevelInfo(userId, preQID, preQType, preStatus);
        }
        Player player = new Player();
        player.setPlayerId(userId);
        player = playerMapper.selectOne(player);
        if (player != null){
            /* 读取关卡配置信息&做相关的配置处理 */
            ConfigWen configWen = gameLevelManage.queryWenConfigInfo(player.getGameLevel());
            if (configWen != null) {
                String qType = configWen.getQType();
                /* 选择题目 */
                String nextID = gameLevelManage.getQuestionId(userId, qType);
                if (qTypes[0].equals(qType)) {
                    /* 读取题目和答案 */
                    QuTianzi quTianzi = new QuTianzi();
                    quTianzi.setQuId(nextID);
                    quTianzi.setQuStatus(STATUS_VALID);
                    quTianzi = quTianziMapper.selectOne(quTianzi);
                    if (quTianzi == null) {
                        quTianzi.setQuId("Q_tianzi_1");
                        quTianzi.setQuStatus(STATUS_VALID);
                        quTianzi = quTianziMapper.selectOne(quTianzi);
                    }
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
                    gameLevel.setCandidate(padWord(candidate)); // 补充候选矩阵
                    gameLevel.setQuestionTianzi(question);
                    gameLevel.setAnswerTianzi(answer);
                    configWen.setQuestionId(quTianzi.getQuId());
                }
                if (qTypes[1].equals(qType)) {
                    QuDuicuo quDuicuo = new QuDuicuo();
                    quDuicuo.setQuId(nextID);
                    quDuicuo.setQuStatus(STATUS_VALID);
                    quDuicuo = quDuicuoMapper.selectOne(quDuicuo);
                    if (quDuicuo == null) {
                        quDuicuo.setQuId("Q_duicuo_1");
                        quDuicuo.setQuStatus(STATUS_VALID);
                        quDuicuo = quDuicuoMapper.selectOne(quDuicuo);
                    }
                    /* 提取题目和答案 */
                    QuestionDuicuo question = new QuestionDuicuo();
                    BeanCopier copier = BeanCopier.create(quDuicuo.getClass(), question.getClass(), false);
                    copier.copy(quDuicuo, question, null);
                    String answer = quDuicuo.getAnswer();
                    gameLevel.setQuestionDuicuo(question);
                    gameLevel.setAnswerDuicuo(answer);
                    configWen.setQuestionId(quDuicuo.getQuId());
                }
                if (qTypes[2].equals(qType)) {
                    /* 读取题库配置 */
                    QuXuanze quXuanze = new QuXuanze();
                    quXuanze.setQuId(nextID);
                    quXuanze.setQuStatus(STATUS_VALID);
                    quXuanze = quXuanzeMapper.selectOne(quXuanze);
                    if (quXuanze == null) {
                        quXuanze.setQuId("Q_xuanze_1");
                        quXuanze.setQuStatus(STATUS_VALID);
                        quXuanze = quXuanzeMapper.selectOne(quXuanze);
                    }
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
                    configWen.setQuestionId(quXuanze.getQuId());
                }
                if (qTypes[3].equals(qType)) {
                    Map tianzi = extractQuestion.extractTianzi4();
                    AnswerTianzi answer = (AnswerTianzi) tianzi.get("answer");
                    CandidateWordTianzi candidate = (CandidateWordTianzi) tianzi.get("candidate");
                    QuestionTianzi questionTianzi = (QuestionTianzi) tianzi.get("question");
                    gameLevel.setAnswerTianzi(answer);
                    gameLevel.setQuestionTianzi(questionTianzi);
                    gameLevel.setCandidate(padWord(candidate)); // 补充候选矩阵
                    log.info("=== tainzi4 answer:{} ===", JSON.toJSONString(answer));
                    log.info("=== tainzi4 question:{} ===", JSON.toJSONString(questionTianzi));
                }
                gameLevel.setConfigWen(configWen);

                return gameLevel;
            }
            log.warn("=== 获取文关配置信息失败 ===");
        }
        log.warn("=== 玩家信息获取失败 ===");
        return null;
    }

    @Override
    public GameLevel queryWuGameLevelInfo(String preQID, String userId) {
        // 根据权重获取最终命中的武关关卡类型
        ConfigWu configWu = new ConfigWu();
        // 获取本题题数，获取配置信息
        String[] preQIds = preQID.split("_");
        String preQId = preQIds[preQIds.length - 1];
        String qId = String.valueOf(Integer.valueOf(preQId) + 1);
        JiangliConfig jiangliConfig = jiangliConfigService.selectByGuanqia(qId);
        if (jiangliConfig == null) {
            jiangliConfig = jiangliConfigService.selectByGuanqia("1");
        }
        // 获取基本配置信息
        getBaseConfigWu(jiangliConfig, configWu);

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
                setDianjiTypeConfig(dianJiConfigs, configWu);
            }
        } else if ("fanpai".equals(configWu.getLevelType())) {
            // 翻牌类型
            List<FanPaiConfig> fanPaiConfigs = fanPaiConfigMapper.selectAll();
            String wuFanpaiId = gameLevelManage.getWuFanpaiId(userId, "fanpai");
            FanPaiConfig finalFanpaiConfig = fanPaiConfigMapper.selectByPrimaryKey(wuFanpaiId);
            if (finalFanpaiConfig == null) {
                finalFanpaiConfig = fanPaiConfigs.get(0);
            }
            setFanPaiTypeConfig(finalFanpaiConfig, configWu);
            /* 保存保存玩家答题记录 */
            gameLevelManage.saveUserWuGameRecord(userId, wuFanpaiId, "fanpai");
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
     * description: 获取武关基本配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/16 23:59 <br>
     * author: zhengzhiqiang <br>
     *
     * @param jiangliConfig
     * @param configWu
     * @return void
     */
    private void getBaseConfigWu(JiangliConfig jiangliConfig, ConfigWu configWu) {
        // 关卡配置时间
        String timeStr = jiangliConfig.getTime();
        String[] timeLimit = timeStr.split(";");
        int timeLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(timeLimit[0]), Integer.parseInt(timeLimit[1]));
        String time = String.valueOf(timeLimitNum);
        configWu.setGuanqiaTime(time);
        // 关卡数
        configWu.setLevelId(jiangliConfig.getGuanqia());

        // 武关关卡类型
        String wuLevelType = getWuLevelType(jiangliConfig);
        configWu.setLevelType(wuLevelType);

        // hp系数
        String hpxishu = jiangliConfig.getHpxishu();
        configWu.setHpXishu(hpxishu);

        // 经验
        String jiangliexpStr = jiangliConfig.getJiangliexp();
        String[] jiangliexpLimit = jiangliexpStr.split(";");
        int jiangliexpLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jiangliexpLimit[0]), Integer.parseInt(jiangliexpLimit[1]));
        String jiangliexp = String.valueOf(jiangliexpLimitNum);
        configWu.setExp(jiangliexp);

        // 金币
        String jiangligoldStr = jiangliConfig.getJiangligold();
        String[] jiangligoldLimit = jiangligoldStr.split(";");
        int jiangligoldLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jiangligoldLimit[0]), Integer.parseInt(jiangligoldLimit[1]));
        String jiangligold = String.valueOf(jiangligoldLimitNum);
        configWu.setGold(jiangligold);
        // 道具奖励概率
        String jiangligailv = jiangliConfig.getJiangligailv();
        boolean isHaveItem = RandomUtil.isHit(jiangligailv);
        configWu.setHaveItem(isHaveItem);
        // 若出现奖励道具，则计算道具数量及道具id
        if (isHaveItem) {
            String jiangliitemStr = jiangliConfig.getJiangliitem();
            String[] itemArray = jiangliitemStr.split(";");
            int i = RandomUtil.getRandomNumInTwoIntNum(0, itemArray.length - 1);
            configWu.setItem(itemArray[i]);
            String itemshuliang = jiangliConfig.getItemshuliang();
            String itemNum = getRandomString(itemshuliang);
            configWu.setItemNum(itemNum);
        }
        // 广告概率
        String guanggao = jiangliConfig.getGuanggao();
        configWu.setGuangGao(RandomUtil.isHit(guanggao));
        // 复活概率
        String fuhuo = jiangliConfig.getFuhuo();
        configWu.setFuhuo(RandomUtil.isHit(fuhuo));
    }

    /**
     * description: 获取武关关卡类型 <br>
     * version: 1.0 <br>
     * date: 2020/3/16 23:39 <br>
     * author: zhengzhiqiang <br>
     *
     * @param jiangliConfig
     * @return java.lang.String
     */
    private String getWuLevelType(JiangliConfig jiangliConfig) {
        String dabaWeight = jiangliConfig.getkDaba();
        String dianjiWeight = jiangliConfig.getkDianji();
        String fanpaiWeight = jiangliConfig.getkFanpai();
        String qiuWeight = jiangliConfig.getkQiu();
        String zhuiluoWeight = jiangliConfig.getkZhuiluo();
        Map<String, String> weightMap = new HashMap<>();
        weightMap.put("daba", dabaWeight);
        weightMap.put("dianji", dianjiWeight);
        weightMap.put("fanpai", fanpaiWeight);
        weightMap.put("qiu", qiuWeight);
        weightMap.put("zhuiluo", zhuiluoWeight);
        return selectRuleByWeight(weightMap);
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
        String levelId = configWu.getLevelId();
        String hpXishu = configWu.getHpXishu();
        // 数量给具体值
        String baseNumStr = zhuiLuoConfig.getNum();
        String baseNum = getRandomString(baseNumStr);
        configWu.setBaseNum(baseNum);
        // 速度
        String speedStr = zhuiLuoConfig.getSpend();
        String speed = getRandomString(speedStr);
        configWu.setSpeed(speed);
        Double num = 0D;
        // cd
        String cdStr = zhuiLuoConfig.getCd();
        configWu.setCd(cdStr);

        // hp
        String hpStr = zhuiLuoConfig.getHp();
        String baseHp = getRandomString(hpStr);
        Double hp = Double.valueOf(baseHp) + Double.valueOf(levelId) / Double.valueOf(hpXishu);
        configWu.setHp(String.valueOf(hp.intValue()));

        String time = "";
        String jiangliTime = "";
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
        // 道具破坏数量
        configWu.setTotalNum(String.valueOf(num.intValue()));
        // 总时间
        configWu.setGuizeTime(String.valueOf(timeInt.intValue()));
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
        String qiuquanzhongStr = qiuConfig.getQiuquanzhong();
        String[] qiunumArray = {"1", "2", "3"};
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
        // 速度
        String speedStr = qiuConfig.getSpend();
        String speed = getRandomString(speedStr);
        configWu.setSpeed(speed);
        // 交换次数
        String jiaohuannumStr = qiuConfig.getJiaohuannum();
        String jiaohuanNum = getRandomString(jiaohuannumStr);
        configWu.setJiaohuanNum(String.valueOf(jiaohuanNum));
        // cd
        String cdStr = qiuConfig.getCd();
        String cd = getDoubleRandomString(cdStr);
        Double cdD = Double.valueOf(cd) * 1000;
        configWu.setCd(String.valueOf(cdD.intValue()));
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
        configWu.setGuizeTime(finalTime);
        // 奖励时间
        String jianglitime = fanPaiConfig.getJianglitime();
        configWu.setJiangliTime(jianglitime);

        // 获取最终翻牌文字
        List<String> wordList = getWordList(fanPaiConfig);
        // 根据权重判断结果返回对应数量的文字信息
        int wordNum = Integer.valueOf(finalFanpaiNum) / 2;
        List<String> randomList = RandomUtil.getRandomList(wordList, wordNum);
        configWu.setWordList(randomList);
    }

    private List<String> getWordList(FanPaiConfig fanPaiConfig) {
        List<String> wordList = new ArrayList<>();
        if (!StringUtils.isEmpty(fanPaiConfig.getZi1())) {
            wordList.add(fanPaiConfig.getZi1());
        }

        if (!StringUtils.isEmpty(fanPaiConfig.getZi2())) {
            wordList.add(fanPaiConfig.getZi2());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi3())) {
            wordList.add(fanPaiConfig.getZi3());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi4())) {
            wordList.add(fanPaiConfig.getZi4());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi5())) {
            wordList.add(fanPaiConfig.getZi5());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi6())) {
            wordList.add(fanPaiConfig.getZi6());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi7())) {
            wordList.add(fanPaiConfig.getZi7());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi8())) {
            wordList.add(fanPaiConfig.getZi8());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi9())) {
            wordList.add(fanPaiConfig.getZi9());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi10())) {
            wordList.add(fanPaiConfig.getZi10());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi11())) {
            wordList.add(fanPaiConfig.getZi11());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi12())) {
            wordList.add(fanPaiConfig.getZi12());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi13())) {
            wordList.add(fanPaiConfig.getZi13());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi14())) {
            wordList.add(fanPaiConfig.getZi14());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi15())) {
            wordList.add(fanPaiConfig.getZi15());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi16())) {
            wordList.add(fanPaiConfig.getZi16());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi17())) {
            wordList.add(fanPaiConfig.getZi17());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi18())) {
            wordList.add(fanPaiConfig.getZi18());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi19())) {
            wordList.add(fanPaiConfig.getZi19());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi20())) {
            wordList.add(fanPaiConfig.getZi20());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi21())) {
            wordList.add(fanPaiConfig.getZi21());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi22())) {
            wordList.add(fanPaiConfig.getZi22());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi23())) {
            wordList.add(fanPaiConfig.getZi23());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi24())) {
            wordList.add(fanPaiConfig.getZi24());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi25())) {
            wordList.add(fanPaiConfig.getZi25());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi26())) {
            wordList.add(fanPaiConfig.getZi26());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi27())) {
            wordList.add(fanPaiConfig.getZi27());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi28())) {
            wordList.add(fanPaiConfig.getZi28());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi29())) {
            wordList.add(fanPaiConfig.getZi29());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi30())) {
            wordList.add(fanPaiConfig.getZi30());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi31())) {
            wordList.add(fanPaiConfig.getZi31());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi32())) {
            wordList.add(fanPaiConfig.getZi32());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi33())) {
            wordList.add(fanPaiConfig.getZi33());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi34())) {
            wordList.add(fanPaiConfig.getZi34());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi35())) {
            wordList.add(fanPaiConfig.getZi35());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi36())) {
            wordList.add(fanPaiConfig.getZi36());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi37())) {
            wordList.add(fanPaiConfig.getZi37());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi38())) {
            wordList.add(fanPaiConfig.getZi38());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi39())) {
            wordList.add(fanPaiConfig.getZi39());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi40())) {
            wordList.add(fanPaiConfig.getZi40());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi41())) {
            wordList.add(fanPaiConfig.getZi41());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi42())) {
            wordList.add(fanPaiConfig.getZi42());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi43())) {
            wordList.add(fanPaiConfig.getZi43());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi44())) {
            wordList.add(fanPaiConfig.getZi44());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi45())) {
            wordList.add(fanPaiConfig.getZi45());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi46())) {
            wordList.add(fanPaiConfig.getZi46());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi47())) {
            wordList.add(fanPaiConfig.getZi47());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi48())) {
            wordList.add(fanPaiConfig.getZi48());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi49())) {
            wordList.add(fanPaiConfig.getZi49());
        }
        if (!StringUtils.isEmpty(fanPaiConfig.getZi50())) {
            wordList.add(fanPaiConfig.getZi50());
        }
        return wordList;
    }

    /**
     * description: 点击类型武关配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/1 15:28 <br>
     * author: zhengzhiqiang <br>
     *
     * @param dianJiConfigs
     * @param configWu
     * @return void
     */
    private void setDianjiTypeConfig(List<DianJiConfig> dianJiConfigs, ConfigWu configWu) {
        String hpXishu = configWu.getHpXishu();
        String levelId = configWu.getLevelId();
        List<DianJiConfigInfo> dianJiConfigInfos = new ArrayList<>();
        for (DianJiConfig dianJiConfig : dianJiConfigs) {
            DianJiConfigInfo dianJiConfigInfo = new DianJiConfigInfo();
            dianJiConfigInfo.setLei(dianJiConfig.getLei());
            // 数量给具体值
            String baseNumStr = dianJiConfig.getNum();
            String baseNum = getRandomString(baseNumStr);
            dianJiConfigInfo.setNum(baseNum);
            // hp
            String hpStr = dianJiConfig.getHp();
            String baseHp = getRandomString(hpStr);
            Double hp = Double.valueOf(baseHp) + Double.valueOf(levelId) / Double.valueOf(hpXishu);
            dianJiConfigInfo.setHp(String.valueOf(hp.intValue()));
            // 根据规则的权重判断命中哪一个规则
            String guize1quanzhong = dianJiConfig.getGuize1quanzhong();
            Map<String, String> guizeWeight = new HashMap<>();
            guizeWeight.put("rule1", guize1quanzhong);
            String rule = selectRuleByWeight(guizeWeight);
            String time = "";
            String jiangliTime = "";
            if ("rule1".equals(rule)) {
                // 总时间
                time = dianJiConfig.getTime1();
                // 奖励时间
                jiangliTime = dianJiConfig.getJiangli1time();
                dianJiConfigInfo.setRuleType("rule1");
            }
            dianJiConfigInfo.setTime(time);
            dianJiConfigInfo.setJiangliTime(jiangliTime);
            dianJiConfigInfo.setRemark(dianJiConfig.getRemark());
            dianJiConfigInfos.add(dianJiConfigInfo);
        }
        configWu.setDianJiConfigs(dianJiConfigInfos);
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

        // 数量给具体值
        String baseNumStr = daBaConfig.getNum();
        String baseNum = getRandomString(baseNumStr);
        configWu.setBaseNum(baseNum);

        Integer num = 0;
        // 出现时间
        String chuxiantimeStr = daBaConfig.getChuxiantime();
        String chuxianTime = getDoubleRandomString(chuxiantimeStr);
        Double chuxianTimeD = Double.valueOf(chuxianTime) * 1000;
        configWu.setChuxianTime(String.valueOf(chuxianTimeD.intValue()));
        // 出现次数
        String cishuStr = daBaConfig.getCishu();
        String cishu = getRandomString(cishuStr);
        configWu.setCishu(cishu);

        // cd
        String cdStr = daBaConfig.getCd();
        String cd = getRandomString(cdStr);
        int cdI = Integer.valueOf(cd) * 1000;
        configWu.setCd(String.valueOf(cdI));

        // 错误次数
        String wrong = daBaConfig.getWrong();
        configWu.setWrong(wrong);

        // 根据规则的权重判断命中哪一个规则
        Map<String, String> guizeWeight = new HashMap<>();
        guizeWeight.put("rule1", guize1quanzhong);
        guizeWeight.put("rule2", guize2quanzhong);
        String rule = selectRuleByWeight(guizeWeight);
        String time = "";
        String jiangliTime = "";
        if ("rule1".equals(rule)) {
            // 采用规则1
            // 出现道具数量
            String guize1num = daBaConfig.getGuize1num();
            num = Integer.valueOf(baseNum) + Integer.valueOf(guize1num);
            // 总时间
            time = daBaConfig.getTime1();
            // 奖励时间
            jiangliTime = daBaConfig.getJiangli1time();
            configWu.setHitRule("rule1");
        } else if ("rule2".equals(rule)) {
            // 采用规则2
            // 出现道具数量
            String guize2num = daBaConfig.getGuize2num();
            num = Integer.valueOf(baseNum) + Integer.valueOf(guize2num);
            // 总时间
            time = daBaConfig.getTime2();
            // 奖励时间
            jiangliTime = daBaConfig.getJiangli2time();
            configWu.setHitRule("rule2");
        }

        Double timeInt = Double.valueOf(time) * Integer.valueOf(baseNum);
        // 道具破坏数量
        configWu.setTotalNum(String.valueOf(num.intValue()));
        // 规则配置总时间
        configWu.setGuizeTime(String.valueOf(timeInt));
        // 奖励时间
        configWu.setJiangliTime(jiangliTime);
    }

    private String getRandomString(String baseNumStr) {
        String[] limit = baseNumStr.split(";");
        int limitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(limit[0]), Integer.parseInt(limit[1]));
        return String.valueOf(limitNum);
    }

    private String getDoubleRandomString(String baseNumStr) {
        String[] limit = baseNumStr.split(";");
        double limitNum = RandomUtil.getRandomNumInTwoDoubleNum(Double.parseDouble(limit[0]), Double.parseDouble(limit[1]));
        return String.valueOf(limitNum);
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
                if (fields[i].get(answer) != null && !"radom".equals(fields[i].get(answer).toString()) && !"null".equals(fields[i].get(answer).toString()))
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
        if (notNUllCount < 10) {
            CandidateWordTianzi10 answer10 = new CandidateWordTianzi10();
            BeanCopier copier = BeanCopier.create(answer.getClass(), answer10.getClass(), false);
            copier.copy(answer, answer10, null);
            log.info("=== candidate:{} ===", JSON.toJSONString(answer10));
            return answer10;
        }
        log.info("=== candidate:{} ===", JSON.toJSONString(answer));
        return answer;
    }
}
