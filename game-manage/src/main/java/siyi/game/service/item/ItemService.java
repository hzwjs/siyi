package siyi.game.service.item;

import siyi.game.dao.entity.Item;

import java.util.List;

/**
 * description: ItemService <br>
 * date: 2020/2/28 23:17 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface ItemService {

    /**
     * description: 分页查询道具信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 23:28 <br>
     * author: zhengzhiqiang <br>
     *
     * @param item
     * @param pageNum
     * @param pageSize
     * @return java.util.List<siyi.game.dao.entity.Item>
     */
    List<Item> getItemPageList(Item item, int pageNum, int pageSize);
}
