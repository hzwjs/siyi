package siyi.game.service.user;

import siyi.game.dao.entity.User;

import java.util.List;

/**
 * description: UserService <br>
 * date: 2020/2/19 21:07 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface UserService {

    /**
     * description: 根据bean进行查询 <br>
     * version: 1.0 <br>
     * date: 2020/2/19 21:12 <br>
     * author: zhengzhiqiang <br>
     *
     * @param user
     * @return siyi.game.dao.entity.User
     */
    User selectByUserBean(User user);

    /**
     * description: 新增用户 <br>
     * version: 1.0 <br>
     * date: 2020/2/19 21:39 <br>
     * author: zhengzhiqiang <br>
     *
     * @param user
     * @return void
     */
    void insertUser(User user);

    /**
     * description: 通过用户名查询用户信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/19 21:43 <br>
     * author: zhengzhiqiang <br>
     *
     * @param userName
     * @return siyi.game.dao.entity.User
     */
    User selectByUserName(String userName);

    /**
     * description: 获取所有用户 <br>
     * version: 1.0 <br>
     * date: 2020/2/19 22:51 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.List<siyi.game.dao.entity.User>
     */
    List<User> selectAllUsers();

    /**
     * description: 通过主键查询用户信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/23 14:20 <br>
     * author: zhengzhiqiang <br>
     *
     * @param id
     * @return siyi.game.dao.entity.User
     */
    User selectById(Long id);

    /**
     * description: 分页查询用户信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/23 23:44 <br>
     * author: zhengzhiqiang <br>
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @param user 查询用户参数
     * @return java.util.List<siyi.game.dao.entity.User>
     */
    List<User> selectAllPageInfo(int pageNum, int pageSize, User user);
}
