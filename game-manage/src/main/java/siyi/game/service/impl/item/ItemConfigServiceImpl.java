package siyi.game.service.impl.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.entity.ItemConfig;
import siyi.game.service.item.ItemConfigService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description: ItemConfigServiceImpl <br>
 * date: 2020/3/9 0:12 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class ItemConfigServiceImpl implements ItemConfigService {

    @Autowired
    private ItemConfigMapper itemConfigMapper;

    @Override
    public List<ItemConfig> selectByItemNoList(List<String> itemNoList) {
        Example example = new Example(ItemConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", itemNoList);
        return itemConfigMapper.selectByExample(example);
    }

    @Override
    public List<ItemConfig> selectAll() {
        return itemConfigMapper.selectAll();
    }

    @Override
    public ItemConfig selectByItemId(String itemId) {
        return itemConfigMapper.selectByPrimaryKey(itemId);
    }
}
