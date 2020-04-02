package siyi.game.service.impl.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.MessionConfigMapper;
import siyi.game.service.mission.MessionConfigService;

/**
 * description: MessionConfigServiceImpl <br>
 * date: 2020/4/2 11:46 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class MessionConfigServiceImpl implements MessionConfigService {

    @Autowired
    private MessionConfigMapper messionConfigMapper;
}
