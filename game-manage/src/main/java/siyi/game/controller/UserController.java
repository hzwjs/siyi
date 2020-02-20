package siyi.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siyi.game.dao.entity.User;
import siyi.game.service.user.UserService;
import siyi.game.utill.Dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: UserController <br>
 * date: 2020/2/19 21:14 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * description: 登录 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 20:21 <br>
     * author: zhengzhiqiang <br>
     *
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping(value = "login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<>();
        User findUser = userService.selectByUserBean(user);
        if (findUser != null) {
            getSuccessResult(resultMap);
        } else {
            getFailResult(resultMap, "账号或密码错误");
        }
        return resultMap;
    }

    /**
     * description: 新增用户 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 20:21 <br>
     * author: zhengzhiqiang <br>
     *
     * @param user
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @GetMapping(value = "addUser")
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<>();
        String userName = user.getUserName();
        User userByName = userService.selectByUserName(userName);
        if (userByName != null) {
            getFailResult(resultMap, "用户名称已存在");
            return resultMap;
        }
        userService.insertUser(user);
        getSuccessResult(resultMap);
        return resultMap;
    }

    @GetMapping(value = "getAll")
    public List<User> getAll() {
        List<User> users = userService.selectAllUsers();
        return users;
    }
}
