package siyi.game.bo;

import siyi.game.dao.entity.Player;

public class PlayerBo extends Player {
    String wxCode;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }
}
