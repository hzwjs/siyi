package siyi.game.dao;

import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * description: 用户mapper <br>
 * version: 1.0 <br>
 * date: 2020/2/19 21:06 <br>
 * author: zhengzhiqiang <br>
 */
@Repository
public interface UserMapper extends Mapper<User> {

}
