package siyi.game.service.impl.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import siyi.game.dao.PlayerMessionRelationMapper;
import siyi.game.dao.entity.PlayerMessionRelation;
import siyi.game.service.mission.MessionConfigService;
import siyi.game.service.mission.PlayerMessionRelationService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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

    @Autowired
    private MessionConfigService messionConfigService;

    @Override
    public List<PlayerMessionRelation> selectByPlayerId(String playerId) {
        Example example = new Example(PlayerMessionRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("playerId", playerId);
        example.setOrderByClause("id DESC");
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

    @Override
    public List<PlayerMessionRelation> createNewMession(String playerId, List<PlayerMessionRelation> relations) {

        List<PlayerMessionRelation> list = selectByPlayerId(playerId);
        if (CollectionUtils.isEmpty(list)) {
            // 玩家第一次进入游戏，无任务记录，任务取两个主线，一个支线
            list = messionConfigService.createNewMession(playerId, null);
        } else {
            list = messionConfigService.createNewMession(playerId, relations);
        }
        return list;
    }

    @Override
    public List<PlayerMessionRelation> selectLastThreeRelationByPlayerId(String playerId) {
        Example example = new Example(PlayerMessionRelation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("playerId", playerId);
        example.setOrderByClause("id desc");
        List<PlayerMessionRelation> playerMessionRelations = playerMessionRelationMapper.selectByExample(example);
        List<PlayerMessionRelation> targetList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(playerMessionRelations) && playerMessionRelations.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                targetList.add(playerMessionRelations.get(i));
            }
        } else {
            targetList = playerMessionRelations;
        }
        return targetList;
    }
}
