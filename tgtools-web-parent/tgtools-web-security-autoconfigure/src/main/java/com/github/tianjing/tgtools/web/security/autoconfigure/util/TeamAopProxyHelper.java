package com.github.tianjing.tgtools.web.security.autoconfigure.util;

import org.springframework.aop.framework.AopContext;

/**
 * @author 田径
 * @date 2019-12-31 10:59
 * @desc
 **/
public class TeamAopProxyHelper {
    public static Object getCurrentProxy() {
        return AopContext.currentProxy();
    }
}

