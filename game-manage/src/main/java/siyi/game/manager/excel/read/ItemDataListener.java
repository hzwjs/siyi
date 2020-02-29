package siyi.game.manager.excel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.entity.ItemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * description: ItemDataListener <br>
 * date: 2020/2/29 15:22 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class ItemDataListener  extends AnalysisEventListener<ItemConfig> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemDataListener.class);

    private static final int BATCH_COUNT = 100;
    List<ItemConfig> list = new ArrayList<ItemConfig>();

    private ItemConfigMapper itemConfigMapper;

    public ItemDataListener(ItemConfigMapper itemConfigMapper) {
        this.itemConfigMapper = itemConfigMapper;
    }

    @Override
    public void invoke(ItemConfig itemConfig, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(itemConfig));
        list.add(itemConfig);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        list.forEach(gameLevelConfig -> itemConfigMapper.insertSelective(gameLevelConfig));
        LOGGER.info("存储数据库成功！");
    }
}
