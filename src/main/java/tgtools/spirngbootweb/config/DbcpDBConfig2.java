package tgtools.spirngbootweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import tk.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import tgtools.util.StringUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by PrimaryKey on 17/2/4.
 */

@Configuration
@MapperScan(basePackages = {"tgtools.spirngbootweb.demo.mybatis.db2"}, sqlSessionFactoryRef = "sqlSessionFactory2" )
public class DbcpDBConfig2 {
    private Logger logger = LoggerFactory.getLogger(DbcpDBConfig2.class);

    @Bean(initMethod = "init", destroyMethod = "close")   //声明其为Bean实例
    public DataSource dataSource2() {
        DruidDataSource datasource = new DruidDataSource();

        //本地库
        String h2_1_url="jdbc:h2:file:${path}\\db\\db2;FILE_LOCK=NO;INIT=CREATE SCHEMA IF NOT EXISTS TG\\;SET SCHEMA TG;";
        String h2_1_driver="org.h2.Driver";
        String username="TG";
        String password="TG123";
        h2_1_url= StringUtil.replace(h2_1_url,"${path}",getClassPath());
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(h2_1_driver);
        datasource.setUrl(h2_1_url);

        //configuration
        datasource.setInitialSize(1);
        datasource.setMinIdle(3);
        datasource.setMaxActive(20);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(30000);
        datasource.setValidationQuery("select 'x'");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        try {
            datasource.setFilters("stat,slf4j");
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }

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
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory2) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory2);
        return template;
    }
}

