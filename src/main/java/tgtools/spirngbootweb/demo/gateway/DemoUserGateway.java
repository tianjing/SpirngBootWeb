package tgtools.spirngbootweb.demo.gateway;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tgtools.spirngbootweb.demo.mybatis.db1.MyUserDO;
import tgtools.spirngbootweb.demo.service.UserServiceImpl;
import tgtools.web.develop.gateway.AbstractGateway;
import tgtools.web.develop.gateway.AbstractSingleGateway;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:21
 */
@Controller
@RequestMapping("/demouser")
public class DemoUserGateway extends AbstractGateway<UserServiceImpl,MyUserDO> {


}