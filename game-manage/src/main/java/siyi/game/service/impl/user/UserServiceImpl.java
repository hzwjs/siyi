package siyi.game.service.impl.user;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.UserMapper;
import siyi.game.dao.entity.User;
import siyi.game.service.user.UserService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description: UserServiceImpl <br>
 * date: 2020/2/19 21:08 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUserBean(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User selectByUserName(String userName) {
        User user = new User();
        user.setUserName(userName);
        return selectByUserBean(user);
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAllPageInfo(int pageNum, int pageSize, User user) {
        user.setIsDeleted("0");
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.select(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteById(Long id) {
        User user = new User();
        user.setId(id);
        user.setIsDeleted("1");
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
