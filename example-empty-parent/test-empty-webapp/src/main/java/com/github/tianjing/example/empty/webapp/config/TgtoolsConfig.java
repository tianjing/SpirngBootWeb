package com.github.tianjing.example.empty.webapp.config;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.socket.config.annotation.DelegatingWebSocketConfiguration;
import tgtools.log.LoggerFactory;
import tgtools.message.MessageFactory;
import tgtools.web.platform.Platform;
import tgtools.web.platform.PlatformDispatcherServlet;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.net.URL;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */
@Configuration
public class TgtoolsConfig {

    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DataSource dataSource;


    @Bean
    public CacheManager cacheManager() {
        try {
            URL url = ResourceUtils.getURL("classpath:config/ehcache.xml");
            tgtools.cache.CacheFactory.init(url);
            return tgtools.cache.CacheFactory.getCacheManager();
        } catch (Exception e) {
            LoggerFactory.getDefault().error("缓存初始化失败；原因：" + e.getMessage(), e);
            return null;
        }
    }

    @Bean
    public ServletRegistrationBean restServlet() {
        //注解扫描上下文
        //org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
        //org.springframework.web.context.support.XmlWebApplicationContext;
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
        //启动 websocket
        applicationContext.register(DelegatingWebSocketConfiguration.class);
        //通过构造函数指定dispatcherServlet的上下文
        PlatformDispatcherServlet rest_dispatcherServlet
                = new PlatformDispatcherServlet();

        rest_dispatcherServlet.setApplicationContext(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean
                = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/myrest/*");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("rest");
        return registrationBean;
    }

    /**
     *
     */
    @PostConstruct
    public void startup() {
        cacheManager();

        Platform.startup(applicationContext, false, false, false, false, false, false);
        //springboot 默认不使用log4j所以可以不用 如果使用 请 仔细查看 pom 中   <!-- 排除 默认日志  使用log4j 开始-->
        loadLogger();
        //使用数据源管理器 tgtools.db.DataBaseFactory
        loadDataSource();
        //插件模型
        loadPlugins();
        //消息
        loadMessage();
        //自定义rest（配合插件可以组合插件）
        restServlet();
    }

    protected void loadDataSource() {
        try {

            tgtools.web.db.TransactionDataAccess dataAccess = new tgtools.web.db.TransactionDataAccess(dataSource);
            tgtools.db.DataBaseFactory.add("MyDATAACCESS", dataAccess);
            System.out.println("MyDATAACCESS: " + tgtools.db.DataBaseFactory.getDefault().getDataBaseType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化日志
     */
    protected void loadLogger() {
        try {
            //Log4jFactory.start("classpath:config/log4j.xml");
            // LoggerFactory.getDefault().info("Platform 初始化完毕========");
            URL url = ResourceUtils.getURL("classpath:config/logback.xml");
            tgtools.web.log.logback.LogbackFactory.start(url);
        } catch (Exception ex) {
            LoggerFactory.getDefault().error("Logger 加载失败。", ex);
        }
    }

    /**
     * 加载所有插件
     */
    protected void loadPlugins() {
        String path = Platform.getServerPath() + "Plugins/";
        try {
            tgtools.plugin.PluginFactory.startup(path);
        } catch (Exception ex) {
            LoggerFactory.getDefault().error("插件管理器启动失败", ex);
        }
    }

    protected void loadMessage() {
        MessageFactory.start();
    }

}
