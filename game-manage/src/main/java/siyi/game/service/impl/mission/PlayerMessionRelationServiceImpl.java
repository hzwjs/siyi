package siyi.game.service.impl.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.PlayerMessionRelationMapper;
import siyi.game.dao.entity.PlayerMessionRelation;
import siyi.game.service.mission.PlayerMessionRelationService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description: PlayerMessionRelationServiceImpl <br>
 * date: 2020/4/2 15:36 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class PlayerMessionRelationServiceImpl implements PlayerMessionRelationService {

    @Autowired
    private PlayerMessionRelationMapper playerMessionRelationMapper;

    @Override
    public List<PlayerMessionRelation> selectByPlayerId(String playerId) {
        Example example = new Example(PlayerMessionRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("playerId", playerId);
        return playerMessionRelationMapper.selectByExample(example);
    }

    @Override
    public PlayerMessionRelation selectByBean(PlayerMessionRelation selectRelation) {
        return playerMessionRelationMapper.selectOne(selectRelation);
    }

    @Override
    public void updateByIdSelective(PlayerMessionRelation relation) {
        playerMessionRelationMapper.updateByPrimaryKeySelective(relation);
    }
}
