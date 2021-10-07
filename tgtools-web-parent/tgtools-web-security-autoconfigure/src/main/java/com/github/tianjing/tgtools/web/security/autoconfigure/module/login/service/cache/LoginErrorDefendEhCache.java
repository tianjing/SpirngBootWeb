package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import tgtools.exceptions.APPErrorException;

/**
 * @author 田径
 * @date 2020-08-26 11:17
 * @desc
 **/
public class LoginErrorDefendEhCache implements LoginErrorDefendCache {

    protected Ehcache cache;

    public LoginErrorDefendEhCache() {
    }

    public LoginErrorDefendEhCache(Ehcache pEhcache) {
        cache = pEhcache;
    }

    @Override
    public void add(String pUserName, int pSeconds) throws APPErrorException {
        Integer vErrorTimes = get(pUserName);
        Element vElement = new Element(pUserName, Integer.valueOf(1), pSeconds);

        if (null != vErrorTimes) {
            vElement = new Element(pUserName, vErrorTimes + 1, pSeconds, pSeconds);
        }

        cache.put(vElement);
    }

    @Override
    public Integer get(String pUserName) throws APPErrorException {
        Element vElement = cache.get(pUserName);
        if (null == vElement) {
            return null;
        }

        Object vErrorTimes = vElement.getObjectValue();
        if (null != vErrorTimes && vErrorTimes instanceof Integer) {
            return (Integer) vErrorTimes;
        }
        return null;
    }
}
