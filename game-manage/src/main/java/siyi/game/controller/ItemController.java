package siyi.game.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siyi.game.dao.ItemConfigMapper;
import siyi.game.dao.entity.Game;
import siyi.game.dao.entity.Item;
import siyi.game.dao.entity.ItemConfig;
import siyi.game.manager.excel.read.ItemDataListener;
import siyi.game.service.game.GameService;
import siyi.game.service.item.ItemService;

import java.io.File;
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
@RequestMapping("idiom/item")
public class ItemController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ItemConfigMapper itemConfigMapper;

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
        List<Game> gameList = gameService.selectAllGame();
        resultMap.put("gameList", gameList);
        return resultMap;
    }

    @GetMapping("changeItemStatus")
    public Map<String, Object> changeItemStatus(String id, String status) {
        Map<String, Object> resultMap = new HashMap<>();
        Item item = new Item();
        item.setId(Long.valueOf(id));
        item.setItemStatus(status);
        int i = itemService.updateByIdSelective(item);
        if (i <= 0) {
            getFailResult(resultMap, "道具状态更新失败");
            return resultMap;
        }
        getSuccessResult(resultMap);
        return resultMap;
    }

    @RequestMapping(value = "fileAnalysis")
    @ResponseBody
    public void fileAnalysis(){
        try {
            String fileDir = ClassLoader.getSystemResource("").toURI().getPath();
            String filePath = fileDir + "biz_config" + File.separator + "item.xlsx";
            System.out.println(filePath);
            EasyExcel.read(filePath, ItemConfig.class, new ItemDataListener(itemConfigMapper)).sheet().doRead();

        } catch (Exception e) {
            LOGGER.error("读取道具配置信息错误：{}", e);
        }
    }
}
