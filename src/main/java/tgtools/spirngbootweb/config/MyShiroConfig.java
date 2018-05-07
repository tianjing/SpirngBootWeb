package tgtools.spirngbootweb.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Configuration;
import tgtools.spirngbootweb.demo.bo.UserBO;
import tgtools.spirngbootweb.demo.shiro.UserRealm;
import tgtools.web.develop.config.ShiroConfig;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:24
 */
@Configuration
public class MyShiroConfig extends ShiroConfig {

    @Override
    public UserRealm userRealm(EhCacheManager cacheManager) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }


    @Override
    public  ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap();
//        filterChainDefinitionMap.put("/login.html", "anon");
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/image/**", "anon");
//        filterChainDefinitionMap.put("/scripts/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/user/login", "anon");
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/**", "authc");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //activiti 用户id自动注入
//        filterChainDefinitionMap.put("/**", "authc,MyFilter");
 //       shiroFilterFactoryBean.getFilters().put("MyFilter",new ActivitiFilter());
        return shiroFilterFactoryBean;
    }

    @Override
    public UserRealm getUserRealm() {
        return new UserRealm();
    }


    public static class ActivitiFilter implements Filter
    {


        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            filterChain.doFilter(servletRequest,servletResponse);
            if(null!=SecurityUtils.getSubject()&&null!=SecurityUtils.getSubject().getPrincipal()) {
                org.activiti.engine.impl.identity.Authentication.setAuthenticatedUserId(((UserBO) SecurityUtils.getSubject().getPrincipal()).getUser().getId());
            }
        }

        @Override
        public void destroy() {

        }
    }
}
