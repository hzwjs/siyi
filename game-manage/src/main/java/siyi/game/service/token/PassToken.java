package siyi.game.service.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description: 跳过token验证 <br>
 * date: 2020/2/23 14:08 <br>
 * author: zhengzhiqiang <br>
 * version: 1.0 <br>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
