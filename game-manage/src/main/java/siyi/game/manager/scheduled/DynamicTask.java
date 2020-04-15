package siyi.game.manager.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import siyi.game.bo.TaskInfo;
import siyi.game.framework.ApplicationContextHelper;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 动态任务：可执行、停止、重新设定启动间隔
 */
@Slf4j
@Component
public class DynamicTask {
    // 保存定时任务
    private static Map<String, ScheduledFuture<?>> futuresMap = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 添加任务
     * @param taskInfo
     */
    public void addTask(TaskInfo taskInfo) {
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(getTask(taskInfo), getTrigger(taskInfo));
        futuresMap.put(taskInfo.getTaskName(), future);
    }

    /**
     * 添加任务
     * @param taskInfo
     * @param param 执行任务需要的参数
     */
    public void addTask(TaskInfo taskInfo, Map param) {
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(getTask(taskInfo, param), getTrigger(taskInfo));
        futuresMap.put(taskInfo.getTaskName(), future);
    }

    /**
     * 获取需要执行的任务
     * @param taskInfo
     * @return
     */
    private Runnable getTask(TaskInfo taskInfo){
        return new Runnable() {
            @Override
            public void run() {
                Class<?> clazz;
                try {
                    clazz = Class.forName(taskInfo.getClassName());
                    String className = lowerFirstCapse(clazz.getSimpleName());
                    Object bean = (Object) ApplicationContextHelper.getBean(className);
                    Method method = ReflectionUtils.findMethod(bean.getClass(), taskInfo.getMethod());
                    ReflectionUtils.invokeMethod(method, bean);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 获取需要执行的任务
     * @param taskInfo
     * @param param 执行任务需要的参数
     * @return
     */
    private Runnable getTask(TaskInfo taskInfo, Map param){
        return new Runnable() {
            @Override
            public void run() {
                Class<?> clazz;
                try {
                    clazz = Class.forName(taskInfo.getClassName());
                    String className = lowerFirstCapse(clazz.getSimpleName());
                    Object bean = (Object) ApplicationContextHelper.getBean(className);
                    Method method = ReflectionUtils.findMethod(bean.getClass(), taskInfo.getMethod(), Map.class);
                    ReflectionUtils.invokeMethod(method, bean, param);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 删除任务
     * @param taskName
     * @return
     */
    public boolean deleteTask(String taskName) {
        ScheduledFuture toBeRemovedFuture = futuresMap.remove(taskName);
        if (toBeRemovedFuture != null) {
            toBeRemovedFuture.cancel(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 终止正在执行的任务
     * @param taskName
     * @return
     */
    public boolean stopTask(String taskName) {
        ScheduledFuture future = futuresMap.remove(taskName);
        if (future != null) {
            future.cancel(true);
            return true;
        }
        return false;
    }

    public void updateTask(TaskInfo s) {
        ScheduledFuture toBeRemovedFuture = futuresMap.remove(s.getTaskName());
        if (toBeRemovedFuture != null) {
            toBeRemovedFuture.cancel(true);
        }
        addTask(s);
    }





    private Trigger getTrigger(TaskInfo taskInfo){
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(taskInfo.getCron());
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };

    }

    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
