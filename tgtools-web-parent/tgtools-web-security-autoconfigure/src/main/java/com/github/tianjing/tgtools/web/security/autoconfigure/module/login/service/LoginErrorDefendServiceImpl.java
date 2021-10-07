package com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service;

import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache.LoginErrorDefendCache;
import com.github.tianjing.tgtools.web.security.autoconfigure.module.login.service.cache.LoginErrorDefendCacheFactory;
import tgtools.exceptions.APPErrorException;

/**
 * @author 田径
 * @date 2020-08-26 10:55
 * @desc
 **/
public class LoginErrorDefendServiceImpl implements LoginErrorDefendService {

    protected LoginErrorDefendCache loginErrorDefendCache;
    /**
     * 600秒10分钟
     */
    protected int lockSecond = 60;
    protected int errorTimes = 5;

    public LoginErrorDefendServiceImpl() {
        loginErrorDefendCache = LoginErrorDefendCacheFactory.createLoginErrorDefendCache();
    }

    public LoginErrorDefendServiceImpl(LoginErrorDefendCache pLoginErrorDefendCache) {
        loginErrorDefendCache = pLoginErrorDefendCache;
    }

    public LoginErrorDefendServiceImpl(LoginErrorDefendCache pLoginErrorDefendCache, int pLockSecond, int pErrorTimes) {
        loginErrorDefendCache = pLoginErrorDefendCache;
        lockSecond = pLockSecond;
        errorTimes = pErrorTimes;
    }

    @Override
    public void addUserErrorLogin(String pLoginName) throws APPErrorException {
        loginErrorDefendCache.add(pLoginName, lockSecond);
    }

    @Override
    public void validUserErrorLogin(String pLoginName) throws APPErrorException {
        Integer vErrorTimes = loginErrorDefendCache.get(pLoginName);
        if (null != vErrorTimes && errorTimes <= vErrorTimes.intValue()) {
            throw new APPErrorException("密码错误次数过多，请" + lockSecond + "秒后再试！");
        }
    }
}
