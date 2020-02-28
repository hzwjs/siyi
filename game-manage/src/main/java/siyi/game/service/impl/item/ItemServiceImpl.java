package siyi.game.service.impl.item;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.ItemMapper;
import siyi.game.dao.entity.Item;
import siyi.game.service.item.ItemService;

import java.util.List;

/**
 * description: ItemServiceImpl <br>
 * date: 2020/2/28 23:18 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> getItemPageList(Item item, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return itemMapper.select(item);
    }
}
