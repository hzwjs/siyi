package siyi.game.utill;

import java.util.UUID;

/**
 * description: UUIDUtil 获取UUID工具类 <br>
 * date: 2020/2/26 22:13 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
public class UUIDUtil {

    //得到32位的uuid
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
