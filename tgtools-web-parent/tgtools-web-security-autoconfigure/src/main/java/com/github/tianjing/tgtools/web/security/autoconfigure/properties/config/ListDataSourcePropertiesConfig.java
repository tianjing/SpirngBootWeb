package com.github.tianjing.tgtools.web.security.autoconfigure.properties.config;

import com.github.tianjing.tgtools.web.security.autoconfigure.properties.DataSourceListProperties;
import com.github.tianjing.tgtools.web.security.autoconfigure.properties.DataSourceListPropertiesLoadedListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 数据源集合配置项
 * 配置后 会自动根据配置信息 添加 tgtools.db.DataBaseFactory 中
 * app:
 * datasource:
 * configs:
 * - name: OMSDATAACCESS
 * url: jdbc:dm://172.17.3.143:5236
 * username: OMSREAD
 * password: OMSREAD
 * driverClassName: dm.jdbc.driver.DmDriver
 * - name: WCPTDATAACCESS
 * url: jdbc:dm://26.47.6.97:7236
 * username: WCPT
 * password: WCPT123456
 * driverClassName: dm.jdbc.driver.DmDriver
 * <p>
 * 加载出错 可能需要排除
 * exclude = {DataSourceAutoConfiguration.class, JdbcTemplateAutoConfiguration.class,
 * DataSourceTransactionManagerAutoConfiguration.class}
 *
 * @author 田径
 * @date 2019-12-04 14:50
 * @desc
 **/
public class ListDataSourcePropertiesConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Environment environment;
    @Value("${app.datasource.database.add:false}")
    private boolean addDataBase;
    @Value("${app.datasource.spring.add:false}")
    private boolean addSpringBean;

    @ConfigurationProperties(prefix = "app.datasource")
    @Bean
    public DataSourceListProperties dataSourceListProperties() {
        return new DataSourceListProperties(addDataBase, addSpringBean);
    }

    @Bean
    public DataSourceListPropertiesLoadedListener dataSourceListPropertiesLoadedListener() {
        return new DataSourceListPropertiesLoadedListener();
    }

}
