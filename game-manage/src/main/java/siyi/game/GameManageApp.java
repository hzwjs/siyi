package siyi.game;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;


@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@MapperScan(basePackages="siyi.game.dao")
public class GameManageApp {

    private static final Logger log = LoggerFactory.getLogger(GameManageApp.class);
    public static Map<String, ScheduledFuture> map = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(GameManageApp.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {

        return new Jackson2ObjectMapperBuilder() {

            @Override
            public void configure(ObjectMapper objectMapper) {
                super.configure(objectMapper);
                objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
                objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            }
        };
    }

    // 创建线程
    @EnableAsync
    @Configuration
    class TaskPoolConfig {

        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
            executor.setPoolSize(20);
            executor.setThreadNamePrefix("taskExecutor-");
            return executor;
        }

    }

//    @Bean
//    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
//        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
//        executor.setPoolSize(20);
//        executor.setThreadNamePrefix("taskExecutor-");
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.setAwaitTerminationSeconds(60);
//        return executor;
//    }

}
