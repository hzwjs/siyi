package siyi.game.service.impl.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.ItemPlayerRelationMapper;
import siyi.game.dao.entity.ItemPlayerRelation;
import siyi.game.service.item.ItemPlayerRelationService;

import java.util.List;

/**
 * description: ItemPlayerRelationServiceImpl <br>
 * date: 2020/3/8 16:02 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class ItemPlayerRelationServiceImpl implements ItemPlayerRelationService {

    @Autowired
    private ItemPlayerRelationMapper itemPlayerRelationMapper;

    @Override
    public List<ItemPlayerRelation> selectByPlayerIdAndItemNoList(List<ItemPlayerRelation> itemPlayerRelations) {
        return itemPlayerRelationMapper.selectByPlayerIdAndItemNoList(itemPlayerRelations);
    }

    @Override
    public void insertListSelective(List<ItemPlayerRelation> notExistRelations) {
        itemPlayerRelationMapper.insertListSelective(notExistRelations);
    }

    @Override
    public void updateQuantityListById(List<ItemPlayerRelation> existRelations) {
        itemPlayerRelationMapper.updateQuantityListById(existRelations);
    }
}
