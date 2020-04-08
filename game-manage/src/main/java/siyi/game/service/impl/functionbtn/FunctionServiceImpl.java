package siyi.game.service.impl.functionbtn;

import org.springframework.beans.factory.annotation.Autowired;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.entity.Item;
import siyi.game.dao.entity.ItemConfig;
import siyi.game.dao.entity.Player;
import siyi.game.service.fuctionbtn.FunctionService;
import siyi.game.utill.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private ItemConfigMapper itemConfigMapper;

    @Override
    public Map getAwardInfo(String playerId, boolean flag) {
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
}
