package com.github.tianjing.tgtools.web.security.autoconfigure.util;


import com.github.tianjing.tgtools.encrypt.spring.security.DelegatingEncrypter;
import com.github.tianjing.tgtools.encrypt.spring.security.PasswordEncoderFactory;
import com.github.tianjing.tgtools.web.security.autoconfigure.bean.AbstractSysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 田径
 * @date 2019-09-25 11:26
 * @desc
 **/
public class TeamDevelopSecurityHelper {
    public static void setCurrentAuthentication(Authentication pAuthentication) {
        SecurityContextHolder.getContext().setAuthentication(pAuthentication);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static AbstractSysUser getCurrentUser() {
        return getDetails();
    }

    /**
     * 获取 spring security 中 当前用户 UserDetails 信息
     * 就是 认证时 AuthenticationToken 的 setDetails
     *
     * @param <T>
     * @return
     */
    public static <T> T getDetails() {
        if (null == SecurityContextHolder.getContext().getAuthentication().getDetails()) {
            return null;
        }

        if (SecurityContextHolder.getContext().getAuthentication().getDetails() instanceof WebAuthenticationDetails) {
            return null;
        }


        try {
            return (T) SecurityContextHolder.getContext().getAuthentication().getDetails();
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * 通常 存放 用户名
     * AuthenticationToken 或 UsernamePasswordAuthenticationToken Principal:loginName;Credentials:password;
     *
     * @return
     */
    public static <T> T getPrincipal() {
        if (null == SecurityContextHolder.getContext().getAuthentication().getPrincipal()) {
            return null;
        }

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof WebAuthenticationDetails) {
            return null;
        }

        try {
            return (T) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public static AuthenticationManager getAuthenticationManager() {
        return tgtools.web.platform.Platform.getBeanFactory().getBean(AuthenticationManager.class);
    }

    public static void userLogin(String pLoginName, String pPassword) {
        AbstractSysUser vSysUserDO = new AbstractSysUser();
        vSysUserDO.setFirst(pLoginName);
        vSysUserDO.setPwd(pPassword);
        userLogin(vSysUserDO);
    }

    /**
     * 使用当前认证管理器对用户信息进行认证
     * 只是认证 并没有保存用户信息到 Context和Session
     *
     * @param pSysUser
     */
    public static void userLogin(AbstractSysUser pSysUser) {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(pSysUser.getFirst(), pSysUser.getPwd());
        authRequest.setDetails(pSysUser);
        getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取当前线程的 Request 对象
     * 注意 获取的应该是 spring 的 Request wapper 不是 web 的 Request
     *
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes vServletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != vServletRequestAttributes) {
            return vServletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * 获取当前线程的 Response 对象
     * 注意 获取的应该是 spring 的 Response wapper 不是 web 的 Response
     *
     * @return
     */
    public static HttpServletResponse getCurrentResponse() {
        ServletRequestAttributes vServletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != vServletRequestAttributes) {
            return vServletRequestAttributes.getResponse();
        }
        return null;
    }

    /**
     * 通过Session 获取 用户
     *
     * @param <T>
     * @return
     */
    public static <T> T getCurrentUserBySession() {
        ServletRequestAttributes vServletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest vRequest = getCurrentRequest();
        if (null != vRequest) {
            HttpSession vSession = vRequest.getSession(false);
            if (null != vSession) {
                Object vContext = vSession.getAttribute("SPRING_SECURITY_CONTEXT");
                if (null != vContext && vContext instanceof SecurityContext) {
                    ((SecurityContext) vContext).getAuthentication().getDetails();
                }
            }
        }
        return null;
    }

    public static void saveCurrentUserToSession() {
        ServletRequestAttributes vServletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != vServletRequestAttributes) {
            HttpServletRequest vRequest = vServletRequestAttributes.getRequest();
            if (null != vServletRequestAttributes) {
                saveCurrentUserToSession(vRequest);
            }
        }
    }


    /**
     * 将当前用户保存到 session 中
     *
     * @param pRequest
     */
    public static void saveCurrentUserToSession(HttpServletRequest pRequest) {
        saveCurrentUserToSession(pRequest, SecurityContextHolder.getContext());
    }


    /**
     * 将当前用户保存到 session 中
     *
     * @param pRequest
     */
    public static void saveCurrentUserToSession(HttpServletRequest pRequest, SecurityContext pSecurityContext) {
        HttpSession session = pRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", pSecurityContext);
    }


    public static DelegatingEncrypter getDelegatingEncrypter(String pDefaultEncodeId, String pPassword) {
        Map<String, PasswordEncoder> vPasswordEncoders = new HashMap<>(20);
        vPasswordEncoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        //hash加密器
        vPasswordEncoders.put("md5", PasswordEncoderFactory.newMd5Encrypter());
        vPasswordEncoders.put("MD5", vPasswordEncoders.get("md5"));

        vPasswordEncoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        vPasswordEncoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
        vPasswordEncoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
        vPasswordEncoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        vPasswordEncoders.put("scrypt", new SCryptPasswordEncoder());

        //对称加密器
        vPasswordEncoders.put("des", PasswordEncoderFactory.newDesNoModeNoPaddingEncrypter());
        vPasswordEncoders.put("desa", PasswordEncoderFactory.newDesSaltNoModeNoPaddingEncrypter());
        vPasswordEncoders.put("sm4c", PasswordEncoderFactory.newSm4CbcEncrypter());
        vPasswordEncoders.put("sm4e", PasswordEncoderFactory.newSm4EcbEncrypter());

        DelegatingEncrypter vDelegatingPasswordEncoder = new DelegatingEncrypter(pDefaultEncodeId, vPasswordEncoders);
        vDelegatingPasswordEncoder.setKey(pPassword);

        return vDelegatingPasswordEncoder;
    }

}
