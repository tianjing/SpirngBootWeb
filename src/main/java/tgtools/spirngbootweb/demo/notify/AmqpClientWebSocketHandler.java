package tgtools.spirngbootweb.demo.notify;

import org.apache.shiro.mgt.SecurityManager;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Controller;
import tgtools.notify.rabbitmq.websocket.AbstractClientWebSocketHandler;
import tgtools.spirngbootweb.demo.service.UserServiceImpl;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 17:17
 */
@Controller
public class AmqpClientWebSocketHandler extends AbstractClientWebSocketHandler {


    @Override
    public RabbitAdmin getRabbitAdmin() {
        return (RabbitAdmin)tgtools.web.platform.Platform.getBeanFactory().getBean("rabbitAdmin");
    }

    public AmqpClientWebSocketHandler()
    {super();}


    @Override
    protected String getCommandType() {
        return "amqp";
    }

    @Override
    protected SecurityManager getSecurityManager() {
        return tgtools.web.platform.Platform.getBeanFactory().getBean(SecurityManager.class);
    }

    @Override
    protected UserServiceImpl getUserService() {
        return tgtools.web.platform.Platform.getBeanFactory().getBean(tgtools.spirngbootweb.demo.service.UserServiceImpl.class);
    }

    @Override
    public String getServletName() {
        return "rest";
    }

    @Override
    public String getUrl() {
        return "/amqptest";
    }


}
