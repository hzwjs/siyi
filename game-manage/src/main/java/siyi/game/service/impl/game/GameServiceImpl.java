package siyi.game.service.impl.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.GameMapper;
import siyi.game.service.game.GameService;

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
}
