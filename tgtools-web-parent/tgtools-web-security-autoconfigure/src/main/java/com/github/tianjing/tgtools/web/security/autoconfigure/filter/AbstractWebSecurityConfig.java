package com.github.tianjing.tgtools.web.security.autoconfigure.filter;


import com.github.tianjing.tgtools.encrypt.spring.security.DelegatingEncrypter;
import com.github.tianjing.tgtools.web.security.autoconfigure.bean.AppSecurityConfig;
import com.github.tianjing.tgtools.web.security.autoconfigure.service.AbstractUserDetailsService;
import com.github.tianjing.tgtools.web.security.autoconfigure.service.UserDetailsAndPasswordService;
import com.github.tianjing.tgtools.web.security.autoconfigure.util.TeamDevelopSecurityHelper;
import com.github.tianjing.tgtools.web.security.autoconfigure.util.TeamReflectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

import javax.servlet.ServletContext;

/**
 * @author 田径
 * @date 2019-07-04 16:19
 * @desc
 **/
@ConditionalOnClass(AppSecurityConfig.class)
public abstract class AbstractWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    protected AppSecurityConfig appConfig;

    @Autowired
    protected ServletContext servletContext;
    protected HttpSecurity httpSecurity;
    /**
     * 默认没有加密
     * spring 默认支持 加密 PasswordEncoderFactories.createDelegatingPasswordEncoder()
     */
    protected PasswordEncoder encoder;
    @Autowired
    private ApplicationContext applicationContext;


    public PasswordEncoder getEncoder() {
        if (null == encoder) {
            encoder = getDelegatingPasswordEncoder();
        }
        return encoder;
    }

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    protected void setFameOptions(HttpSecurity pHttp) throws Exception {
        switch (appConfig.getFrameOptions()) {
            case "none":
                pHttp.headers().frameOptions().disable();
                break;
            case "deny":
                pHttp.headers().frameOptions().deny();
            default:
                pHttp.headers().frameOptions().sameOrigin();
                break;
        }
    }

    @Override
    protected void configure(HttpSecurity pHttp) throws Exception {
        httpSecurity = pHttp;

        if (appConfig.getUseCsrfSecurity()) {
            pHttp.csrf().ignoringAntMatchers(appConfig.parseResourcePath());
        } else {
            pHttp.csrf().disable();
        }

        setFameOptions(pHttp);

        pHttp.authorizeRequests()
                // 以下不用登录就能访问
                .antMatchers(
                        appConfig.parseResourcePath()
                ).permitAll()
                // 其他请求必须登录才能访问
                .anyRequest().authenticated();

        pHttp.formLogin()
                .successForwardUrl(appConfig.getMainPage());
        if (StringUtil.isNotEmpty(appConfig.getLoginPage())) {
            pHttp.formLogin().loginPage(appConfig.getLoginPage());
        }

        // 开启注销功能
        pHttp.logout().and().rememberMe();
        addFilter(pHttp);
    }

    protected abstract void addFilter(HttpSecurity pHttp);

    public DelegatingPasswordEncoder getDelegatingPasswordEncoder() {
        DelegatingEncrypter vDelegatingPasswordEncoder = TeamDevelopSecurityHelper.getDelegatingEncrypter(appConfig.getEncrypterId(), appConfig.getEncrypterKey());
        return vDelegatingPasswordEncoder;
    }

    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        return getDelegatingPasswordEncoder();
    }

    public abstract UserDetailsAndPasswordService getUserDetailsService();


    /**
     * PasswordEncoderFactories.createDelegatingPasswordEncoder()
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService()).passwordEncoder(
                org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()
        );
    }


    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * 通过反射 创建 UserDetailsService
     *
     * @param pClassName
     * @param paramsClass
     * @param params
     * @return
     */
    protected AbstractUserDetailsService createUserDetailsService(String pClassName, Class[] paramsClass, Object[] params) {
        Object vObject = null;
        try {
            vObject = TeamReflectionHelper.createObject(pClassName, paramsClass, params);
            if (vObject instanceof AbstractUserDetailsService) {
                return (AbstractUserDetailsService) vObject;
            }
            return null;
        } catch (APPErrorException e) {
            tgtools.util.LogHelper.info(this.getClass().getName(), "创建错误", "createUserDetailsService");
            return null;
        }

    }

}
