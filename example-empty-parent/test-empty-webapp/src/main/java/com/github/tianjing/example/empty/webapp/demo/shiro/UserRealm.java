//package com.github.tianjing.example.empty.webapp.demo.shiro;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//import tgtools.exceptions.APPErrorException;
//import tgtools.spirngbootweb.demo.bo.UserBO;
//import tgtools.spirngbootweb.demo.service.UserServiceImpl;
//
//import java.util.Set;
//
///**
// * @author 田径
// * @Title
// * @Description
// * @date 11:56
// */
//public class UserRealm extends AuthorizingRealm {
//
//    @Autowired
//    UserServiceImpl mUserServiceImpl;
//
//    public UserRealm() {
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //token验证
//        if (authenticationToken instanceof UsernameToken) {
//            try {
//                UsernameToken token = (UsernameToken) authenticationToken;
//                if (!mUserServiceImpl.validToken(token.getToken(), token.getUsername(), token.getHost())) {
//                    throw new AuthenticationException("token 过期");
//                }
//                UserBO user = mUserServiceImpl.getUserInfoByLoginName(token.getUsername());
//                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, token.getToken(), this.getName());
//                return authenticationInfo;
//            }catch (APPErrorException e)
//            {
//                throw new AuthenticationException("认证失败",e);
//            }
//            //普通用户密码验证
//        } else {
//            String loginName = (String) authenticationToken.getPrincipal();
//            String password = new String((char[]) ((char[]) authenticationToken.getCredentials()));
//            UserBO user = null;
//            try {
//                user = mUserServiceImpl.getUserInfoByLoginName(loginName);
//            } catch (APPErrorException e) {
//                throw new AuthenticationException("认证失败", e);
//            }
//            if (null == user) {
//                throw new UnknownAccountException("账号不存在!");
//            } else if (!password.equals(user.getUser().getPassword())) {
//                throw new IncorrectCredentialsException("密码错误!");
//            } else {
//                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, password, user.getUser().getUserName());
//                return authenticationInfo;
//            }
//        }
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        UserBO user = (UserBO) SecurityUtils.getSubject().getPrincipal();
//        Set<String> perms = user.getRole();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(perms);
//        return info;
//    }
//}
