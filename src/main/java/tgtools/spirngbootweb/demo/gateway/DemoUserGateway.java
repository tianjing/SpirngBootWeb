package tgtools.spirngbootweb.demo.gateway;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONObject;
import tgtools.spirngbootweb.demo.mybatis.db1.MyUserDO;
import tgtools.spirngbootweb.demo.mybatis.db2.UserDO;
import tgtools.spirngbootweb.demo.service.UserServiceImpl;
import tgtools.web.develop.gateway.AbstractGateway;
import tgtools.web.develop.gateway.AbstractSingleGateway;
import tgtools.web.develop.message.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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