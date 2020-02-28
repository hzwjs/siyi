package siyi.game.service.impl.loginLog;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.LoginLogMapper;
import siyi.game.dao.model.LoginLogInfo;
import siyi.game.service.loginLog.LoginLogService;

import java.util.List;

/**
 * description: LoginLogServiceImpl <br>
 * date: 2020/2/28 22:01 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLogInfo> getLoginLogPageInfo(String playerId, String playerName, String gameCode, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return loginLogMapper.getLoginLogPageInfo(playerId, playerName, gameCode);
    }
}
