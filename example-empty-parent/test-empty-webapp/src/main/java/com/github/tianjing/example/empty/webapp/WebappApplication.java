package com.github.tianjing.example.empty.webapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tianjing.example.empty.webapp.config.CustomAnnotation;
import com.github.tianjing.tgtools.web.security.autoconfigure.module.tgtools.annotation.TgToolsEnablePlatform;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;
import tgtools.net.WebClient;
import tgtools.tasks.Task;
import tgtools.tasks.TaskContext;
import tgtools.util.LogHelper;

import java.util.Date;

/**
 * @author 田径
 * @create 2019-02-13 10:05
 * @desc
 **/
@EnableAspectJAutoProxy
@TgToolsEnablePlatform
@CustomAnnotation(value = "1a", size = 2)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class WebappApplication {


    public static void main(String[] args) throws JsonProcessingException {
        User vUser = new ObjectMapper().readValue("{\"name\":\"0xe70xad0xbe\"}",User.class);

        SpringApplication.run(WebappApplication.class, args);
        LogHelper.info("", "==================================================", "main");
        for (int i = 0; i < 20; i++) {
            new Test().run(null);
        }
    }

    public static class Test extends Task {

        @Override
        protected boolean canCancel() {
            return false;
        }

        @Override
        public void run(TaskContext p_Param) {
            int a =0;
            while (true) {
                a =1;
            }
        }
    }
    public static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
