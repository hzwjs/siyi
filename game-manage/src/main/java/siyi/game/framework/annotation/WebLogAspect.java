package siyi.game.framework.annotation;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @program siyi
 * @description WebLog切面日志类
 * @author: hzw
 * @create: 2020-04-29 16:21
 **/
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("@annotation(siyi.game.framework.annotation.WebLog)")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        String thread = Thread.currentThread().getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String methodDescription = getMethodDescription(joinPoint);
        log.info("---------------------------------------- start : {} ----------------------------------------", thread);
        log.info("URL           :{}", request.getRequestURL().toString());
        log.info("description   :{}", methodDescription);
        log.info("Request Args  :{}", JSON.toJSONString(joinPoint.getArgs()));
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Response Result : {}", JSON.toJSONString(result));
        log.info("Time-consuming: {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    @After("webLog()")
    public void doAfter() {
        String thread = Thread.currentThread().getName();
        log.info("---------------------------------------- end : {} ----------------------------------------", thread);
    }

    private String getMethodDescription(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        WebLog webLog = method.getAnnotation(WebLog.class);
        return webLog.description();
    }
}
