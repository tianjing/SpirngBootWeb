//package com.github.tianjing.example.empty.webapp.demo.shiro;
//
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
///**
// * 自定义 未登录时的处理
// * @author 田径
// * @Title
// * @Description
// * @date 8:55
// */
//public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse res =(HttpServletResponse)response;
//        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        res.setContentType("html/text;charset=utf-8");
//        PrintWriter write= res.getWriter();
//        write.write("没有权限");
//        write.close();
//        return false;
//    }
//
//}
