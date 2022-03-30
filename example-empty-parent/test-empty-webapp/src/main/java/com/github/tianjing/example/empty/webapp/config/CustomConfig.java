package com.github.tianjing.example.empty.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.Map;

public class CustomConfig {

    @Qualifier

    @Autowired
    protected ApplicationContext applicationContext;


    @PostConstruct
    public void init() {
        Map<String, Object> vBeanMap = applicationContext.getBeansWithAnnotation(CustomAnnotation.class);
        vBeanMap.values().forEach((item) -> {
            CustomAnnotation vCustomAnnotation = item.getClass().getAnnotation(CustomAnnotation.class);
            System.out.println("vCustomAnnotation.size()");
        });
        System.out.println("vCustomAnnotation.size()");
    }

}
