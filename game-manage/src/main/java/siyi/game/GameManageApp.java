package siyi.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@EnableAutoConfiguration
@ComponentScan
@MapperScan(basePackages="siyi.game.dao")
public class GameManageApp {

    private static final Logger log = LoggerFactory.getLogger(GameManageApp.class);

    public static void main(String[] args) {
        SpringApplication.run(GameManageApp.class, args);
    }

}
