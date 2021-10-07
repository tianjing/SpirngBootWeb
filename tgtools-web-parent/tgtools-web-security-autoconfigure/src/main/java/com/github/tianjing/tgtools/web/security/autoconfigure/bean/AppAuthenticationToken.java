package com.github.tianjing.tgtools.web.security.autoconfigure.bean;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 田径
 * @date 2019-07-05 14:50
 * @desc
 **/
public class AppAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AppAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public AppAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
