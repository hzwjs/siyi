package siyi.game.manager.excel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.MessionConfigMapper;
import siyi.game.dao.entity.ItemConfig;
import siyi.game.dao.entity.MessionConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * description: 任务配置信息读取类 <br>
 * date: 2020/2/29 15:22 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class MessionConfigDataListener extends AnalysisEventListener<MessionConfig> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessionConfigDataListener.class);

    private static final int BATCH_COUNT = 100;
    List<MessionConfig> list = new ArrayList<MessionConfig>();

    private MessionConfigMapper messionConfigMapper;

    public MessionConfigDataListener(MessionConfigMapper messionConfigMapper) {
        this.messionConfigMapper = messionConfigMapper;
    }

    @Override
    public void invoke(MessionConfig messionConfig, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(messionConfig));
        list.add(messionConfig);
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
        list.forEach(messionConfig -> messionConfigMapper.insertSelective(messionConfig));
        LOGGER.info("存储数据库成功！");
    }
}
