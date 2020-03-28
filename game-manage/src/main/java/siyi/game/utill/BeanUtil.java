package siyi.game.utill;

import net.sf.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }
}
