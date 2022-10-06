package com.github.tianjing.tgtools.web.security.autoconfigure.service;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author
 */
public interface UserDetailsAndPasswordService extends UserDetailsService, UserDetailsPasswordService {

    PasswordEncoder getEncoder();
}
