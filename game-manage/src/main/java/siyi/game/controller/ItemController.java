package siyi.game.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siyi.game.dao.entity.Item;
import siyi.game.service.item.ItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: ItemController <br>
 * date: 2020/2/28 22:52 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @GetMapping("getAll")
    public Map<String, Object> getItemPageList(@RequestParam(name = "itemName", required = false, defaultValue = "") String itemName,
                                               @RequestParam(name = "itemNo", required = false, defaultValue = "") String itemNo,
                                               @RequestParam(name = "gameCode", required = false, defaultValue = "") String gameCode,
                                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
                                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum) {
        Map<String, Object> resultMap = new HashMap<>();
        Item item = new Item();
        item.setItemName(itemName);
        item.setItemNo(itemNo);
        item.setGameCode(gameCode);
        List<Item> items = itemService.getItemPageList(item, pageNum, pageSize);
        PageInfo<Item> page = new PageInfo<>(items);
        getSuccessResult(resultMap);
        resultMap.put("page", page);
        return resultMap;
    }
}
