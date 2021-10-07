package com.github.tianjing.example.empty.webapp.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 建表 ：建表sql：activiti-engine.jar ->org/activiti/db/create
 * 管理页面：
 * http://ip:port/${context-path}/activiti/explorer/manage/resource/model.html
 * http://ip:port/${context-path}/activiti/explorer/manage/resource/flow.html
 * 模型编辑页面：
 * http://ip:port/${context-path}/activiti/resource/modeler.html?modelId=57501
 */
@Configuration
@MapperScan(basePackages = {"tgtools.activiti.explorer.dao"}, sqlSessionFactoryRef = "activitiExplorerSqlSessionFactory")
public class ActivitiConfig extends tgtools.activiti.explorer.config.ActivitiConfig {

}
