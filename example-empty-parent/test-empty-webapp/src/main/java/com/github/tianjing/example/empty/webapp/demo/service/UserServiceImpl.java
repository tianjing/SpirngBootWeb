//package com.github.tianjing.example.empty.webapp.demo.service;
//
//import com.github.tianjing.example.empty.webapp.demo.bo.UserBO;
//import com.github.tianjing.example.empty.webapp.demo.mybatis.db1.MyUserDao;
//import com.github.tianjing.example.empty.webapp.demo.shiro.UsernameToken;
//import org.springframework.stereotype.Service;
//import tgtools.exceptions.APPErrorException;
//import tgtools.spirngbootweb.demo.mybatis.db1.MyUserDO;
//
//import tgtools.util.DateUtil;
//import tgtools.web.develop.model.BaseModel;
//import tgtools.web.develop.service.AbstractService;
//import tgtools.web.develop.service.UserService;
//
//import java.util.HashSet;
//
///**
// * @author 田径
// * @Title
// * @Description
// * @date 11:54
// */
//@Service
//public class UserServiceImpl extends AbstractService<MyUserDao> implements UserService {
//
//
//    @Override
//    public boolean validLoginUser(String pUsername, String pPassword) throws APPErrorException {
//        UserBO user = validSysLoginUser(pUsername, pPassword);
//        return null != user;
//    }
//
//    @Override
//    public String createToken(String pUsername, String pAddr) throws APPErrorException {
//        String name = pUsername;
//        String date = DateUtil.formatCustemtime(DateUtil.getCurrentDate(), "yyyyMMdd");
//        return tgtools.util.EncrpytionUtil.str2MD5(name + pAddr + date);
//    }
//
//    @Override
//    public void tokenLogin(String pAddr, String pUsername, String pToken) throws APPErrorException {
//        UsernameToken upt = new UsernameToken();
//        upt.setUsername(pUsername);
//        upt.setPassword(pToken.toCharArray());
//        upt.setHost(pAddr);
//        SecurityUtils.getSubject().login(upt);
//    }
//
//    public boolean validToken(String pToken, String pUser, String pAddr) throws APPErrorException {
//        return pToken.equals(createToken(pUser, pAddr));
//    }
//
//    /**
//     * 验证用户登陆信息
//     *
//     * @param pUser
//     * @param pPassword
//     * @throws APPErrorException
//     */
//    public UserBO validSysLoginUser(String pUser, String pPassword) throws APPErrorException {
//        UserBO user = new UserBO();
//        MyUserDO u = new MyUserDO();
//        u.setId("1");
//        u.setRev(1L);
//        u.setUserName("admin");
//        u.setPassword("1");
//
//        user.setUser(u);
//        user.setRole(new HashSet<String>());
//        if (null == user) {
//            throw new APPErrorException("无效的用户");
//        }
//        if (!user.getUser().getPassword().equals(pPassword)) {
//            throw new APPErrorException("无效的密码");
//        }
//        return user;
//    }
//
//    public UserBO getUserInfoByLoginName(String pUserName) throws APPErrorException {
//        return validSysLoginUser(pUserName, "1");
//    }
//
//    public UserBO getUser() {
//        return (UserBO) SecurityUtils.getSubject().getPrincipal();
//    }
//
//    @Override
//    public BaseModel createModel() {
//        return new MyUserDO();
//    }
//}
