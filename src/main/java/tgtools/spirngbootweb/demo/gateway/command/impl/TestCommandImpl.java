package tgtools.spirngbootweb.demo.gateway.command.impl;

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
 * @date 16:03
 */
@Controller
public class TestCommandImpl implements Command {
    @Autowired
    UserServiceImpl mUserService;

    @Override
    public String getType() {
        return "rest";
    }

    @Override
    public String getName() {
        return "operation1";
    }

    @Override
    public Object excute(Object... params) throws APPErrorException {
        JSONObject json =(JSONObject)params[0];
        return mUserService.getUser().getUser().getUserName()+" say:"+json.getString("mess");
    }
}
