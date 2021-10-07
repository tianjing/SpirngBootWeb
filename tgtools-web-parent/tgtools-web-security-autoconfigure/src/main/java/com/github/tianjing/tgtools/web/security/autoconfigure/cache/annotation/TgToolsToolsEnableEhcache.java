package com.github.tianjing.tgtools.web.security.autoconfigure.cache.annotation;

import com.github.tianjing.tgtools.web.security.autoconfigure.cache.config.EhcacheConnectionConfig;
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
@TgToolsEnableEhcacheClock
@ImportAutoConfiguration({EhcacheConnectionConfig.class})
public @interface TgToolsToolsEnableEhcache {

}
