package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.annotation;

import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.LoginErrorDefendAutoConfigure;
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
@ImportAutoConfiguration({
        LoginErrorDefendAutoConfigure.class})
public @interface TgToolsModuleEnableLoginErrorDefend {
}
