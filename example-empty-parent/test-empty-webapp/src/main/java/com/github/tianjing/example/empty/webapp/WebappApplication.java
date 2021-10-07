package com.github.tianjing.example.empty.webapp;

import com.github.tianjing.tgtools.web.security.autoconfigure.mvc.config.annotation.base.TgToolsBaseEnableSecurityWithNone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tgtools.util.LogHelper;

/**
 * @author 田径
 * @create 2019-02-13 10:05
 * @desc
 **/
@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
        LogHelper.info("", "==================================================", "main");
    }

}
