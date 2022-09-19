package com.github.tianjing.test.alibaba.webapp.config;

import in.togetu.tablestore.repository.config.EnableTableStoreRepositories;
import in.togetu.tscommon.config.AliyunProductConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableTableStoreRepositories(basePackages = "com.github.tianjing.test.alibaba.webapp.repository")
public class ServiceConfig {

    @Bean("tsConfig")
    @ConfigurationProperties(prefix = "tablestore")
    public AliyunProductConfig getTableStoreConfig() {
        // manual fill the config if not use spring boot
        return new AliyunProductConfig();
    }
}
