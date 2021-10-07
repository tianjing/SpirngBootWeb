package com.github.tianjing.tgtools.web.security.autoconfigure.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import tgtools.util.GUID;
import tgtools.web.clock.CacheDatabaseClockImpl;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author 田径
 * @date 2019-07-13 22:52
 * @desc
 **/
@Configuration
public class RedisClockConfig {

    @Bean
    public CacheDatabaseClockImpl clock(LettuceConnectionFactory pFactory) {
        RedisCacheConfiguration vDefaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        Map<String, RedisCacheConfiguration> vConfigMap = new HashMap<>();
        vConfigMap.put("TimerCache", vDefaultCacheConfig.entryTtl(Duration.ofSeconds(120)));
        Set<String> vCacheNames = new HashSet<>();
        vCacheNames.add("TimerCache");

        RedisCacheManager vRedisCacheManager = RedisCacheManager.builder(pFactory).initialCacheNames(vCacheNames).withInitialCacheConfigurations(vConfigMap).build();

        CacheDatabaseClockImpl vClock = new CacheDatabaseClockImpl();
        vClock.setIDataAccess(tgtools.db.DataBaseFactory.getDefault());
        vClock.setCacheManager(vRedisCacheManager);
        vClock.setCacheKey(GUID.newGUID());
        return vClock;
    }

}
