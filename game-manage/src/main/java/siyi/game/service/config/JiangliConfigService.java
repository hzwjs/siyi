package siyi.game.service.config;

import siyi.game.dao.entity.JiangliConfig;

/**
 * description: JiangliConfigService <br>
 * date: 2020/3/16 22:38 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface JiangliConfigService {


    /**
     * description: 根据关卡查询关卡配置信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/16 23:27 <br>
     * author: zhengzhiqiang <br>
     *
     * @param qId
     * @return siyi.game.dao.entity.JiangliConfig
     */
    JiangliConfig selectByGuanqia(String qId);
}
