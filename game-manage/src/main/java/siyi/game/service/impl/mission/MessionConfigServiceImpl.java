package siyi.game.service.impl.mission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import siyi.game.dao.MessionConfigMapper;
import siyi.game.dao.PlayerMessionRelationMapper;
import siyi.game.dao.entity.MessionConfig;
import siyi.game.dao.entity.PlayerMessionRecord;
import siyi.game.dao.entity.PlayerMessionRelation;
import siyi.game.service.mission.MessionConfigService;
import siyi.game.service.mission.PlayerMessionRecordService;
import siyi.game.service.mission.PlayerMessionRelationService;
import siyi.game.utill.RandomUtil;
import siyi.game.utill.ReflectOperate;
import siyi.game.utill.StringUtil;
import tk.mybatis.mapper.entity.Example;

import java.text.DecimalFormat;
import java.util.*;

/**
 * description: MessionConfigServiceImpl <br>
 * date: 2020/4/2 11:46 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
@Slf4j
public class MessionConfigServiceImpl implements MessionConfigService {

    @Autowired
    private MessionConfigMapper messionConfigMapper;

    @Autowired
    private PlayerMessionRelationService playerMessionRelationService;

    @Autowired
    private PlayerMessionRecordService playerMessionRecordService;


    @Override
    public List<PlayerMessionRelation> createNewMession(String playerId, List<PlayerMessionRelation> relations) {
        log.info("开始生成任务");
        // 获取所有支线任务id
        List<String> messionIds = getFeederIds();
        Example example = new Example(MessionConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", messionIds);
        List<MessionConfig> messionConfigs = messionConfigMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(relations)) {
            // 如果任务关系为空 表示为第一次进入游戏，需在主线任务中选择第一条任务
            createFeederMission(playerId, "one");
            createFeederMission(playerId, "two");
            createFeederMission(playerId, "three");
        } else {
            // 如果存在关联关系，则需在支线任务中删除掉已完成过的任务（三次以内），保证三次内无重复支线任务
            createFeederMission(playerId);
        }
        return playerMessionRelationService.selectByPlayerId(playerId);
    }

    @Override
    public void createFeederMission(String playerId) {
        // 获取所有支线任务id
        List<String> messionIds = getFeederIds();
        Example example = new Example(MessionConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", messionIds);
        List<MessionConfig> messionConfigs = messionConfigMapper.selectByExample(example);
        // 查询玩家最近三次任务信息
        List<PlayerMessionRecord> messionList = playerMessionRecordService.selectLastThreeRelationByPlayerId(playerId);
        // 查询玩家正在执行的任务信息
        List<PlayerMessionRelation> executingMission = playerMessionRelationService.selectExecutingMission(playerId);
        List<String> relationIds = new ArrayList<>();
        for (PlayerMessionRecord record : messionList) {
            relationIds.add(record.getMessionId());
        }
        for (PlayerMessionRelation playerMessionRelation : executingMission) {
            relationIds.add(playerMessionRelation.getMessionId());
        }
        Iterator<MessionConfig> iterator = messionConfigs.iterator();
        if (iterator.hasNext()) {
            MessionConfig next = iterator.next();
            String id = next.getId();
            if (relationIds.contains(id)) {
                messionConfigs.remove(next);
            }
        }
        // 选择支线任务中的一条支线任务
        Map<String, String> weightMap = new HashMap<>();
        for (MessionConfig messionConfig : messionConfigs) {
            weightMap.put(messionConfig.getId(), messionConfig.getWeight());
        }
        String messionId = selectRuleByWeight(weightMap);

        // 根据获取的任务id进行任务记录赋值，进行任务关联赋值
        MessionConfig messionConfig = messionConfigMapper.selectByPrimaryKey(messionId);

        // 奖励经验
        String jiangliexp = messionConfig.getJiangliexp();
        String[] jiangliexpLimit = jiangliexp.split(";");
        int jiangliexpLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jiangliexpLimit[0]), Integer.parseInt(jiangliexpLimit[1]));
        String exp = String.valueOf(jiangliexpLimitNum);
        String target = messionConfig.getTarget();
        log.info("获取目标：{}", target);
        target = StringUtil.getCamelCase(target);
        log.info("转换后目标：{}", target);
        String getMethodValue = (String) ReflectOperate.getGetMethodValue(messionConfig, target);
        log.info("获取目标字段值：{}", getMethodValue);
        PlayerMessionRelation relation = new PlayerMessionRelation();

        // 奖励金币
        String jianglijinbi = messionConfig.getJianglijinbi();
        String[] jianglijinbiLimit = jianglijinbi.split(";");
        int jianglijinbiLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jianglijinbiLimit[0]), Integer.parseInt(jianglijinbiLimit[1]));
        String jinbi = String.valueOf(jianglijinbiLimitNum);
        // 赋值任务关联

        relation.setMessionId(messionId);
        relation.setPlayerId(playerId);
        String[] messionIdArray = messionId.split("_");
        if (Integer.parseInt(messionIdArray[1]) >= 257 && Integer.parseInt(messionIdArray[1]) <= 276) {
            String[] itemArray = getMethodValue.split(";");
            String itemNum = itemArray[0];
            relation.setTarget(itemNum);
            int i = RandomUtil.getRandomNumInTwoIntNum(1, itemArray.length - 1);
            relation.setTargetItem(itemArray[i]);
        } else {
            relation.setTarget(getMethodValue);
        }

        relation.setMessionTips(messionConfig.getTips());
        relation.setBlankId("three");
        // 经验需给确定值，金币确定值，道具是否存在
        relation.setExp(exp);
        relation.setGold(jinbi);
        // 是否有奖励道具
        // 道具奖励概率
        String jiangligailv = messionConfig.getItemgailv();
        boolean isHaveItem = RandomUtil.isHit(jiangligailv);
        relation.setIsItem(isHaveItem ? "1" : "0");
        if (isHaveItem) {
            // 奖励道具数量
            String itemnum = messionConfig.getItemnum();
            String[] itemnumLimit = itemnum.split(";");
            int itemnumLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(itemnumLimit[0]), Integer.parseInt(itemnumLimit[1]));
            String num = String.valueOf(itemnumLimitNum);
            relation.setItemNum(num);
            // 奖励道具ID
            String jiangliitemStr = messionConfig.getJiangliitem();
            String[] itemArray = jiangliitemStr.split(";");
            int i = RandomUtil.getRandomNumInTwoIntNum(0, itemArray.length - 1);
            relation.setItemId(itemArray[i]);
        } else {
            relation.setItemNum("0");
        }
        playerMessionRelationService.insertSelective(relation);
    }

    @Override
    public PlayerMessionRelation createFeederMission(String playerId, String blankId) {
        // 获取所有支线任务id
        List<MessionConfig> messionConfigs = messionConfigMapper.selectAll();
        // 查询玩家最近三次任务信息
        List<PlayerMessionRecord> messionList = playerMessionRecordService.selectLastThreeRelationByPlayerId(playerId);
        // 查询玩家正在执行的任务信息
        List<PlayerMessionRelation> executingMission = playerMessionRelationService.selectExecutingMission(playerId);
        List<String> relationIds = new ArrayList<>();
        for (PlayerMessionRecord record : messionList) {
            relationIds.add(record.getMessionId());
        }
        for (PlayerMessionRelation playerMessionRelation : executingMission) {
            relationIds.add(playerMessionRelation.getMessionId());
        }
        Iterator<MessionConfig> iterator = messionConfigs.iterator();
        if (iterator.hasNext()) {
            MessionConfig next = iterator.next();
            String id = next.getId();
            if (relationIds.contains(id)) {
                messionConfigs.remove(next);
            }
        }
        // 选择支线任务中的一条支线任务
        Map<String, String> weightMap = new HashMap<>();
        for (MessionConfig messionConfig : messionConfigs) {
            weightMap.put(messionConfig.getId(), messionConfig.getWeight());
        }
        String messionId = selectRuleByWeight(weightMap);
        // 根据获取的任务id进行任务记录赋值，进行任务关联赋值
        MessionConfig messionConfig = messionConfigMapper.selectByPrimaryKey(messionId);
        // 奖励经验
        String jiangliexp = messionConfig.getJiangliexp();
        String[] jiangliexpLimit = jiangliexp.split(";");
        int jiangliexpLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jiangliexpLimit[0]), Integer.parseInt(jiangliexpLimit[1]));
        String exp = String.valueOf(jiangliexpLimitNum);
        String target = messionConfig.getTarget();
        log.info("获取目标：{}", target);
        target = StringUtil.getCamelCase(target);
        log.info("转换后目标：{}", target);
        String getMethodValue = (String) ReflectOperate.getGetMethodValue(messionConfig, target);
        log.info("获取目标字段值：{}", getMethodValue);
        // 奖励金币
        String jianglijinbi = messionConfig.getJianglijinbi();
        String[] jianglijinbiLimit = jianglijinbi.split(";");
        int jianglijinbiLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(jianglijinbiLimit[0]), Integer.parseInt(jianglijinbiLimit[1]));
        String jinbi = String.valueOf(jianglijinbiLimitNum);
        // 赋值任务关联
        PlayerMessionRelation relation = new PlayerMessionRelation();
        relation.setMessionId(messionId);
        relation.setPlayerId(playerId);
        String[] messionIdArray = messionId.split("_");
        if (Integer.parseInt(messionIdArray[1]) >= 58 && Integer.parseInt(messionIdArray[1]) <= 77) {
            String[] itemArray = getMethodValue.split(";");
            String itemNum = itemArray[0];
            relation.setTarget(itemNum);
            String[] itemList = itemArray[1].split(",");
            int i = RandomUtil.getRandomNumInTwoIntNum(0, itemList.length - 1);
            relation.setTargetItem(itemList[i]);
        } else {
            relation.setTarget(getMethodValue);
        }
        relation.setMessionTips(messionConfig.getTips());
        relation.setBlankId(blankId);
        relation.setCompleteStatus("0");
        relation.setProcess("0");
        // 经验需给确定值，金币确定值，道具是否存在
        relation.setExp(exp);
        relation.setGold(jinbi);
        // 是否有奖励道具
        // 道具奖励概率
        String jiangligailv = messionConfig.getItemgailv();
        boolean isHaveItem = RandomUtil.isHit(jiangligailv);
        relation.setIsItem(isHaveItem ? "1" : "0");
        if (isHaveItem) {
            // 奖励道具数量
            String itemnum = messionConfig.getItemnum();
            String[] itemnumLimit = itemnum.split(";");
            int itemnumLimitNum = RandomUtil.getRandomNumInTwoIntNum(Integer.parseInt(itemnumLimit[0]), Integer.parseInt(itemnumLimit[1]));
            String num = String.valueOf(itemnumLimitNum);
            relation.setItemNum(num);
            // 奖励道具ID
            String jiangliitemStr = messionConfig.getJiangliitem();
            String[] itemArray = jiangliitemStr.split(";");
            int i = RandomUtil.getRandomNumInTwoIntNum(0, itemArray.length - 1);
            relation.setItemId(itemArray[i]);
        } else {
            relation.setItemNum("0");
        }
        playerMessionRelationService.insertSelective(relation);
        PlayerMessionRecord record = new PlayerMessionRecord();
        record.setMessionId(messionId);
        record.setPlayerId(playerId);
        playerMessionRecordService.insertSelective(record);
        return relation;
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

    private List<String> getFeederIds() {
        List<String> feederIds = new ArrayList<>();
        feederIds.add("quest_200");
        feederIds.add("quest_201");
        feederIds.add("quest_202");
        feederIds.add("quest_203");
        feederIds.add("quest_204");
        feederIds.add("quest_205");
        feederIds.add("quest_206");
        feederIds.add("quest_207");
        feederIds.add("quest_208");
        feederIds.add("quest_209");
        feederIds.add("quest_210");
        feederIds.add("quest_211");
        feederIds.add("quest_212");
        feederIds.add("quest_213");
        feederIds.add("quest_214");
        feederIds.add("quest_215");
        feederIds.add("quest_216");
        feederIds.add("quest_217");
        feederIds.add("quest_218");
        feederIds.add("quest_219");
        feederIds.add("quest_220");
        feederIds.add("quest_221");
        feederIds.add("quest_222");
        feederIds.add("quest_223");
        feederIds.add("quest_224");
        feederIds.add("quest_225");
        feederIds.add("quest_226");
        feederIds.add("quest_227");
        feederIds.add("quest_228");
        feederIds.add("quest_229");
        feederIds.add("quest_230");
        feederIds.add("quest_231");
        feederIds.add("quest_232");
        feederIds.add("quest_233");
        feederIds.add("quest_234");
        feederIds.add("quest_235");
        feederIds.add("quest_236");
        feederIds.add("quest_237");
        feederIds.add("quest_238");
        feederIds.add("quest_239");
        feederIds.add("quest_240");
        feederIds.add("quest_241");
        feederIds.add("quest_242");
        feederIds.add("quest_243");
        feederIds.add("quest_244");
        feederIds.add("quest_245");
        feederIds.add("quest_246");
        feederIds.add("quest_247");
        feederIds.add("quest_248");
        feederIds.add("quest_249");
        feederIds.add("quest_250");
        feederIds.add("quest_251");
        feederIds.add("quest_252");
        feederIds.add("quest_253");
        feederIds.add("quest_254");
        feederIds.add("quest_255");
        feederIds.add("quest_256");
        feederIds.add("quest_257");
        feederIds.add("quest_258");
        feederIds.add("quest_259");
        feederIds.add("quest_260");
        feederIds.add("quest_261");
        feederIds.add("quest_262");
        feederIds.add("quest_263");
        feederIds.add("quest_264");
        feederIds.add("quest_265");
        feederIds.add("quest_266");
        feederIds.add("quest_267");
        feederIds.add("quest_268");
        feederIds.add("quest_269");
        feederIds.add("quest_270");
        feederIds.add("quest_271");
        feederIds.add("quest_272");
        feederIds.add("quest_273");
        feederIds.add("quest_274");
        feederIds.add("quest_275");
        feederIds.add("quest_276");
        feederIds.add("quest_277");
        feederIds.add("quest_278");
        feederIds.add("quest_279");
        feederIds.add("quest_280");
        feederIds.add("quest_281");
        feederIds.add("quest_282");
        feederIds.add("quest_283");
        feederIds.add("quest_284");
        feederIds.add("quest_285");
        feederIds.add("quest_286");
        feederIds.add("quest_287");
        feederIds.add("quest_288");
        feederIds.add("quest_289");
        feederIds.add("quest_290");
        feederIds.add("quest_291");
        feederIds.add("quest_292");
        feederIds.add("quest_293");
        feederIds.add("quest_294");
        feederIds.add("quest_295");
        feederIds.add("quest_296");
        feederIds.add("quest_297");
        feederIds.add("quest_298");
        feederIds.add("quest_299");
        feederIds.add("quest_300");
        feederIds.add("quest_301");
        feederIds.add("quest_302");
        feederIds.add("quest_303");
        feederIds.add("quest_304");
        feederIds.add("quest_305");
        feederIds.add("quest_306");
        feederIds.add("quest_307");
        feederIds.add("quest_308");
        feederIds.add("quest_309");
        feederIds.add("quest_310");
        feederIds.add("quest_311");
        feederIds.add("quest_312");
        feederIds.add("quest_313");
        feederIds.add("quest_314");
        feederIds.add("quest_315");
        feederIds.add("quest_316");
        feederIds.add("quest_317");
        feederIds.add("quest_318");
        feederIds.add("quest_319");
        feederIds.add("quest_320");
        feederIds.add("quest_321");
        feederIds.add("quest_322");
        feederIds.add("quest_323");
        feederIds.add("quest_324");
        feederIds.add("quest_325");
        feederIds.add("quest_326");
        feederIds.add("quest_327");
        feederIds.add("quest_328");
        feederIds.add("quest_329");
        feederIds.add("quest_330");
        feederIds.add("quest_331");
        feederIds.add("quest_332");
        feederIds.add("quest_333");
        feederIds.add("quest_334");
        feederIds.add("quest_335");
        feederIds.add("quest_336");
        feederIds.add("quest_337");
        feederIds.add("quest_338");
        feederIds.add("quest_339");
        feederIds.add("quest_340");
        feederIds.add("quest_341");
        feederIds.add("quest_342");
        feederIds.add("quest_343");
        feederIds.add("quest_344");
        feederIds.add("quest_345");
        feederIds.add("quest_346");
        feederIds.add("quest_347");
        feederIds.add("quest_348");
        feederIds.add("quest_349");
        feederIds.add("quest_350");
        feederIds.add("quest_351");
        feederIds.add("quest_352");
        feederIds.add("quest_353");
        feederIds.add("quest_354");
        feederIds.add("quest_355");
        feederIds.add("quest_356");
        feederIds.add("quest_357");
        feederIds.add("quest_358");
        feederIds.add("quest_359");
        feederIds.add("quest_360");
        feederIds.add("quest_361");
        feederIds.add("quest_362");
        feederIds.add("quest_363");
        feederIds.add("quest_364");
        feederIds.add("quest_365");
        feederIds.add("quest_366");
        feederIds.add("quest_367");
        feederIds.add("quest_368");
        feederIds.add("quest_369");
        feederIds.add("quest_370");
        feederIds.add("quest_371");
        feederIds.add("quest_372");
        feederIds.add("quest_373");
        feederIds.add("quest_374");
        feederIds.add("quest_375");
        feederIds.add("quest_376");
        feederIds.add("quest_377");
        feederIds.add("quest_378");
        feederIds.add("quest_379");
        feederIds.add("quest_380");
        feederIds.add("quest_381");
        feederIds.add("quest_382");
        feederIds.add("quest_383");
        feederIds.add("quest_384");
        feederIds.add("quest_385");
        feederIds.add("quest_386");
        feederIds.add("quest_387");
        feederIds.add("quest_388");
        feederIds.add("quest_389");
        feederIds.add("quest_390");
        feederIds.add("quest_391");
        feederIds.add("quest_392");
        feederIds.add("quest_393");
        feederIds.add("quest_394");
        feederIds.add("quest_395");
        feederIds.add("quest_396");
        feederIds.add("quest_397");
        feederIds.add("quest_398");
        feederIds.add("quest_399");
        feederIds.add("quest_400");
        feederIds.add("quest_401");
        feederIds.add("quest_402");
        feederIds.add("quest_403");
        feederIds.add("quest_404");
        feederIds.add("quest_405");
        feederIds.add("quest_406");
        feederIds.add("quest_407");
        feederIds.add("quest_408");
        feederIds.add("quest_409");
        feederIds.add("quest_410");
        feederIds.add("quest_411");
        feederIds.add("quest_412");
        feederIds.add("quest_413");
        feederIds.add("quest_414");
        feederIds.add("quest_415");
        feederIds.add("quest_416");
        feederIds.add("quest_417");
        feederIds.add("quest_418");
        feederIds.add("quest_419");
        feederIds.add("quest_420");
        feederIds.add("quest_421");
        feederIds.add("quest_422");
        feederIds.add("quest_423");
        feederIds.add("quest_424");
        feederIds.add("quest_425");
        feederIds.add("quest_426");
        feederIds.add("quest_427");
        feederIds.add("quest_428");
        feederIds.add("quest_429");
        feederIds.add("quest_430");
        feederIds.add("quest_431");
        feederIds.add("quest_432");
        feederIds.add("quest_433");
        feederIds.add("quest_434");
        feederIds.add("quest_435");
        feederIds.add("quest_436");
        feederIds.add("quest_437");
        feederIds.add("quest_438");
        feederIds.add("quest_439");
        feederIds.add("quest_440");
        feederIds.add("quest_441");
        feederIds.add("quest_442");
        feederIds.add("quest_443");
        feederIds.add("quest_444");
        feederIds.add("quest_445");
        feederIds.add("quest_446");
        feederIds.add("quest_447");
        feederIds.add("quest_448");
        feederIds.add("quest_449");
        feederIds.add("quest_450");
        feederIds.add("quest_451");
        feederIds.add("quest_452");
        feederIds.add("quest_453");
        feederIds.add("quest_454");
        feederIds.add("quest_455");
        feederIds.add("quest_456");
        feederIds.add("quest_457");
        feederIds.add("quest_458");
        feederIds.add("quest_459");
        feederIds.add("quest_460");
        feederIds.add("quest_461");
        feederIds.add("quest_462");
        feederIds.add("quest_463");
        feederIds.add("quest_464");
        feederIds.add("quest_465");
        feederIds.add("quest_466");
        feederIds.add("quest_467");
        feederIds.add("quest_468");
        feederIds.add("quest_469");
        feederIds.add("quest_470");
        feederIds.add("quest_471");
        feederIds.add("quest_472");
        feederIds.add("quest_473");
        feederIds.add("quest_474");
        feederIds.add("quest_475");
        feederIds.add("quest_476");
        feederIds.add("quest_477");
        feederIds.add("quest_478");
        feederIds.add("quest_479");
        feederIds.add("quest_480");
        feederIds.add("quest_481");
        feederIds.add("quest_482");
        feederIds.add("quest_483");
        feederIds.add("quest_484");
        feederIds.add("quest_485");
        feederIds.add("quest_486");
        feederIds.add("quest_487");
        feederIds.add("quest_488");
        feederIds.add("quest_489");
        feederIds.add("quest_490");
        feederIds.add("quest_491");
        feederIds.add("quest_492");
        feederIds.add("quest_493");
        feederIds.add("quest_494");
        feederIds.add("quest_495");
        feederIds.add("quest_496");
        feederIds.add("quest_497");
        feederIds.add("quest_498");
        feederIds.add("quest_499");
        feederIds.add("quest_500");
        feederIds.add("quest_501");
        feederIds.add("quest_502");
        feederIds.add("quest_503");
        feederIds.add("quest_504");
        feederIds.add("quest_505");
        feederIds.add("quest_506");
        feederIds.add("quest_507");
        feederIds.add("quest_508");
        feederIds.add("quest_509");
        feederIds.add("quest_510");
        feederIds.add("quest_511");
        feederIds.add("quest_512");
        feederIds.add("quest_513");
        feederIds.add("quest_514");
        feederIds.add("quest_515");
        feederIds.add("quest_516");
        feederIds.add("quest_517");
        feederIds.add("quest_518");
        feederIds.add("quest_519");
        feederIds.add("quest_520");
        feederIds.add("quest_521");
        feederIds.add("quest_522");
        feederIds.add("quest_523");
        feederIds.add("quest_524");
        feederIds.add("quest_525");
        feederIds.add("quest_526");
        feederIds.add("quest_527");
        feederIds.add("quest_528");
        feederIds.add("quest_529");

        return feederIds;
    }
}
