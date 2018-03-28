package tgtools.spirngbootweb.demo.websocket.command;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONObject;
import tgtools.spirngbootweb.demo.service.UserServiceImpl;
import tgtools.web.develop.command.Command;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:35
 */
@Controller
public class HelloWordCommandImpl implements Command {
    @Autowired
    UserServiceImpl mSysUserService;

    @Override
    public String getType() {
        return "websocket";
    }

    @Override
    public String getName() {
        return "helloword";
    }

    @Override
    public Object excute(Object... params) throws APPErrorException {
        JSONObject json =(JSONObject)params[0];

        System.out.println(mSysUserService.getUser().getUser().getUserName()+" say:"+json.getString("say"));
        return true;
    }
}
