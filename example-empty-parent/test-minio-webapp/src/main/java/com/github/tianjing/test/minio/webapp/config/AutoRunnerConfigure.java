package com.github.tianjing.test.minio.webapp.config;

import com.github.tianjing.test.minio.webapp.runner.MinIoTestRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田径
 * @date 2021-12-07 14:13
 * @desc
 **/
@Configuration
public class AutoRunnerConfigure {

    @Bean
    public MinIoTestRunner minIoTestRunner() {
        return new MinIoTestRunner();
    }
}
