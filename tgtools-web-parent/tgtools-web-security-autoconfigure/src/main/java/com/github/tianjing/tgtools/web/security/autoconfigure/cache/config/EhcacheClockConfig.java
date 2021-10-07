package com.github.tianjing.tgtools.web.security.autoconfigure.cache.config;

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
public class EhcacheClockConfig {

    @Bean
    public CacheDatabaseClockImpl clock(@Qualifier("ehcacheManager") CacheManager pCacheManager) {
        initCache(pCacheManager);
        EhCacheCacheManager vCacheManage = new EhCacheCacheManager();
        vCacheManage.setCacheManager(pCacheManager);
        CacheDatabaseClockImpl vClock = new CacheDatabaseClockImpl();
        vClock.setIDataAccess(DataBaseFactory.getDefault());
        vClock.setCacheManager(vCacheManage);
        vClock.setCacheKey(GUID.newGUID());
        return vClock;
    }

    protected void initCache(CacheManager pCacheManager) {
        if (null == pCacheManager.getCache("TimerCache")) {
            pCacheManager.addCache(new Cache("TimerCache", 10000, false, false, 600, 900));
        }
    }
}
