package siyi.game.bo;

import lombok.Data;
import siyi.game.dao.entity.Player;

@Data
public class PlayerBo extends Player {
    String wxCode;
    // 当前体力
    Integer hp;
    // 满体力值
    Integer maxHp;

}
