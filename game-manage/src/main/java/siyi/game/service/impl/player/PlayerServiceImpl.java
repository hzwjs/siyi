package siyi.game.service.impl.player;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.PlayerMapper;
import siyi.game.dao.entity.Player;
import siyi.game.service.player.PlayerService;

import java.util.List;
import java.util.Map;

/**
 * description: PlayerServiceImpl <br>
 * date: 2020/2/26 22:08 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public Player selectByPlayerId(String playerId) {
        Player player = new Player();
        player.setPlayerId(playerId);
        return playerMapper.selectOne(player);
    }

    @Override
    public void insertSelective(Player player) {
        playerMapper.insertSelective(player);
    }

    @Override
    public List<Player> selectPlayerPageInfo(Player player, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return playerMapper.select(player);
    }

    @Override
    public List<Map<String, Object>> selectAreaReportByGameCode(String gameCode) {
        return playerMapper.selectAreaReportByGameCode(gameCode);
    }

    @Override
    public long selectCountByGameCode(String gameCode) {
        return playerMapper.selectCountByGameCode(gameCode);
    }
}
