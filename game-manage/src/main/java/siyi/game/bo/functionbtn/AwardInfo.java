package siyi.game.bo.functionbtn;

public class AwardInfo {
    private String playerId; // 用户id
    private String itemId; // 道具id
    private int num; // 道具数量
    private String gameCode; // 游戏编号
    private int multiple; // 奖励倍数，将道具配置表中的所有道具乘以这个倍数

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }
}
