package com.github.tianjing.tgtools.web.security.autoconfigure.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.session.web.http.DefaultCookieSerializer;
import tgtools.util.StringUtil;

import javax.annotation.PostConstruct;

/**
 * @author 田径
 * @date 2019-07-12 20:15
 * @desc
 **/
public abstract class AbstractCustomHttpSession {

    @Value("${spring.application.name:PLATFORM}")
    protected String appName;
    @Value("${app.cookie.name:}")
    protected String cookieName;

    @Value("${app.session.cache.name:}")
    protected String sessionCacheName;


    @Autowired
    protected DefaultCookieSerializer defaultCookieSerializer;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }


    @PostConstruct
    public void init() {
        defaultCookieSerializer.setCookieName(getCookieName());
    }

    public String getCookieName() {
        if (StringUtil.isNullOrEmpty(cookieName)) {
            cookieName = appName + "SESSION";
        }
        return cookieName;
    }

    public String getSessionCacheName() {
        if (StringUtil.isNullOrEmpty(sessionCacheName)) {
            return getCookieName();
        }
        return sessionCacheName;
    }

}
