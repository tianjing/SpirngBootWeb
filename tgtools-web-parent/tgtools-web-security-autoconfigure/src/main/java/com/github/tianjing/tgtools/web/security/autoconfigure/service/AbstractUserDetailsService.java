package com.github.tianjing.tgtools.web.security.autoconfigure.service;

import com.github.tianjing.tgtools.encrypt.spring.security.DelegatingEncrypter;
import com.github.tianjing.tgtools.web.security.autoconfigure.bean.AbstractSysUser;
import com.github.tianjing.tgtools.web.security.autoconfigure.bean.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tgtools.exceptions.APPErrorException;
import tgtools.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田径
 * @date 2019-07-11 14:44
 * @desc
 **/
public abstract class AbstractUserDetailsService implements UserDetailsAndPasswordService {

    protected String appId;
    protected PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

    /**
     * 获取用户对象
     *
     * @param pUserName
     * @return
     * @throws APPErrorException
     */
    public abstract SysUser getUser(String pUserName) throws APPErrorException;

    /**
     * 获取用户角色
     *
     * @param pUser
     * @return
     * @throws APPErrorException
     */
    public abstract String[] getRole(SysUser pUser) throws APPErrorException;

    /**
     * 获取用户权限
     *
     * @param pUser
     * @return
     * @throws APPErrorException
     */
    public abstract List<String> getUserAuthority(SysUser pUser) throws APPErrorException;

    public List<SimpleGrantedAuthority> getAuthority(SysUser pUser) throws APPErrorException {
        ArrayList<SimpleGrantedAuthority> vResult = new ArrayList<>();
        List<String> vMenuPerms = getUserAuthority(pUser);
        if (null == vMenuPerms || vMenuPerms.size() < 1) {
            return new ArrayList<SimpleGrantedAuthority>();
        }

        vMenuPerms.forEach((vMenuPerm) -> {
            if (StringUtil.isNotEmpty(vMenuPerm)) {
                vResult.add(new SimpleGrantedAuthority(vMenuPerm));
            }
        });

        return vResult;
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
    public UserDetails loadUserByUsername(String pUserName) throws UsernameNotFoundException {
        try {
            SysUser vSysUserDO = getUser(pUserName);
            String vEncodeId = null;
            if ((encoder instanceof DelegatingEncrypter) && StringUtil.isNotEmpty(vSysUserDO.getPwd())) {
                vEncodeId = ((DelegatingEncrypter) encoder).extractId(vSysUserDO.getPwd());
            }

            UserDetails vUser = User.builder().username(vSysUserDO.getFirst()).password(StringUtil.isNotEmpty(vEncodeId) ? vSysUserDO.getPwd() : encoder.encode(vSysUserDO.getPwd())).authorities(getAllRoleAndAuthorize(vSysUserDO)).build();
            return User.withUserDetails(vUser).build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户登录信息处理失败；原因：" + e.toString());
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    protected List<GrantedAuthority> getAllRoleAndAuthorize(SysUser pSysUserDO) throws APPErrorException {
        UserDetails vUserRole = User.builder().username(pSysUserDO.getFirst()).password(pSysUserDO.getPwd()).roles(getRole(pSysUserDO)).build();
        UserDetails vUserAuthorize = User.builder().username(pSysUserDO.getFirst()).password(pSysUserDO.getPwd()).authorities(getAuthority(pSysUserDO)).build();
        List<GrantedAuthority> vAuthorizes = new ArrayList<>();
        vAuthorizes.addAll(vUserRole.getAuthorities());
        vAuthorizes.addAll(vUserAuthorize.getAuthorities());
        return vAuthorizes;

    }
}
