package siyi.game.service.mission;

import siyi.game.dao.entity.PlayerMessionRelation;

import java.util.List;

/**
 * description: MessionConfigService <br>
 * date: 2020/4/2 11:46 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public interface MessionConfigService {

    List<PlayerMessionRelation> createNewMession(String playerId, List<PlayerMessionRelation> relations);
}
