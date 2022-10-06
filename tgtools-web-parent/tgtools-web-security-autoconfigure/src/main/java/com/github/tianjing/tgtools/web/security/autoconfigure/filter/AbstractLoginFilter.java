package com.github.tianjing.tgtools.web.security.autoconfigure.filter;

import com.github.tianjing.tgtools.web.security.autoconfigure.service.UserDetailsAndPasswordService;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 田径
 * @date 2019-09-25 9:57
 * @desc
 **/
public abstract class AbstractLoginFilter extends AbstractAuthenticationProcessingFilter {

    protected UserDetailsAndPasswordService userDetailsAndPasswordService;

    public AbstractLoginFilter(UserDetailsAndPasswordService pUserDetailsAndPasswordService, String pPath) {
        super(new AntPathRequestMatcher(pPath, null));
        userDetailsAndPasswordService = pUserDetailsAndPasswordService;
        this.setAuthenticationManager(performBuild());
    }

    protected ProviderManager performBuild() {
        DaoAuthenticationProvider vDaoAuthenticationProvider = new DaoAuthenticationProvider();
        vDaoAuthenticationProvider.setPasswordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        if (null != userDetailsAndPasswordService.getEncoder()) {
            vDaoAuthenticationProvider.setPasswordEncoder(userDetailsAndPasswordService.getEncoder());
        }
        vDaoAuthenticationProvider.setUserDetailsService(userDetailsAndPasswordService);
        ProviderManager providerManager = new ProviderManager(new ArrayList() {{
            add(vDaoAuthenticationProvider);
        }}, null);

        return providerManager;
    }

    /**
     * 重写改方法 可以避免 302 跳转
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);
        getRememberMeServices().loginSuccess(request, response, authResult);

        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                    authResult, this.getClass()));
        }

    }

}
