package com.github.tianjing.tgtools.web.security.autoconfigure.service;

import com.github.tianjing.tgtools.web.security.autoconfigure.bean.AbstractSysUser;
import com.github.tianjing.tgtools.web.security.autoconfigure.bean.SysUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tgtools.exceptions.APPErrorException;

/**
 * @author 田径
 * @date 2019-07-11 14:44
 * @desc
 **/
public abstract class AbstractAppDetailsService implements UserDetailsAndPasswordService {

    protected String appId;
    protected PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

    public abstract String getAppName(String pUserName) throws APPErrorException;

    public String[] getRole(SysUser pAppInfo) throws APPErrorException{
        return new String[]{"app"};
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String pAppId) {
        this.appId = pAppId;
    }
    @Override
    public PasswordEncoder getEncoder() {
        return encoder;
    }

    public void setEncoder(PasswordEncoder pEncoder) {
        this.encoder = pEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String pAppId) throws UsernameNotFoundException {
        try {
            String vAppName = getAppName(pAppId);
            AbstractSysUser vSysUser = new AbstractSysUser();
            vSysUser.setId(pAppId);
            vSysUser.setFirst(vAppName);
            vSysUser.setLast(vAppName);
            vSysUser.setPwd("1");


            UserDetails vUser = User.builder()
                    .username(vSysUser.getFirst())
                    .password(encoder.encode(vSysUser.getPwd()))
                    .roles(getRole(vSysUser))
                    .build();
            return User.withUserDetails(vUser).build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户登录信息处理失败；原因：" + e.toString());
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }


}
