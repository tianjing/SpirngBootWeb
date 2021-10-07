//package com.github.tianjing.example.empty.webapp.demo.websocket;
//
//import org.springframework.stereotype.Controller;
//import tgtools.web.develop.service.UserService;
//import tgtools.web.develop.websocket.AbstractSingleWebSocketHandler;
//
///**
// * @author 田径
// * @Title
// * @Description
// * @date 17:17
// */
//@Controller
//public class ClientWebSocketHandler extends AbstractSingleWebSocketHandler {
//
//
//    /**
//     * 命令类型 用于加载 同类型的 CommandImpl
//     * @return
//     */
//    @Override
//    protected String getCommandType() {
//        return "websocket";
//    }
//
//    /**
//     * 认证管理器 模拟登录用到
//     * @return
//     */
//    @Override
//    protected SecurityManager getSecurityManager() {
//        return tgtools.web.platform.Platform.getBeanFactory().getBean(SecurityManager.class);
//    }
//
//    /**
//     * 用户认证服务
//     * @return
//     */
//    @Override
//    protected UserService getUserService() {
//        return tgtools.web.platform.Platform.getBeanFactory().getBean(UserService.class);
//    }
//
//    /**
//     * 匹配 ServletName 参看 TgtoolsConfig 中 servletName 对应
//     * @return
//     */
//    @Override
//    public String getServletName() {
//        return "rest";
//    }
//
//    /**
//     * 自定义 websocket url（/{ContextPath}/{ServletUrl}/socket）
//     * @return
//     */
//    @Override
//    public String getUrl() {
//        return "/websocket";
//    }
//
//
//
//}
