package siyi.game.service.impl.functionbtn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import siyi.game.bo.functionbtn.ItemBo;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.ItemPlayerRelationMapper;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.PlayerSignMapper;
import siyi.game.dao.entity.*;
import siyi.game.manager.function.ItemAnalyze;
import siyi.game.service.fuctionbtn.FunctionService;
import siyi.game.service.item.ItemPlayerRelationService;
import siyi.game.utill.Constants;
import siyi.game.utill.RandomUtil;
import siyi.game.utill.ReflectOperate;
import siyi.game.utill.UUIDUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class FunctionServiceImpl implements FunctionService {

    private final Logger log = LoggerFactory.getLogger(FunctionServiceImpl.class);

    private static final String SIGN_STATUS_DONE = "0"; // 已签到
    private static final String SIGN_STATUS_REPAIR = "1"; // 不签到
    private static final String SIGN_STATUS_DOING = "2"; // 待签到
    private static final String SIGN_STATUS_TODO = "3"; // 未开始

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private ItemConfigMapper itemConfigMapper;
    @Autowired
    private ItemPlayerRelationService itemPlayerRelationService;
    @Autowired
    private PlayerSignMapper playerSignMapper;
    @Autowired
    private ItemPlayerRelationMapper itemPlayerRelationMapper;

    @Override
    public Map getLotteryInfo(String playerId, boolean flag) {
        Map result = new HashMap();
        Player player = new Player();
        player.setPlayerId(playerId);
        player = playerMapper.selectOne(player);
        if (player != null) {
            result.put("num", player.getLotteryNum());
            result.put("items", getItemByRandom(8));
            if (flag) {
                player.setLotteryNum(player.getLotteryNum() - 1);
                playerMapper.updateByPrimaryKey(player);
            }
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public boolean saveLotteryInfo(String playerId, String itemId, int num, String gameCode) {
        boolean flag = false;
        try {
            // 获取道具关联信息，判断是否已有该道具
            ItemPlayerRelation selectParam = new ItemPlayerRelation();
            selectParam.setPlayerId(playerId);
            selectParam.setItemNo(itemId);
            selectParam.setGameCode(gameCode);
            ItemPlayerRelation itemPlayerRelation = itemPlayerRelationService.selectByBean(selectParam);
            if (itemPlayerRelation != null) {
                log.info("玩家已有该道具，开始更新道具记录");
                String oldQuantity = itemPlayerRelation.getQuantity();
                Long newQuantity = Long.valueOf(oldQuantity) + num;
                itemPlayerRelation.setQuantity(String.valueOf(newQuantity));
                itemPlayerRelationService.updateByIdSelective(itemPlayerRelation);
                flag = true;
            } else {
                log.info("玩家新增道具信息，开始新增道具记录");
                ItemPlayerRelation itemPlayerRelation2 = new ItemPlayerRelation();
                itemPlayerRelation2.setPlayerId(playerId);
                itemPlayerRelation2.setQuantity(num+"");
                itemPlayerRelation2.setGameCode(gameCode);
                itemPlayerRelation2.setItemNo(itemId);
                itemPlayerRelation2.setId(Long.valueOf(RandomUtil.generate16()));
                itemPlayerRelationService.insertSelective(itemPlayerRelation2);
                flag = true;
            }
        } catch (Exception e) {
            log.error("=== 保存奖道具失败：{}===", e);
        }
        return flag;
    }

    @Override
    public List<ItemBo> queryItemInPacksack(String playerId, String gameCode) {
        Player player = new Player();
        player.setPlayerId(playerId);
        player = playerMapper.selectOne(player);
        List<Map> list = itemPlayerRelationMapper.selectItemInfo(playerId, gameCode);
        return itemAnalyze(list, player);
    }

    @Override
    public PlayerSign querySignInfo(String playerId) {
        PlayerSign playerSign = new PlayerSign();
        playerSign.setPlayerId(playerId);
        playerSign = playerSignMapper.selectOne(playerSign);
        if (playerSign == null && !StringUtils.isEmpty(playerId)) {
            // 第一次签到的时候是没有记录的需要手动插入一条
            PlayerSign firstSign = new PlayerSign();
            firstSign.setPlayerId(playerId);
            firstSign.setStartDate(new Date());
            firstSign.setAccumulateAwardFlag(Constants.COMMON_FALSE);
            setDateStatus(1,firstSign);
            log.info("=== 第一次签到 ===");
            playerSignMapper.insert(firstSign);
            return firstSign;
        }
        return playerSign;
    }

    @Override
    public boolean submitSignInfo(String playerId, String signDays) {

        return false;
    }

    public List<ItemBo> itemAnalyze(List<Map> list, Player player) {
        List<ItemBo> resultList = new ArrayList<>();
        for (Map item : list) {
            try {
                ItemBo itemBo = new ItemBo();
                itemBo.setName((String) item.get("name"));
                itemBo.setNum((String) item.get("quantity"));
                itemBo.setId((String) item.get("id"));
                itemBo.setTips((String) item.get("tips"));
                // 从描述中提取道具使用限制条件，并判断是可以使用
                String remark = (String) item.get("remark");
                ItemAnalyze.analyzeLimitInfo(itemBo, remark, player);
                // 提取道具类型和增幅
                String tips = (String) item.get("tips");
                ItemAnalyze.analyzeItemType(itemBo, tips);
                resultList.add(itemBo);
            } catch (Exception e) {
                log.warn("=== 道具解析出错 ===", e);
            }
        }
        return resultList;
    }

    /**
     * 随机获取指定个数的道具
     * @param num
     * @return
     */
    private List<String> getItemByRandom(int num) {
        List<String> resultList = new ArrayList<>();
        ItemConfig itemConfig = new ItemConfig();
        itemConfig.setOnoff("0"); // 0-启用
        List<ItemConfig> list = itemConfigMapper.select(itemConfig);
        int len = list.size();
        for (int i = 0; i < num; i++) {
            int index = RandomUtil.getRandomNumInTwoIntNum(0, len);
            resultList.add(list.get(index).getId());
        }
        return resultList;
    }

    /**
     * 设置日期的状态
     * @param index 表示当前处在第几天
     */
    private void setDateStatus(int index, Object obj) {
        String field = "day";
        // index的状态改为：2待签到
        // index之后的日期：判断状态是否是3-未开始，如果是不处理，不是将状态更新为3；
        // index之前的日期：判断状态是否是0或1，如果不是更新成1；
        for (int i = 1; i <= 30; i++) {
            String status = (String) ReflectOperate.getGetMethodValue(obj, field + i);
            if (i == index) {
                ReflectOperate.setValueByFieldName(obj, field+index, FunctionServiceImpl.SIGN_STATUS_DOING);
            }
            if (i < index) {
                if (!FunctionServiceImpl.SIGN_STATUS_DONE.equals(status) && !FunctionServiceImpl.SIGN_STATUS_REPAIR.equals(status)) {
                    ReflectOperate.setValueByFieldName(obj, field+i, FunctionServiceImpl.SIGN_STATUS_REPAIR);
                }
            }
            if (i > index) {
                if (!FunctionServiceImpl.SIGN_STATUS_TODO.equals(status)) {
                    ReflectOperate.setValueByFieldName(obj, field+i, FunctionServiceImpl.SIGN_STATUS_TODO);
                }
            }
        }
    }
}
