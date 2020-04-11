package siyi.game.utill;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheClass {
    private static Map<String,String> cache = new ConcurrentHashMap<String, String>();

    public static void setCache(String key, String obj){
        cache.put(key,obj);
    }

    public static String getCache(String key){
        return cache.get(key);
    }

    public static  void removeCache(String key){
        cache.remove(key);
    }

}
