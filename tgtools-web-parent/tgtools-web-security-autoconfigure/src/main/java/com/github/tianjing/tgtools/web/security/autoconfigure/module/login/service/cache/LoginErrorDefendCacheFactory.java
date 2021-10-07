package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 田径
 * @date 2020-08-26 11:17
 * @desc
 **/
public class LoginErrorDefendCacheFactory {

    public static LoginErrorDefendCache createLoginErrorDefendCache() {
        LoginErrorDefendCache vLoginErrorDefendCache = createLoginErrorDefendRedisCache();
        if (null == vLoginErrorDefendCache) {
            vLoginErrorDefendCache = createLoginErrorDefendEhCache();
        }
        return vLoginErrorDefendCache;
    }

    public static LoginErrorDefendCache createLoginErrorDefendCache(
            CacheManager pCacheManager
            , RedisConnectionFactory pRedisConnectionFactory
            , RedisTemplate pRedisTemplate) {
        LoginErrorDefendCache vLoginErrorDefendCache = createLoginErrorDefendRedisCache(pRedisConnectionFactory, pRedisTemplate);
        if (null == vLoginErrorDefendCache) {
            vLoginErrorDefendCache = createLoginErrorDefendEhCache(pCacheManager);
        }
        return vLoginErrorDefendCache;
    }

    public static <T> T getBean(Class pClass) {
        try {
            return (T) tgtools.web.platform.Platform.getBeanFactory().getBean(pClass);
        } catch (Exception e) {
            return null;
        }
    }

    public static LoginErrorDefendCache createLoginErrorDefendEhCache() {
        CacheManager pCacheManager = getBean(CacheManager.class);
        return createLoginErrorDefendEhCache(pCacheManager);
    }

    public static LoginErrorDefendCache createLoginErrorDefendEhCache(CacheManager pCacheManager) {
        if (null == pCacheManager) {
            return null;
        }
        Cache vCache = initCache(pCacheManager);
        return new LoginErrorDefendEhCache(vCache);
    }

    public static LoginErrorDefendCache createLoginErrorDefendRedisCache() {
        RedisConnectionFactory pRedisConnectionFactory = getBean(RedisConnectionFactory.class);

        return createLoginErrorDefendRedisCache(pRedisConnectionFactory, null);
    }

    public static LoginErrorDefendCache createLoginErrorDefendRedisCache(RedisConnectionFactory pRedisConnectionFactory, RedisTemplate pRedisTemplate) {
        if (null == pRedisConnectionFactory) {
            return null;
        }

        RedisTemplate vRedisTemplate = pRedisTemplate;

        if (null == vRedisTemplate && null != pRedisConnectionFactory) {
            vRedisTemplate = createLoginErrorDefendRedisTemplate(pRedisConnectionFactory);
        }
        if (null != vRedisTemplate) {
            return new LoginErrorDefendRedisCache(vRedisTemplate);
        }

        return null;
    }

    protected static RedisTemplate<String, Object> createLoginErrorDefendRedisTemplate(RedisConnectionFactory pFactory) {
        RedisTemplate<String, Object> vTemplate = new RedisTemplate<>();
        vTemplate.setConnectionFactory(pFactory);
        vTemplate.setKeySerializer(new StringRedisSerializer());
        vTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        vTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        vTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        vTemplate.afterPropertiesSet();
        return vTemplate;
    }

    protected static Cache initCache(CacheManager pCacheManager) {
        Cache vCache = pCacheManager.getCache("LoginErrorDefend");
        if (null == vCache) {
            vCache = new Cache("LoginErrorDefend", 10000, false, false, 6000000, 6000000);
            pCacheManager.addCache(vCache);
        }
        return vCache;
    }

}
