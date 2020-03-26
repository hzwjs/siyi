package siyi.game.manager.excel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import siyi.game.dao.IdiomMapper;
import siyi.game.dao.IdiomWrongMapper;
import siyi.game.dao.QuDuicuoMapper;
import siyi.game.dao.entity.Idiom;
import siyi.game.dao.entity.IdiomWrong;
import siyi.game.dao.entity.QuDuicuo;

import java.util.ArrayList;
import java.util.List;

/**
 * 对错题库模板读取类
 *
 * @author hzw
 */
@Component
public class QuDuicuo2DataListener extends AnalysisEventListener<IdiomWrong> {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuDuicuo2DataListener.class);

    private static final int BATCH_COUNT = 100;
    List<IdiomWrong> list = new ArrayList<IdiomWrong>();

    private IdiomWrongMapper idiomWrongMapper;
    private IdiomMapper idiomMapper;

    public QuDuicuo2DataListener(IdiomWrongMapper idiomWrongMapper, IdiomMapper idiomMapper) {
        this.idiomWrongMapper = idiomWrongMapper;
        this.idiomMapper = idiomMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(IdiomWrong data, AnalysisContext context) {
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
        list.forEach(duiCuo -> {
            if (!StringUtils.isEmpty(duiCuo.getWrongIdiom())) {
                idiomWrongMapper.insert(duiCuo);
            }
            Idiom idiom = new Idiom();
            idiom.setIdiom(duiCuo.getIdiom());
            idiom.setIdiomId(duiCuo.getWrongId());
            idiomMapper.insert(idiom);
        });
        LOGGER.info("存储数据库成功！");
    }
}
