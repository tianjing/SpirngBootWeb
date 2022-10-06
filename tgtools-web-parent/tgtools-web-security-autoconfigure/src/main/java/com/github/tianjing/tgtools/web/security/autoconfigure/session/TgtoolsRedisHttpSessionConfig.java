package com.github.tianjing.tgtools.web.security.autoconfigure.session;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 田径
 * @date 2019-07-12 20:15
 * @desc
 **/

public class TgtoolsRedisHttpSessionConfig extends AbstractCustomHttpSession {

    @Autowired
    protected RedisIndexedSessionRepository redisIndexedSessionRepository;
    @Value("${server.servlet.session.timeout:1800}")
    private int timeout;

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory factory) {
        RedisCacheConfiguration vDefaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        //设置默认超过期时间是30秒
        vDefaultCacheConfig.entryTtl(Duration.ofSeconds(30));
        //初始化RedisCacheManager

        Map<String, RedisCacheConfiguration> vConfigMap = new HashMap<>();
        vConfigMap.put("TimerCache", vDefaultCacheConfig.entryTtl(Duration.ofSeconds(120)));
        Set<String> vCacheNames = new HashSet<>();
        vCacheNames.add("TimerCache");


        return RedisCacheManager.builder(factory).initialCacheNames(vCacheNames).withInitialCacheConfigurations(vConfigMap).build();
    }


    @Override
    @PostConstruct
    public void init() {
        super.init();
        redisIndexedSessionRepository.setRedisKeyNamespace(getSessionCacheName() + ":session");
        if (timeout > 0) {
            redisIndexedSessionRepository.setDefaultMaxInactiveInterval(timeout);
        }
    }


}
