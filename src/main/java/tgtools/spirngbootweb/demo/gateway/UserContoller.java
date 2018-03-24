package tgtools.spirngbootweb.demo.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tgtools.spirngbootweb.demo.mybatis.db1.MyUserDao;
import tgtools.spirngbootweb.demo.mybatis.db2.UserDao;

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
    public void list(HttpServletResponse response)
    {
        try {
            mDUserDao.selectAll();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("田径");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
