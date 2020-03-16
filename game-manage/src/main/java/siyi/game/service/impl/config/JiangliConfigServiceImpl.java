package siyi.game.service.impl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.JiangliConfigMapper;
import siyi.game.dao.entity.JiangliConfig;
import siyi.game.service.config.JiangliConfigService;

/**
 * description: JiangliConfigServiceImpl <br>
 * date: 2020/3/16 22:38 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class JiangliConfigServiceImpl implements JiangliConfigService {

    @Autowired
    private JiangliConfigMapper jiangliConfigMapper;

    @Override
    public JiangliConfig selectByGuanqia(String qId) {
        JiangliConfig jiangliConfig = new JiangliConfig();
        jiangliConfig.setGuanqia(qId);
        return jiangliConfigMapper.selectOne(jiangliConfig);
    }
}
