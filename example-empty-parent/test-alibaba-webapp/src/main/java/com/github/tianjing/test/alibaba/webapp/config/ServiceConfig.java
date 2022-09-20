package com.github.tianjing.test.alibaba.webapp.config;

import in.togetu.tablestore.repository.config.EnableTableStoreRepositories;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableTableStoreRepositories(basePackages = "com.github.tianjing.test.alibaba.webapp.repository")
public class ServiceConfig {

}
