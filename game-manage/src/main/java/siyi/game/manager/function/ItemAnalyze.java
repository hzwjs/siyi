package siyi.game.manager.function;

import org.springframework.util.StringUtils;
import siyi.game.bo.functionbtn.ItemBo;
import siyi.game.dao.entity.Player;
import siyi.game.utill.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提取道具配置表中的信息
 */
public class ItemAnalyze {

    /**
     * 从道具描述中提取道具类型、道具增幅
     * 道具类型: 0-续命卡；1-无限体力卡；2-体力点卡；3-跳关卡；4-金币卡；5-经验卡；6-金币双倍卡；7-经验双倍卡；8-攻击力双倍卡
     * @param itemBo
     * @param tips
     * @param
     */
    public static void analyzeItemType(ItemBo itemBo, String tips) {
        String[] p = {"重新玩本关", "无限体力：持续\\d+分钟", "获得体力\\d+点", "使用后直接从第\\d+层开始", "获得金币：\\d+个", "使用后获得\\d+经验值",
                        "双倍金币收益\\d+分钟", "双倍经验收益\\d+分钟（仅限闯关经验）", "双倍攻击力收益\\d+分钟"};
        String pattern = "\\d+";
        for (int i = 0; i < p.length; i++) {
            if (Pattern.matches(p[i], tips)) {
                String num = "";
                Matcher m = Pattern.compile(pattern).matcher(tips);
                if (m.find()) {
                    num = m.group();
                }
                switch (i) {
                    case 0:
                        itemBo.setType("0"); break;
                    case 1:
                        itemBo.setType("1"); break;
                    case 2:
                        itemBo.setAddNum(Integer.parseInt(num));
                        itemBo.setType("2"); break;
                    case 3:
                        itemBo.setAddNum(Integer.parseInt(num));
                        itemBo.setType("3"); break;
                    case 4:
                        itemBo.setAddNum(Integer.parseInt(num));
                        itemBo.setType("4"); break;
                    case 5:
                        itemBo.setAddNum(Integer.parseInt(num));
                        itemBo.setType("5"); break;
                    case 6:
                        itemBo.setAddNum(2);
                        itemBo.setType("6"); break;
                    case 7:
                        itemBo.setAddNum(2);
                        itemBo.setType("7"); break;
                    case 8:
                        itemBo.setAddNum(2);
                        itemBo.setType("8"); break;
                }
                break;
            }
        }
    }

    /**
     * 提取备注中的限制信息
     * @param itemBo
     * @param remark 限制描述
     * @param player 玩家信息
     */
    public static void analyzeLimitInfo(ItemBo itemBo, String remark, Player player) {
        String limit1 = "等级\\d+限制";
        String limit2 = "需要解锁\\d+层后使用";
        String p = "\\d+";
        String playerLevel = "";
        String gameLevel = "";
        Matcher m = Pattern.compile(p).matcher(remark);
        if (StringUtils.isEmpty(remark)) {
            itemBo.setStatus(Constants.COMMON_SUCCESS);
        } else {
            if (Pattern.matches(limit1, remark)) {
                if (m.find()) {
                    playerLevel = m.group();
                    itemBo.setPlayerLevel(playerLevel);
                }
                if (Integer.parseInt(player.getPlayerLevel()) >= Integer.parseInt(playerLevel)) {
                    itemBo.setStatus(Constants.COMMON_SUCCESS);
                } else {
                    itemBo.setStatus(Constants.COMMON_FALSE);
                }
            } else if (Pattern.matches(limit2, remark)) {
                if (m.find()) {
                    gameLevel = m.group();
                    itemBo.setGameLevel(gameLevel);
                }
                if (Integer.parseInt(player.getGameLevel()) >= Integer.parseInt(gameLevel)) {
                    itemBo.setStatus(Constants.COMMON_SUCCESS);
                } else {
                    itemBo.setStatus(Constants.COMMON_FALSE);
                }
            } else {
                itemBo.setStatus(Constants.COMMON_FALSE);
            }
        }

    }
}
