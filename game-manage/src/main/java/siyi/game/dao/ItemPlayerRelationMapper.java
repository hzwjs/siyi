package siyi.game.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import siyi.game.dao.entity.ItemPlayerRelation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ItemPlayerRelationMapper extends Mapper<ItemPlayerRelation> {

    /**
     * description: 根据玩家ID道具编号集合查询关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 16:50 <br>
     * author: zhengzhiqiang <br>
     *
     * @param itemPlayerRelations
     * @return java.util.List<siyi.game.dao.entity.ItemPlayerRelation>
     */
    List<ItemPlayerRelation> selectByPlayerIdAndItemNoList(@Param("itemPlayerRelations") List<ItemPlayerRelation> itemPlayerRelations);

    /**
     * description: 批量插入道具关联信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 20:45 <br>
     * author: zhengzhiqiang <br>
     *
     * @param itemPlayerRelations
     * @return void
     */
    void insertListSelective(@Param("itemPlayerRelations") List<ItemPlayerRelation> itemPlayerRelations);

    /**
     * description: 根据主键更新数据信息 <br>
     * version: 1.0 <br>
     * date: 2020/3/8 21:16 <br>
     * author: zhengzhiqiang <br>
     *
     * @param itemPlayerRelations
     * @return void
     */
    void updateQuantityListById(@Param("itemPlayerRelations") List<ItemPlayerRelation> itemPlayerRelations);
}
