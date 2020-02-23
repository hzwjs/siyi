package siyi.game.service.token;

import siyi.game.dao.entity.User;

/**
 * description: TokenService <br>
 * date: 2020/2/23 14:11 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface TokenService {
    String getToken(User user);
}
