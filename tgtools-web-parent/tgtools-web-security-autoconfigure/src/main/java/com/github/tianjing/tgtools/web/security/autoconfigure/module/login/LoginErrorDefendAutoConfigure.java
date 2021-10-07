package com.github.tianjing.tgtools.web.security.autoconfigure.module.login;

import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.LoginErrorDefendService;
import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.LoginErrorDefendServiceImpl;
import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache.LoginErrorDefendCache;
import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache.LoginErrorDefendCacheFactory;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 田径
 * @date 2020-08-26 10:56
 * @desc
 **/

public class LoginErrorDefendAutoConfigure {

    @Value("${app.security.login.error.lock-second:600}")
    private int lockSecond = 600;
    @Value("${app.security.login.error.error-times:5}")
    private int errorTimes = 5;

    @Bean
    public LoginErrorDefendService loginErrorDefendService(
            @Autowired(required = false) CacheManager pCacheManager
            , @Autowired(required = false) RedisConnectionFactory pRedisConnectionFactory
            , @Autowired(required = false) @Qualifier(value = "userLoginRedisTemplate") RedisTemplate pRedisTemplate) {
        LoginErrorDefendCache vLoginErrorDefendCache = null;


        if (null != pRedisConnectionFactory && (pRedisConnectionFactory instanceof LettuceConnectionFactory) && !"localhost".equals(((LettuceConnectionFactory) pRedisConnectionFactory).getHostName())) {
            vLoginErrorDefendCache = LoginErrorDefendCacheFactory.createLoginErrorDefendRedisCache(pRedisConnectionFactory, pRedisTemplate);
        } else if (null != pCacheManager) {
            vLoginErrorDefendCache = LoginErrorDefendCacheFactory.createLoginErrorDefendEhCache(pCacheManager);
        }
        return new LoginErrorDefendServiceImpl(vLoginErrorDefendCache, lockSecond, errorTimes);
    }


}
