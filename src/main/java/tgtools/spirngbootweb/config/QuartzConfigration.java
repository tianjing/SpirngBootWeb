package tgtools.spirngbootweb.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import tgtools.quartz.explorer.utils.QuartzManager;
import tgtools.util.ReflectionUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 访问地址如：http://ip:port/${context-path}/quartz/explorer/manage/resource/quartz.html
 * 建表sql：tgtools.quartz.explorer.jar  -> tgtools/quartz/explorer/db/sql.sql
 */
@Configuration
@MapperScan(basePackages = {"tgtools.quartz.explorer.dao"}, sqlSessionFactoryRef = "quartzSqlSessionFactory")
public class QuartzConfigration  extends tgtools.quartz.explorer.config.QuartzConfigration{


//    /**
//     * 指定数据库类型
//     */
//    @Override
//    protected String getDataBaseType() {
//        return "dm6";
//    }


//    /**
//     * 指定Mapper.xml
//     */
//    @Bean
//    @Override
//    public SqlSessionFactory quartzSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
//        config.setMapUnderscoreToCamelCase(true);
//        factoryBean.setConfiguration(config);
//        factoryBean.setMapperLocations(new Resource[]{new UrlResource(ReflectionUtil.getResource("config/TaskMapper_dm6.xml"))});
//        return factoryBean.getObject();
//
//    }

//    /**
//     * 指定quartz.properties
//     */
//    @Bean
//    @Override
//    public Properties quartzProperties() throws IOException {
//        InputStream inputStream = null;
//        try {
//            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//            Properties prop = new Properties();
//
//            prop.load(ReflectionUtil.getResourceAsStream("config/quartz.properties"));
//            propertiesFactoryBean.setProperties(prop);
//            propertiesFactoryBean.afterPropertiesSet();
//            return propertiesFactoryBean.getObject();
//        } finally {
//            if (null != inputStream) {
//                try {
//                    inputStream.close();
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
}
