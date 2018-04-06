package tgtools.spirngbootweb.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import tgtools.web.develop.mybatis.interceptor.DataTableInterceptor;
import tk.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import tgtools.util.StringUtil;
import tgtools.web.db.TransactionDataAccess;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by PrimaryKey on 17/2/4.
 */

@Configuration
public class DbcpDBConfig {
    private Logger logger = LoggerFactory.getLogger(DbcpDBConfig.class);

    @Bean
    public tk.mybatis.spring.mapper.MapperScannerConfigurer mapperScannerConfigurer1()
    {
        tk.mybatis.spring.mapper.MapperScannerConfigurer dd =new tk.mybatis.spring.mapper.MapperScannerConfigurer();
        dd.setBasePackage("tgtools.spirngbootweb.demo.mybatis.db1");
        dd.setSqlSessionFactoryBeanName("sqlSessionFactory1");
        return dd;
    }
    @Bean(initMethod = "init",destroyMethod = "close")   //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {

        DruidDataSource datasource = new DruidDataSource();

        //使用本地库
        String h2_1_url="jdbc:h2:file:${path}\\db\\db1;FILE_LOCK=NO;INIT=CREATE SCHEMA IF NOT EXISTS TG\\;SET SCHEMA TG;";
        String h2_1_driver="org.h2.Driver";

        h2_1_url= StringUtil.replace(h2_1_url,"${path}",getClassPath());
        datasource.setUrl(h2_1_url);
        datasource.setDriverClassName(h2_1_driver);
        String localusername="TG";
        String localpassword="TG123";
        datasource.setUsername(localusername);
        datasource.setPassword(localpassword);



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
    @Bean(destroyMethod = "close")
    public TransactionDataAccess getLocalDataAccess() {
        TransactionDataAccess localDataAccess = new TransactionDataAccess();
        try {
            localDataAccess.init(dataSource());
            tgtools.db.DataBaseFactory.add("localDATAACCESS", localDataAccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localDataAccess;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations( new Resource[]{ new UrlResource(org.springframework.util.ResourceUtils.getURL("classpath:mybatis/db1/UserMapper.xml"))});
        //驼峰命名
        org.apache.ibatis.session.Configuration config =new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(config);
        //支持返回DataTable类型
        factoryBean.setPlugins(new Interceptor[]{new DataTableInterceptor()});
        return factoryBean.getObject();

    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
        return template;
    }

}

