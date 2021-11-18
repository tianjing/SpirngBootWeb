package com.github.tianjing.test.db.webapp.controller;

import com.github.tianjing.test.db.webapp.mybatis.db1.MyUserDao;
import com.github.tianjing.test.db.webapp.mybatis.db2.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 14:25
 */
@Controller
@RequestMapping("/user")
public class UserContoller {
    @Autowired
    MyUserDao mDUserDao;

    @Autowired
    UserDao mUserDao;


    @RequestMapping("/list")
    public void list(HttpServletResponse response) {
        try {
            mDUserDao.selectAll();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("田径");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
