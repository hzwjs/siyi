package siyi.game.service.menu;

import siyi.game.dao.entity.Menu;

import java.util.List;

/**
 * description: MenuService <br>
 * date: 2020/2/20 20:18 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface MenuService {

    /**
     * description: 查询所有菜单信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 20:18 <br>
     * author: zhengzhiqiang <br>
     *
     * @param
     * @return java.util.List<siyi.game.dao.entity.Menu>
     */
    List<Menu> selectAll();

    /**
     * description: 新增菜单 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:26 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menu
     * @return int
     */
    int insertSelect(Menu menu);

    /**
     * description: 通过菜单名称查找菜单信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:28 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menuName
     * @return siyi.game.dao.entity.Menu
     */
    Menu selectByMenuName(String menuName);

    /**
     * description: 删除菜单，将菜单是否删除状态变更为已删除 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:33 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menuId
     * @return void
     */
    void deleteMenuById(Long menuId);

    /**
     * description: 更新菜单信息 <br>
     * version: 1.0 <br>
     * date: 2020/2/20 21:36 <br>
     * author: zhengzhiqiang <br>
     *
     * @param menu
     * @return int
     */
    int updateByIdSelective(Menu menu);
}
