package com.github.tianjing.tgtools.web.security.autoconfigure.cache.config;

import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import tgtools.cache.CacheFactory;
import tgtools.log.LoggerFactory;

import java.net.URL;

/**
 * @author 田径
 * @date 2019-10-11 11:49
 * @desc
 **/
@Configuration
public class EhcacheConnectionConfig {

    @Bean
    public CacheManager ehcacheManager() {
        try {
            URL url = ResourceUtils.getURL("classpath:config/ehcache.xml");
            CacheFactory.init(url);
            return CacheFactory.getCacheManager();
        } catch (Exception var2) {
            LoggerFactory.getDefault().error("缓存初始化失败；原因：" + var2.getMessage(), var2);
            return null;
        }
    }


}
