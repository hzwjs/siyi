package siyi.game.manager.excel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import siyi.game.dao.QuXuanzeMapper;
import siyi.game.dao.entity.QuXuanze;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择题库模板读取类
 *
 * @author hzw
 */
@Component
public class QuXuanzeDataListener extends AnalysisEventListener<QuXuanze> {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuXuanzeDataListener.class);
    private static final int BATCH_COUNT = 100;
    List<QuXuanze> list = new ArrayList<QuXuanze>();

    private QuXuanzeMapper quXuanzeMapper;

    public QuXuanzeDataListener(QuXuanzeMapper quXuanzeMapper) {
        this.quXuanzeMapper = quXuanzeMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(QuXuanze data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        list.forEach(quXuanze -> quXuanzeMapper.insert(quXuanze));
        LOGGER.info("存储数据库成功！");
    }
}
