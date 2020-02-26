package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import siyi.game.service.game.GameService;

/**
 * description: GameController <br>
 * date: 2020/2/27 0:11 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class GameController extends BaseController {
    @Autowired
    private GameService gameService;
}
