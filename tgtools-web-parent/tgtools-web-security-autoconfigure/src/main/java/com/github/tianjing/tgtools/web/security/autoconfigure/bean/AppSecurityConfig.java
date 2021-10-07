package com.github.tianjing.tgtools.web.security.autoconfigure.bean;

import org.springframework.beans.factory.annotation.Value;
import tgtools.util.StringUtil;

/**
 * @author 田径
 * @date 2019-11-29 14:22
 * @desc
 **/
public class AppSecurityConfig {
    public static final String PATH_RESOURCE =
            "/admin/login.html,"
                    + "/admin/js/**,"
                    + "/admin/css/**,"
                    + "/admin/api/user/login,"
                    + "/css/**,"
                    + "/file/**,"
                    + "/health/**,"
                    + "/myrest/**,"
                    + "/webjars/**,"
                    + "/client/**,"
                    + "/guest/**,"
                    + "/third/**,"
                    + "/image/**,"
                    + "/user/login,"
                    + "/logout,"
                    + "/js/**,"
                    + "/css/**,"
                    + "/test/**";
    protected static final String PATH_SPLIT_MARK = ",";
    @Value("${app.resource-path:" + PATH_RESOURCE + "}")
    protected String resourcePath;

    @Value("${app.resource-path-ext:/demo/**}")
    protected String resourcePathExt;

    @Value("${app.main-page:/index.html}")
    protected String mainPage;

    @Value("${app.login-page:/login.html}")
    protected String loginPage;

    @Value("${app.app-details-service-class:}")
    protected String appDetailsServiceClass;

    @Value("${app.user-details-service-class:}")
    protected String userDetailsServiceClass;

    @Value("${app.filter-user-login-class:}")
    protected String filterUserLoginClass;


    @Value("${app.use-auto-login:false}")
    protected boolean useAutoLogin;

    @Value("${app.security-auto-login-use:false}")
    protected boolean useSecurityAutoLogin;

    @Value("${app.security-auth-userid:false}")
    protected boolean useSecurityAuthUserId;

    @Value("${app.auth-userid:false}")
    protected boolean useAuthUserId;


    @Value("${app.security-auto-login-url:/security/autologin}")
    protected String securityAutoLoginUrl;


    @Value("${app.security-auto-ui-login.enable:false}")
    protected boolean useSecurityUIAutoLogin;

    @Value("${app.security-auto-ui-login.url:/security/ui/login}")
    protected String securityUIAutoLoginUrl;
    @Value("${app.security-auto-ui-login.encrypt-type:}")
    protected String securityUIAutoLoginEncryptType;


    @Value("${app.use-csrf-security:false}")
    protected boolean useCsrfSecurity;

    /**
     * 启用 csrf Referer 方式
     * 通过 header Referer 方式 验证
     */
    @Value("${app.use-csrf-referer:false}")
    protected boolean useCsrfReferer;


    @Value("${app.use-xss-valid:false}")
    protected boolean useXssValid;

    @Value("${app.use-xss-valid-allpath:false}")
    protected boolean useXssValidAllPath;

    @Value("${app.frameOptions:sameOrigin}")
    protected String frameOptions;

    @Value("${app.encrypter.id:sm4c}")
    protected String encrypterId;

    @Value("${app.encrypter.key:@tianjing123*}")
    protected String encrypterKey;


    protected String[] resourcePaths;

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePathExt() {
        return resourcePathExt;
    }

    public void setResourcePathExt(String resourcePathExt) {
        this.resourcePathExt = resourcePathExt;
    }

    public String getMainPage() {
        return mainPage;
    }

    public void setMainPage(String mainPage) {
        this.mainPage = mainPage;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public boolean getUseAutoLogin() {
        return useAutoLogin;
    }

    public void setUseAutoLogin(boolean useAutoLogin) {
        this.useAutoLogin = useAutoLogin;
    }

    public boolean getUseCsrfSecurity() {
        return useCsrfSecurity;
    }

    public void setUseCsrfSecurity(boolean useCsrfSecurity) {
        this.useCsrfSecurity = useCsrfSecurity;
    }

    public boolean getUseCsrfReferer() {
        return useCsrfReferer;
    }

    public void setUseCsrfReferer(boolean useCsrfReferer) {
        this.useCsrfReferer = useCsrfReferer;
    }

    public boolean getUseXssValid() {
        return useXssValid;
    }

    public void setUseXssValid(boolean useXssValid) {
        this.useXssValid = useXssValid;
    }

    public String getFrameOptions() {
        return frameOptions;
    }

    public void setFrameOptions(String frameOptions) {
        this.frameOptions = frameOptions;
    }

    public String getEncrypterKey() {
        if (StringUtil.isNullOrEmpty(encrypterKey)) {
            return "@tianjing123$";
        }
        return encrypterKey;
    }

    public void setEncrypterKey(String encrypterKey) {
        this.encrypterKey = encrypterKey;
    }

    public boolean getUseSecurityAutoLogin() {
        return useSecurityAutoLogin;
    }

    public void setUseSecurityAutoLogin(boolean useSecurityAutoLogin) {
        this.useSecurityAutoLogin = useSecurityAutoLogin;
    }

    public String getEncrypterId() {
        return encrypterId;
    }

    public void setEncrypterId(String pEncrypterId) {
        encrypterId = pEncrypterId;
    }

    public String getSecurityAutoLoginUrl() {
        return securityAutoLoginUrl;
    }

    public void setSecurityAutoLoginUrl(String securityAutoLoginUrl) {
        this.securityAutoLoginUrl = securityAutoLoginUrl;
    }


    public boolean getUseSecurityUIAutoLogin() {
        return useSecurityUIAutoLogin;
    }

    public void setUseSecurityUIAutoLogin(boolean useSecurityUIAutoLogin) {
        this.useSecurityUIAutoLogin = useSecurityUIAutoLogin;
    }

    public String getSecurityUIAutoLoginUrl() {
        return securityUIAutoLoginUrl;
    }

    public void setSecurityUIAutoLoginUrl(String securityUIAutoLoginUrl) {
        this.securityUIAutoLoginUrl = securityUIAutoLoginUrl;
    }

    public String getSecurityUIAutoLoginEncryptType() {
        return securityUIAutoLoginEncryptType;
    }

    public void setSecurityUIAutoLoginEncryptType(String securityUIAutoLoginEncryptType) {
        this.securityUIAutoLoginEncryptType = securityUIAutoLoginEncryptType;
    }

    public String getAppDetailsServiceClass() {
        return appDetailsServiceClass;
    }

    public void setAppDetailsServiceClass(String appDetailsServiceClass) {
        this.appDetailsServiceClass = appDetailsServiceClass;
    }

    public String getUserDetailsServiceClass() {
        return userDetailsServiceClass;
    }

    public void setUserDetailsServiceClass(String userDetailsServiceClass) {
        this.userDetailsServiceClass = userDetailsServiceClass;
    }

    public boolean getUseXssValidAllPath() {
        return useXssValidAllPath;
    }

    public void setUseXssValidAllPath(boolean useXssValidAllPath) {
        this.useXssValidAllPath = useXssValidAllPath;
    }

    public String getFilterUserLoginClass() {
        return filterUserLoginClass;
    }

    public void setFilterUserLoginClass(String pFilterUserLoginClass) {
        filterUserLoginClass = pFilterUserLoginClass;
    }

    public String[] parseResourcePath() {
        if (null == resourcePaths) {
            String vPath = getResourcePath() + PATH_SPLIT_MARK + getResourcePathExt();
            vPath = StringUtil.replace(vPath, PATH_SPLIT_MARK + PATH_SPLIT_MARK, PATH_SPLIT_MARK);
            resourcePaths = vPath.indexOf(PATH_SPLIT_MARK) > 0 ? vPath.split(PATH_SPLIT_MARK) : new String[]{vPath};
        }
        return resourcePaths;
    }

    public boolean getUseSecurityAuthUserId() {
        return useSecurityAuthUserId;
    }

    public void setUseSecurityAuthUserId(boolean pUseSecurityAuthUserId) {
        useSecurityAuthUserId = pUseSecurityAuthUserId;
    }

    public boolean getUseAuthUserId() {
        return useAuthUserId;
    }

    public void setUseAuthUserId(boolean pUseAuthUserId) {
        useAuthUserId = pUseAuthUserId;
    }
}
