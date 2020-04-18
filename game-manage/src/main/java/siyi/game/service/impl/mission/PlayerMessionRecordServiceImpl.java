package siyi.game.service.impl.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import siyi.game.dao.PlayerMessionRecordMapper;
import siyi.game.dao.entity.PlayerMessionRecord;
import siyi.game.dao.entity.PlayerMessionRelation;
import siyi.game.service.mission.PlayerMessionRecordService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerMessionRecordServiceImpl implements PlayerMessionRecordService {

    @Autowired
    private PlayerMessionRecordMapper playerMessionRecordMapper;

    @Override
    public void insertSelective(PlayerMessionRecord record) {
        playerMessionRecordMapper.insertSelective(record);
    }

    @Override
    public List<PlayerMessionRecord> selectLastThreeRelationByPlayerId(String playerId) {
        List<PlayerMessionRecord> playerMessionRecords = selectByPlayerId(playerId);
        List<PlayerMessionRecord> targetList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(playerMessionRecords) && playerMessionRecords.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                targetList.add(playerMessionRecords.get(i));
            }
        } else {
            targetList = playerMessionRecords;
        }
        return targetList;
    }

    @Override
    public List<PlayerMessionRecord> selectByPlayerId(String playerId) {
        Example example = new Example(PlayerMessionRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("playerId", playerId);
        example.setOrderByClause("id desc");
        return playerMessionRecordMapper.selectByExample(example);
    }
}
