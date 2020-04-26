package siyi.game.bo;

import lombok.Data;
import siyi.game.dao.entity.Player;

import java.util.Date;

@Data
public class PlayerBo extends Player {
    String wxCode;
    // 当前体力
    Integer hp;
    // 满体力值
    Integer maxHp;
    // 体力开始恢复的时间；
    Date startTimeRecover;

}
