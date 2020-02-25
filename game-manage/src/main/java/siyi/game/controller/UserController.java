package siyi.game.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siyi.game.dao.entity.User;
import siyi.game.service.token.TokenService;
import siyi.game.service.user.UserService;

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

    @Autowired
    private TokenService tokenService;

    /**
     * description: 登录 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 20:21 <br>
     * author: zhengzhiqiang <br>
     *
     * @param user
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @PostMapping(value = "login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<>();
        User findUser = userService.selectByUserBean(user);
        if (findUser != null) {
            if ("1".equals(findUser.getUserStatus())) {
                getFailResult(resultMap, "该用户已失效");
                return resultMap;
            }
            String token = tokenService.getToken(findUser);
            resultMap.put("token", token);
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
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @PostMapping(value = "addUser")
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

    /**
     * description: 修改用户 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 20:21 <br>
     * author: zhengzhiqiang <br>
     *
     * @param user
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     */
    @PostMapping(value = "editUser")
    public Map<String, Object> editUser(@RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<>();
        int i = userService.updateUserById(user);
        if (i <= 0) {
            getFailResult(resultMap, "用户信息更新失败");
            return resultMap;
        }
        getSuccessResult(resultMap);
        return resultMap;
    }

    /**
     * description: 获取用户列表 <br>
     * version: 1.0 <br>
     * date: 2020/2/24 22:16 <br>
     * author: zhengzhiqiang <br>
     *
     * @param pageNum  当前页码
     * @param pageSize 每页展示条数
     * @param userName 用户名
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping(value = "getAll")
    public Map<String, Object> getAll(int pageNum, int pageSize, String userName) {
        User user = new User();
        user.setUserName(userName);
        List<User> users = userService.selectAllPageInfo(pageNum, pageSize, user);
        Map<String, Object> resultMap = new HashMap<>();
        getSuccessResult(resultMap);
        PageInfo<User> page = new PageInfo<>(users);
        resultMap.put("page", page);
        return resultMap;
    }

    /**
     * description: 修改用户生效状态 <br>
     * version: 1.0 <br>
     * date: 2020/2/25 23:10 <br>
     * author: zhengzhiqiang <br>
     *
     * @param id 用户id
     * @param status 用户修改后的生效状态
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("changeUserStatus")
    public Map<String, Object> changeUserStatus(String id, String status) {
        Map<String, Object> resultMap = new HashMap<>();
        User user = userService.selectById(Long.valueOf(id));
        if (user == null) {
            getFailResult(resultMap, "该用户不存在");
            return resultMap;
        }
        user.setUserStatus(status);
        int i = userService.updateUserById(user);
        if (i <= 0) {
            getFailResult(resultMap, "用户状态更新失败");
            return resultMap;
        }
        getSuccessResult(resultMap);
        return resultMap;
    }


    /**
     * description: 根据主键查询用户信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/25 23:14 <br>
     * author: zhengzhiqiang <br>
     *
     * @param id 用户id
     * @return siyi.game.dao.entity.User
     */
    @GetMapping("getUser")
    public User getUserById(String id) {
        return userService.selectById(Long.valueOf(id));
    }

    @GetMapping("deleteUser")
    public Map<String, Object> deleteUser(String id) {
        Map<String, Object> resultMap = new HashMap<>();
        int i = userService.deleteById(Long.valueOf(id));
        if (i <= 0) {
            getFailResult(resultMap, "用户删除失败");
            return resultMap;
        }
        getSuccessResult(resultMap);
        return resultMap;
    }
}
