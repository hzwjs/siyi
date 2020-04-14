package siyi.game.bo.gamelevel;

import lombok.Data;

@Data
public class MissionItem {
    /**
     * 玩家ID
     */
    private String playerId;

    /**
     * 任务ID
     */
    private String messionId;

    /**
     * 任务进度
     */
    private String processNum;
}
