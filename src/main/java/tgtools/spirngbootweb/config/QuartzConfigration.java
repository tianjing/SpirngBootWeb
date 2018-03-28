package tgtools.spirngbootweb.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import tgtools.util.ReflectionUtil;

import javax.sql.DataSource;

/**
 * 访问地址如：http://ip:port/${context-path}/quartz/explorer/manage/resource/quartz.html
 * 建表sql：tgtools.quartz.explorer.jar  -> tgtools/quartz/explorer/db/sql.sql
 */
@Configuration
@MapperScan(basePackages = {"tgtools.quartz.explorer.dao"}, sqlSessionFactoryRef = "quartzSqlSessionFactory")
public class QuartzConfigration  extends tgtools.quartz.explorer.config.QuartzConfigration{

//    数据库类型
//    @Override
//    protected String getDataBaseType() {
//        return "dm6";
//    }

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
}
