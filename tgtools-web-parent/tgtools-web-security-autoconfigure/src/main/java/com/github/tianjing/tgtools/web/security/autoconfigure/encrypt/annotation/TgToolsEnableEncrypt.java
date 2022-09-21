package com.github.tianjing.tgtools.web.security.autoconfigure.encrypt.annotation;

import com.github.tianjing.tgtools.web.security.autoconfigure.encrypt.config.EncryptConfig;
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
@ImportAutoConfiguration({EncryptConfig.class})
public @interface TgToolsEnableEncrypt {
}
