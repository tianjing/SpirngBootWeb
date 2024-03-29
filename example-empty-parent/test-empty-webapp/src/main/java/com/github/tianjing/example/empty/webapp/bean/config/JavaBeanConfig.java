package com.github.tianjing.example.empty.webapp.bean.config;

import com.github.tianjing.example.empty.webapp.config.TgtoolsConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class JavaBeanConfig implements InitializingBean {
    @Autowired
    TgtoolsConfig tgtoolsConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        //two
        System.out.println("JavaBeanConfig afterPropertiesSet");
    }

    @PostConstruct
    public void init() throws Exception {
        //one
        System.out.println("JavaBeanConfig PostConstruct");
    }

    @Bean(initMethod = "init")
    public MyClass myClass() {
        return new MyClass();
    }


    public static class MyClass implements InitializingBean {
        @Autowired
        TgtoolsConfig tgtoolsConfig;

        public void init() {
            System.out.println("MyClass init");
        }


        @Override
        public void afterPropertiesSet() throws Exception {
            //three
            System.out.println("MyClass afterPropertiesSet");
        }
    }
}
