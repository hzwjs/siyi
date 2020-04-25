package siyi.game.utill;

/**
 * 全局常量类
 */
public final class Constants {

    private Constants(){};


    public final static String GAME_CODE_WENWU = "gm001"; // 文武双全游戏编码
    public final static String COMMON_SUCCESS = "1"; // 通用：成功；是等肯定的含义
    public final static String COMMON_FALSE = "0"; // 通用：失败，否等否定的含义
    public final static String GAME_LEVEL_TYPE_WEN = "wen"; // 关卡类型：文关
    public final static String GAME_LEVEL_TYPE_WU = "wu"; // 关卡类型：武关


    public enum GameLevelStaute{
        HP_ERR("error", "体力不足");
        private String key;
        private String value;
        private GameLevelStaute(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
