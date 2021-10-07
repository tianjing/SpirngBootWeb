package com.github.tianjing.example.empty.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import tgtools.freemarker.TableWrapper;

import javax.annotation.PostConstruct;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:17
 */
@Configuration
public class FreeMarkerConfig {
    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver resolver;
    @Autowired
    protected org.springframework.web.servlet.view.InternalResourceViewResolver springResolver;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setObjectWrapper(new TableWrapper());
    }
}
