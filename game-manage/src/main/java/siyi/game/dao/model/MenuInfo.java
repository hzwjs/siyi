package siyi.game.dao.model;

import siyi.game.dao.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * description: MenuInfo <br>
 * date: 2020/2/20 20:28 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class MenuInfo extends Menu implements Serializable {

    private List<MenuInfo> childrenMenus;

    public List<MenuInfo> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<MenuInfo> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }
}
