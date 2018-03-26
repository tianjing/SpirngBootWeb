package tgtools.spirngbootweb.common.config;

import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import tgtools.log.LoggerFactory;
import tgtools.message.MessageFactory;
import tgtools.web.log.Log4jFactory;
import tgtools.web.platform.Platform;
import tgtools.web.platform.PlatformDispatcherServlet;

import javax.annotation.PostConstruct;
import java.net.URL;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 8:54
 */
@Configuration
@EnableWebSocket
public class TgtoolsConfig {

    @Autowired
    ApplicationContext applicationContext;



    @Bean
    public CacheManager cacheManager()  {
        try {
            URL url = org.springframework.util.ResourceUtils.getURL("classpath:config/ehcache.xml");
            tgtools.cache.CacheFactory.init(url);
            return tgtools.cache.CacheFactory.getCacheManager();
        }catch (Exception e)
        {
            LoggerFactory.getDefault().error("缓存初始化失败；原因："+e.getMessage(),e);
            return null;
        }
    }
    @Bean
    public ServletRegistrationBean restServlet(){
        //注解扫描上下文
        //org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
        //org.springframework.web.context.support.XmlWebApplicationContext;
        AnnotationConfigWebApplicationContext applicationContext
                = new AnnotationConfigWebApplicationContext();
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

    @PostConstruct
    public void setSharedVariable() {
        cacheManager();
        Platform.startup(applicationContext,false,false,false,false,false,false);
        //springboot 默认不使用log4j所以可以不用 如果使用 请 仔细查看 pom 中   <!-- 排除 默认日志  使用log4j 开始-->
        loadLog4j();
        loadPlugins();
        loadMessage();
        restServlet();
    }
    /**
     * 初始化日志
     */
    protected void loadLog4j()
    {
        Log4jFactory.start("classpath:config/log4j.xml");
        LoggerFactory.getDefault().info("Platform 初始化完毕========");
    }

    /**
     * 加载所有插件
     */
    protected void loadPlugins()
    {
        String path=tgtools.web.platform.Platform.getServerPath()+"Plugins/";
        try {
            tgtools.plugin.PluginFactory.startup(path);
        }catch (Exception ex)
        {
            LoggerFactory.getDefault().error("插件管理器启动失败",ex);
        }
     }
    protected void loadMessage()
    {
        MessageFactory.start();
    }
}
