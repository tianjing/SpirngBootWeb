package com.github.tianjing.tgtools.web.security.autoconfigure.module.tgtools.annotation;

import com.github.tianjing.tgtools.web.security.autoconfigure.module.tgtools.TgToolsConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Documented
@ImportAutoConfiguration({TgToolsConfig.class})
public @interface TgToolsEnablePlatform {
}
