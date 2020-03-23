package siyi.game.service.impl.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import siyi.game.dao.ItemPlayerRelationMapper;
import siyi.game.dao.entity.ItemPlayerRelation;
import siyi.game.service.item.ItemPlayerRelationService;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<ItemPlayerRelation> selectByPlayerIdAndGameCode(String playerId, String gameCode) {
        Example example = new Example(ItemPlayerRelation.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(playerId)) {
            criteria.andEqualTo("playerId", playerId);
        }
        if (!StringUtils.isEmpty(gameCode)) {
            criteria.andEqualTo("gameCode", gameCode);
        }

        return itemPlayerRelationMapper.selectByExample(example);
    }

    @Override
    public ItemPlayerRelation selectByBean(ItemPlayerRelation selectParam) {
        return itemPlayerRelationMapper.selectOne(selectParam);
    }

    @Override
    public void updateByIdSelective(ItemPlayerRelation itemPlayerRelation) {
        itemPlayerRelationMapper.updateByPrimaryKeySelective(itemPlayerRelation);
    }

    @Override
    public void insertSelective(ItemPlayerRelation itemPlayerRelation) {
        itemPlayerRelationMapper.insertSelective(itemPlayerRelation);
    }
}
