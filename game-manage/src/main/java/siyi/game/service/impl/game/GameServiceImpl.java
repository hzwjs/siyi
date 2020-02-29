package siyi.game.service.impl.game;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.GameMapper;
import siyi.game.dao.entity.Game;
import siyi.game.service.game.GameService;

import java.util.List;

/**
 * description: GameServiceImpl <br>
 * date: 2020/2/27 0:09 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;

    @Override
    public List<Game> selectAllGame() {
        Game game = new Game();
        game.setIsOnSale("0");
        return gameMapper.select(game);
    }

    @Override
    public Game selectGameByGameCode(String gameCode) {
        Game game = new Game();
        game.setGameCode(gameCode);
        return gameMapper.selectOne(game);
    }

    @Override
    public List<Game> selectGamePageList(Game game, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return gameMapper.select(game);
    }

    @Override
    public int updateByIdSelective(Game game) {
        return gameMapper.updateByPrimaryKeySelective(game);
    }
}
