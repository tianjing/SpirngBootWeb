package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache;

import io.netty.util.internal.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import tgtools.exceptions.APPErrorException;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 田径
 * @date 2020-08-26 11:17
 * @desc
 **/
public class LoginErrorDefendRedisCache implements LoginErrorDefendCache {

    protected RedisTemplate redisTemplate;

    public LoginErrorDefendRedisCache() {
    }

    public LoginErrorDefendRedisCache(RedisTemplate pRedisTemplate) {
        redisTemplate = pRedisTemplate;
    }

    protected RedisTemplate getUserLoginRedisTemplate() {
        if (null == redisTemplate) {
            try {
                Object vUserLoginRedisTemplate = tgtools.web.platform.Platform.getBeanFactory().getBean("userLoginRedisTemplate");
                if (vUserLoginRedisTemplate instanceof RedisTemplate) {
                    redisTemplate = (RedisTemplate) vUserLoginRedisTemplate;
                }
            }catch (Exception e)
            {}
        }
        return redisTemplate;
    }

    @Override
    public void add(String pUserName, int pSeconds) throws APPErrorException {
        if (StringUtil.isNullOrEmpty(pUserName)) {
            return;
        }

        String vCacheName = CACHE_KEY_USER_LOGIN_ERROR + "_" + pUserName;
        Optional.ofNullable(getUserLoginRedisTemplate()).ifPresent((vRedisTemplate) -> {
            vRedisTemplate.opsForValue().increment(vCacheName);
            vRedisTemplate.expire(vCacheName, pSeconds, TimeUnit.SECONDS);
        });
    }

    @Override
    public Integer get(String pUserName) throws APPErrorException {
        RedisTemplate vRedisTemplate = getUserLoginRedisTemplate();
        if (null == vRedisTemplate) {
            return null;
        }

        Object vErrorTimes = vRedisTemplate.opsForValue().get(CACHE_KEY_USER_LOGIN_ERROR + "_" + pUserName);
        if (null != vErrorTimes && (vErrorTimes instanceof Integer)) {
            return (Integer) vErrorTimes;
        }
        return null;
    }
}
