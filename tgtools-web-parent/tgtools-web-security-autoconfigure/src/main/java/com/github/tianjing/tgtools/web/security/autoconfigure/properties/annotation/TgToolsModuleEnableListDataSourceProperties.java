package com.github.tianjing.tgtools.web.security.autoconfigure.properties.annotation;

import com.github.tianjing.tgtools.web.security.autoconfigure.properties.config.ListDataSourcePropertiesConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.DependsOn;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Documented
@DependsOn("dataSourceListProperties")
@ImportAutoConfiguration({
        ListDataSourcePropertiesConfig.class})
public @interface TgToolsModuleEnableListDataSourceProperties {
}
