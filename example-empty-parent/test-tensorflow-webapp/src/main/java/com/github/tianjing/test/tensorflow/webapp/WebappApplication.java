package com.github.tianjing.test.tensorflow.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tgtools.util.LogHelper;

/**
 * @author 田径
 * @create 2019-02-13 10:05
 * @desc
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
        LogHelper.info("", "==================================================", "main");
    }

}
