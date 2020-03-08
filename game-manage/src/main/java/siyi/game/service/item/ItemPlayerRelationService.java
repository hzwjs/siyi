package siyi.game.service.item;

import siyi.game.dao.entity.ItemPlayerRelation;

import java.util.List;

/**
 * description: ItemPlayerRelationService <br>
 * date: 2020/3/8 16:02 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface ItemPlayerRelationService {

    /**
     * description: 根据集合中的玩家id，道具编号查询关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 16:44 <br>
     * author: zhengzhiqiang <br>
     *
     * @param itemPlayerRelations
     * @return java.util.List<siyi.game.dao.entity.ItemPlayerRelation>
     */
    List<ItemPlayerRelation> selectByPlayerIdAndItemNoList(List<ItemPlayerRelation> itemPlayerRelations);

    /**
     * description: 批量插入道具关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 20:33 <br>
     * author: zhengzhiqiang <br>
     *
     * @param notExistRelations
     * @return void
     */
    void insertListSelective(List<ItemPlayerRelation> notExistRelations);

    /**
     * description: 根据主键批量更新关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 21:15 <br>
     * author: zhengzhiqiang <br>
     *
     * @param existRelations
     * @return void
     */
    void updateQuantityListById(List<ItemPlayerRelation> existRelations);
}
