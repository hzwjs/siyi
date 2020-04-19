package siyi.game.bo;

import lombok.Data;

@Data
public class TaskInfo {
    private String taskName;
    private String cron;
    private String className;
    private String method;
    Long period;
    Long delay;
}
