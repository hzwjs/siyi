package siyi.game.manager.gamelevel;

import net.sf.cglib.beans.BeanCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import siyi.game.bo.gamelevel.ConfigWen;
import siyi.game.dao.GamelevelConfigMapper;
import siyi.game.dao.UserQuestionMapper;
import siyi.game.dao.entity.GamelevelConfig;
import siyi.game.dao.entity.UserQuestion;
import siyi.game.utill.Constants;
import siyi.game.utill.RandomUtil;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 关卡管理类：配置信息查询、题目信息查询
 */
@Service
public class GameLevelManage {
    private final Logger log = LoggerFactory.getLogger(GameLevelManage.class);

    @Autowired
    private GamelevelConfigMapper configMapper;
    @Autowired
    private UserQuestionMapper userQuestionMapper;

    private static String[] qTypes = {"tianzi", "duicuo", "xuanze"};

    /**
     * 查询当前文关关卡的配置信息
     * @param level
     * @return
     */
    public ConfigWen queryWenConfigInfo(String level) {
        /* 读取关卡配置信息&做相关的配置处理 */
        GamelevelConfig gamelevelConfig = configMapper.selectByPrimaryKey(level);
        ConfigWen configWen = handleWenConfigInfo(gamelevelConfig);
        return configWen;
    }

    /**
     * 获取题目ID
     * @param userId
     * @param qType
     * @return
     */
    public String getQuestionId(String userId, String qType) {
        Map<String, String> param = new HashMap<>();
        param.put("userId", userId); param.put("questionType", qType);
        UserQuestion userQuestion = userQuestionMapper.queryUserCurrentQuestion(param);
        String questionId = "";
        if (userQuestion != null) {
            String status = userQuestion.getStatus();
            if (Constants.COMMON_SUCCESS.equals(status)) {
                String questionID = userQuestion.getQuestionId();
                String prefix = questionID.substring(0, questionID.lastIndexOf("_")+1);
                String index = questionID.substring(questionID.lastIndexOf("_") + 1);
                questionId = prefix + (Integer.parseInt(index) + 1);
            } else {
                questionId = userQuestion.getQuestionId();
            }
        } else {
            questionId = "Q_" + qType + "_1";
        }
        return questionId;
    }

    /**
     * 保存文关答题情况
     * @param userId
     * @param qId
     * @param qType
     * @param qStatus
     */
    public void saveUserWenGameLevelInfo(String userId, String qId, String qType, String qStatus) {
        if (!StringUtils.isEmpty(qId)){
            UserQuestion userq = new UserQuestion();
            userq.setUserId(userId);
            userq.setQuestionType(qType);
            userq.setQuestionId(qId);
            List<UserQuestion> temp = userQuestionMapper.select(userq);

            if (temp.size() == 0) {
                userq.setCreatedTime(new Date());
                userq.setAnswerNum(1);
                userq.setStatus(qStatus);
                userQuestionMapper.insert(userq);
            } else {
                userq = temp.get(0);
                userq.setStatus(qStatus);
                userq.setAnswerNum(userq.getAnswerNum() + 1);
                if ("0".equals(qStatus)) {
                    userq.setAnswerSuccessNum((userq.getAnswerSuccessNum()==null?0:userq.getAnswerSuccessNum()) + 1);
                } else {
                    userq.setAnswerFailNum((userq.getAnswerFailNum()==null?0:userq.getAnswerFailNum()) + 1);
                }
                userQuestionMapper.updateByPrimaryKey(userq);
            }
        }
    }



    /**
     * 对原始的配置信息进行包装和处理（按权重选题、答案填充等）形成具体的文关关卡的配置信息
     * @param config
     * @return
     */
    private ConfigWen handleWenConfigInfo(GamelevelConfig config) {
        ConfigWen configWen = new ConfigWen();
        /* 属性复制 */
        BeanCopier copier = BeanCopier.create(config.getClass(), configWen.getClass(), false);
        copier.copy(config, configWen, null);
        /* 按权重选题型 */
        List<Map<String, String>> weightList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put(qTypes[0], config.getQutionTianzi());
        map.put(qTypes[1], config.getQutionDuicuo());
        map.put(qTypes[2], config.getQutionXuanze());
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
        if (qTypes[0].equals(configWen.getQType())) {
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
        if (qTypes[2].equals(configWen.getQType())) {
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
    private void selectQuestionTypeByWeight(Map<String, String> weightMap, ConfigWen configWen) {
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

}
