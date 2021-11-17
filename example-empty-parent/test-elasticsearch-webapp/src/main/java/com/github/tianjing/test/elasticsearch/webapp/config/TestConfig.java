package com.github.tianjing.test.elasticsearch.webapp.config;

import com.github.tianjing.test.elasticsearch.webapp.service.NoticeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田径
 * @date 2020-03-05 15:11
 * @desc
 **/
@Configuration
public class TestConfig {

    @Bean
    public MyApplicationRunner mMyApplicationRunner() {
        return new MyApplicationRunner();
    }

    public static class MyApplicationRunner implements ApplicationRunner {
        @Autowired
        public NoticeInfoService noticeInfoService;

        @Override
        public void run(ApplicationArguments args) throws Exception {
            noticeInfoService.testAdd();
        }
    }
}
