package siyi.game.service.impl.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siyi.game.dao.MessionBlankMapper;
import siyi.game.dao.entity.MessionBlank;
import siyi.game.service.mission.MessionBlankService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description: MessionBlankServiceImpl <br>
 * date: 2020/4/2 15:43 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Service
public class MessionBlankServiceImpl implements MessionBlankService {

    @Autowired
    private MessionBlankMapper messionBlankMapper;

    @Override
    public MessionBlank selectByPlayerId(String playerId) {
        Example example = new Example(MessionBlank.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("playerId", playerId);
        return messionBlankMapper.selectOneByExample(example);
    }

    @Override
    public void updateByIdSelective(MessionBlank messionBlank) {
        messionBlankMapper.updateByPrimaryKeySelective(messionBlank);
    }

    @Override
    public void insertSelective(MessionBlank messionBlank) {
        messionBlankMapper.insertSelective(messionBlank);
    }
}
