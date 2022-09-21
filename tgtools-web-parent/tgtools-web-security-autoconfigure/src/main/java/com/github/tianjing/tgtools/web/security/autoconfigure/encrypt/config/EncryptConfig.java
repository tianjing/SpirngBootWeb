package com.github.tianjing.tgtools.web.security.autoconfigure.encrypt.config;

import com.github.tianjing.tgtools.encrypt.EncrypterFactory;
import com.github.tianjing.tgtools.encrypt.spring.security.DelegatingEncrypter;
import com.github.tianjing.tgtools.web.security.autoconfigure.bean.AppSecurityConfig;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tgtools.db.DataBaseFactory;
import tgtools.util.GUID;
import tgtools.web.clock.CacheDatabaseClockImpl;

/**
 * @author 田径
 * @date 2019-10-11 11:49
 * @desc
 **/
@Configuration
public class EncryptConfig {

    @Bean
    public DelegatingEncrypter clock(AppSecurityConfig pAppSecurityConfig) {
        DelegatingEncrypter vDelegatingEncrypter = EncrypterFactory.createDelegatingEncrypter(pAppSecurityConfig.getEncrypterId(),pAppSecurityConfig.getEncrypterKey());
        return vDelegatingEncrypter;
    }


}
