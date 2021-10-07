package com.github.tianjing.example.empty.webapp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import tgtools.util.StringUtil;

import javax.sql.DataSource;

/**
 * Created by PrimaryKey on 17/2/4.
 */

@Configuration
public class DbcpDBConfig2 {
    private Logger logger = LoggerFactory.getLogger(DbcpDBConfig2.class);

    @Bean
    public tk.mybatis.spring.mapper.MapperScannerConfigurer mapperScannerConfigurer2()
    {
        tk.mybatis.spring.mapper.MapperScannerConfigurer dd =new tk.mybatis.spring.mapper.MapperScannerConfigurer();
        dd.setBasePackage("tgtools.spirngbootweb.demo.mybatis.db2");
        dd.setSqlSessionFactoryBeanName("sqlSessionFactory2");
        return dd;
    }

    @ConfigurationProperties(prefix = "spring.datasource.db2")
    @Bean(initMethod = "init", destroyMethod = "close")   //声明其为Bean实例
    public DataSource dataSource2() {
        DruidDataSource datasource = new DruidDataSource();
        //本地库
        String h2_1_url="jdbc:h2:file:${path}\\db\\db2;FILE_LOCK=NO;INIT=CREATE SCHEMA IF NOT EXISTS TG\\;SET SCHEMA TG;";
        h2_1_url= StringUtil.replace(h2_1_url,"${path}",getClassPath());
        datasource.setUrl(h2_1_url);

        return datasource;
    }
    private String getClassPath()
    {
        String path="";
        try {
            path = org.springframework.util.ResourceUtils.getURL("classpath:").getPath();
        } catch (Exception e) {
        }
        if (path.indexOf("classes") > 0) {
            if (path.indexOf("target") > 0) {
                path = path.substring(0, path.substring(0, path.indexOf("target")).lastIndexOf("/"));
            }else {
                path = path.substring(0, path.substring(0, path.indexOf("classes")).lastIndexOf("/"));
            }
        }
        return path;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new Resource[]{ new UrlResource(org.springframework.util.ResourceUtils.getURL("classpath:mybatis/db2/UserMapper.xml"))});
        //驼峰命名
        org.apache.ibatis.session.Configuration config =new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(config);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory2) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2);
        return template;
    }
}

