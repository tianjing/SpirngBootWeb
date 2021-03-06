package tgtools.spirngbootweb.demo.websocket.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONObject;
import tgtools.spirngbootweb.demo.service.UserServiceImpl;
import tgtools.spirngbootweb.demo.websocket.ClientWebSocketHandler;
import tgtools.web.develop.command.Command;
import tgtools.web.develop.message.NotifyMessage;

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
    @Autowired
    ClientWebSocketHandler mWebSocketHandler;
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

        NotifyMessage message=new NotifyMessage();
        message.setSender("SYSTEM");
        message.setType("hello");
        message.setReceiver("");
        JSONObject json1 =new JSONObject();
        json1.put(mSysUserService.getUser().getUser().getUserName()," say:"+json.getString("say"));
        message.setContent(json1);
        mWebSocketHandler.sendNotifyMessage(mSysUserService.getUser().getUser().getUserName(),message);


        return true;
    }
}
