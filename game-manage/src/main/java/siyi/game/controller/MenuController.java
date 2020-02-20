package siyi.game.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import siyi.game.dao.entity.Menu;
import siyi.game.dao.model.MenuInfo;
import siyi.game.service.menu.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: MenuController <br>
 * date: 2020/2/20 20:21 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping(value = "menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * description: 获取菜单结构 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:24 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.List<siyi.game.dao.model.MenuInfo>
     */
    @GetMapping("getAll")
    public List<MenuInfo> getAllMenu() {
        List<Menu> menus = menuService.selectAll();
        List<MenuInfo> rootMenu = getMenuInfo(menus);
        List<MenuInfo> menuList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rootMenu)) {
            for (int i = 0; i < rootMenu.size(); i++) {
                // 一级菜单parentId为0
                if (rootMenu.get(i).getParentId() == 0) {
                    MenuInfo menuInfo = new MenuInfo();
                    BeanUtils.copyProperties(rootMenu.get(i), menuInfo);
                    menuList.add(menuInfo);
                }
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (MenuInfo menuInfo : menuList) {
            menuInfo.setChildrenMenus(getChild(menuInfo.getId(), rootMenu));
        }
        return menuList;
    }

    /**
     * description: 新增菜单 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:30 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menu
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("addMenu")
    public Map<String, Object> addMenu(@RequestBody Menu menu) {
        Map<String, Object> resultMap = new HashMap<>();
        Menu menuByName = menuService.selectByMenuName(menu.getMenuName());
        if (menuByName != null) {
            getFailResult(resultMap, "菜单名称已存在");
            return resultMap;
        }
        int i = menuService.insertSelect(menu);
        if (i <= 0) {
            getFailResult(resultMap, "新增菜单失败");
            return resultMap;
        } else {
            getSuccessResult(resultMap);
            return resultMap;
        }
    }

    /**
     * description: 删除菜单 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:34 <br>
     * author: zhengzhiqiang <br>
     *
     * @param id
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @GetMapping("deleteMenu")
    public Map<String, Object> deleteMenu(String id) {
        Map<String, Object> resultMap = new HashMap<>();
        Long menuId = Long.valueOf(id);
        menuService.deleteMenuById(menuId);
        getSuccessResult(resultMap);
        return resultMap;
    }

    /**
     * description: 修改菜单信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:37 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menu
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @PostMapping("updateMenu")
    public Map<String, Object> updateMenu(@RequestBody Menu menu) {
        Map<String, Object> resultMap = new HashMap<>();
        int i = menuService.updateByIdSelective(menu);
        if (i <= 0) {
            getFailResult(resultMap, "修改菜单失败");
            return resultMap;
        } else {
            getSuccessResult(resultMap);
            return resultMap;
        }
    }

    /**
     * description: 将集合中的Menu替换为MenuInfo <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:23 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menus
     * @return java.util.List<siyi.game.dao.model.MenuInfo>
     */
    private List<MenuInfo> getMenuInfo(List<Menu> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return new ArrayList<>();
        }
        List<MenuInfo> list = new ArrayList<>();
        for (Menu menu : menus) {
            MenuInfo menuInfo = new MenuInfo();
            BeanUtils.copyProperties(menu, menuInfo);
            list.add(menuInfo);
        }
        return list;
    }

    /**
     * description: 递归找出菜单下所有子菜单 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:23 <br>
     * author: zhengzhiqiang <br>
     *
     * @param id
     * @param rootMenu
     * @return java.util.List<siyi.game.dao.model.MenuInfo>
     */
    private List<MenuInfo> getChild(Long id, List<MenuInfo> rootMenu) {
        // 子菜单
        List<MenuInfo> childList = new ArrayList<>();
        for (MenuInfo menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId() == id) {
                childList.add(menu);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuInfo menu : childList) {// 没有url子菜单还有子菜单
            if (!StringUtils.isEmpty(menu.getMenuHref())) {
                // 递归
                menu.setChildrenMenus(getChild(menu.getId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}
