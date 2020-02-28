package siyi.game.service.game;

import siyi.game.dao.entity.Game;

import java.util.List;

/**
 * description: GameService <br>
 * date: 2020/2/27 0:08 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface GameService {

    /**
     * description: 获取全部在售游戏信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 0:04 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.List<siyi.game.dao.entity.Game>
     */
    List<Game> selectAllGame();

    /**
     * description: 通过游戏编码查询游戏信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/28 0:45 <br>
     * author: zhengzhiqiang <br>
     *
     * @param gameCode
     * @return siyi.game.dao.entity.Game
     */
    Game selectGameByGameCode(String gameCode);

    /**
     * description: 分页查询游戏信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/29 0:13 <br>
     * author: zhengzhiqiang <br>
     *
     * @param game
     * @param pageNum
     * @param pageSize
     * @return java.util.List<siyi.game.dao.entity.Game>
     */
    List<Game> selectGamePageList(Game game, int pageNum, int pageSize);
}
