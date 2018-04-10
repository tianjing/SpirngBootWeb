package tgtools.spirngbootweb.demo.notify.command;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tgtools.exceptions.APPErrorException;
import tgtools.json.JSONObject;
import tgtools.notify.rabbitmq.service.RabbitMqService;
import tgtools.spirngbootweb.demo.service.UserServiceImpl;
import tgtools.web.develop.command.Command;
import tgtools.web.develop.message.NotifyMessage;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 10:35
 */
@Component
public class AmqpNotifyCommandImpl implements Command {

    @Autowired
    UserServiceImpl mSysUserService;
    @Autowired
    RabbitMqService mRabbitMqService;

    @Override
    public String getType() {
        return "amqp";
    }

    @Override
    public String getName() {
        return "userNotify";
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
        message.setContent(json1.toString());
        mRabbitMqService.sendToUserMessage("admin",tgtools.util.JsonParseHelper.parseToJson(message,false));
        return true;
    }
}
