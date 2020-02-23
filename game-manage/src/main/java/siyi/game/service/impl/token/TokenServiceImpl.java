package siyi.game.service.impl.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import siyi.game.dao.entity.User;
import siyi.game.service.token.TokenService;

/**
 * description: TokenServiceImpl <br>
 * date: 2020/2/23 14:14 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}
