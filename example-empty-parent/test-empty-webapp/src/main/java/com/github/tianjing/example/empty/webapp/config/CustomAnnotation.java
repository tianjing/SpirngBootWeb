package com.github.tianjing.example.empty.webapp.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@ImportAutoConfiguration({CustomConfig.class})
public @interface CustomAnnotation
{
    String value();
    int size();
}
