package siyi.game.controller;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.bo.functionbtn.*;
import siyi.game.dao.LevelClearRecordMapper;
import siyi.game.dao.ScoreTodayMapper;
import siyi.game.dao.entity.LevelClearRecord;
import siyi.game.dao.entity.PlayerSign;
import siyi.game.framework.annotation.WebLog;
import siyi.game.service.fuctionbtn.FunctionService;
import siyi.game.service.player.PhysicalPowerService;
import siyi.game.utill.DateUtil;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能按钮对应的接口
 */
@RestController
@RequestMapping("idiom/function")
public class FunctionController extends BaseController{

    private final Logger log = LoggerFactory.getLogger(FunctionController.class);

    @Autowired
    private FunctionService functionService;
    @Autowired
    private LevelClearRecordMapper levelClearRecordMapper;
    @Autowired
    private ScoreTodayMapper scoreTodayMapper;
    @Autowired
    private PhysicalPowerService physicalPowerService;


    /**
     * 查询天梯信息：最高层数和挑战次数
     * @param playerId 玩家ID
     * @return
     */
    @RequestMapping("tianti")
    @ResponseBody
    @WebLog(description = "查询最高层数和挑战次数")
    public LevelClearRecord queryTiantiInfo(String playerId) {
        LevelClearRecord result = functionService.getTiantiInfo(playerId);
        return result;
    }

    /**
     * 校验当前体力是否足够
     * @param playerId 玩家ID
     * @return
     */
    @RequestMapping("tianti/checkHp")
    @WebLog(description = "校验体力是否足够")
    public boolean checkHp(String playerId) {
        boolean flag = false;
        int currentHp = physicalPowerService.calculateHp(playerId);
        if (currentHp >= 3) {
            flag = true;
        }
        return flag;
    }



    /**
     * 获取当前的可抽奖次数
     * @param playerId 玩家ID
     * @param flag     是否更新抽奖次数的标志
     * @return
     */
    @RequestMapping("lottery")
    @ResponseBody
    public Map getlotteryInfo(String playerId, boolean flag) {
        Map result = functionService.getLotteryInfo(playerId,flag);
        return result;
    }

    /**
     * 提交获取的道具
     * @param awardInfo
     * @return
     */
    @RequestMapping(value = "itemSubmit")
    @ResponseBody
    public Map<String, Object> submitLottery(AwardInfo awardInfo) {
        log.info("玩家获取道具保存，方法入参：itemId：{}，num：{}，playerId：{}，gameCode：{}", awardInfo.getItemId(), awardInfo.getNum(), awardInfo.getPlayerId(), awardInfo.getGameCode());
        Map<String, Object> resultMap = new HashMap<>();
        if (!StringUtils.isEmpty(awardInfo.getItemId()) && !StringUtils.isEmpty(awardInfo.getNum()) && !StringUtils.isEmpty(awardInfo.getPlayerId())){
            boolean flag = functionService.saveLotteryInfo(awardInfo.getPlayerId(), awardInfo.getItemId(), awardInfo.getNum(), awardInfo.getGameCode());
            if (flag) {
                resultMap.put("errCode", "000000");
                resultMap.put("errMsg", "保存成功");
            }
            getFailResult(resultMap, "保存失败");
        } else {
            getFailResult(resultMap, "缺少必输参数");
        }
        return resultMap;
    }


    /**
     * 查询背包中的道具
     * @param playerId
     * @param gameCode
     * @return
     */
    @RequestMapping(value = "queryItemInPack")
    @ResponseBody
    public List<ItemBo> queryItemInPack(String playerId, String gameCode) {
        log.info("=== 背包查询参数 playerId:{} gameCode:{}", playerId, gameCode);
        List<ItemBo> list = functionService.queryItemInPacksack(playerId, gameCode);
        return list;
    }

    /**
     * 查询签到信息，签到现在是由前端做的没有调用这个接口
     * @param playerId
     * @return
     */
    @RequestMapping(value = "querySignInfo")
    @ResponseBody
    public PlayerSign queryLottery(String playerId){
        PlayerSign playerSign = functionService.querySignInfo(playerId);
        return playerSign;
    }

    /**
     * 查询世界排行榜
     * @return
     */
    @RequestMapping(value = "queryTiantiRanking")
    @WebLog(description = "查询天梯世界排行榜")
    public Map<String, Object> queryTiantiRankingList() {
        Map<String, Object> result = new HashMap<>();
        String nowDate = DateUtil.nowStringSimpleDate();

        PageHelper.startPage(1, 100);
        List<TiantiRanking> tiantilist = levelClearRecordMapper.selectTiantiRanking(); // 查询排名前一百的玩家
        if (tiantilist.size() > 0) {
            result.put("tianti", tiantilist);
        }
        PageHelper.startPage(1, 100);
        List<WenSuccessRecord> list2 = scoreTodayMapper.queryWenRanking(nowDate);
        if (list2.size() > 0) {
            result.put("wenRanking", list2);
        }
        PageHelper.startPage(1, 100);
        List<WuSuccessRecord> list3 = scoreTodayMapper.queryWuRanking(nowDate);
        if (list3.size() > 0) {
            result.put("wuRanking", list3);
        }
        return result;
    }

}
