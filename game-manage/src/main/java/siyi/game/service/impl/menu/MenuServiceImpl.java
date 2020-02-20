package siyi.game.service.impl.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.MenuMapper;
import siyi.game.dao.entity.Menu;
import siyi.game.service.menu.MenuService;

import java.util.List;

/**
 * description: MenuServiceImpl <br>
 * date: 2020/2/20 20:18 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public int insertSelect(Menu menu) {
        return  menuMapper.insertSelective(menu);
    }

    @Override
    public Menu selectByMenuName(String menuName) {
        Menu menu = new Menu();
        menu.setMenuName(menuName);
        return menuMapper.selectOne(menu);
    }

    @Override
    public void deleteMenuById(Long menuId) {
        menuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public int updateByIdSelective(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }
}
