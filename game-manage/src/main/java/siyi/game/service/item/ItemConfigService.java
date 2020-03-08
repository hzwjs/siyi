package siyi.game.service.item;

import siyi.game.dao.entity.ItemConfig;

import java.util.List;

/**
 * description: ItemConfigService <br>
 * date: 2020/3/9 0:12 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface ItemConfigService {

    /**
     * description: 根据道具编号列表查询道具信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/9 0:13 <br>
     * author: zhengzhiqiang <br>
     *
     * @param itemNoList
     * @return java.util.List<siyi.game.dao.entity.ItemConfig>
     */
    List<ItemConfig> selectByItemNoList(List<String> itemNoList);
}
