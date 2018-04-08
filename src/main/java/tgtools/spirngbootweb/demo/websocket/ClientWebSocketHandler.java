package tgtools.spirngbootweb.demo.websocket;

import org.apache.shiro.mgt.SecurityManager;
import org.springframework.stereotype.Controller;
import tgtools.web.develop.service.UserService;
import tgtools.web.develop.websocket.AbstractSingleWebSocketHandler;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 17:17
 */
@Controller
public class ClientWebSocketHandler extends AbstractSingleWebSocketHandler {


    @Override
    protected String getCommandType() {
        return "websocket";
    }

    @Override
    protected SecurityManager getSecurityManager() {
        return tgtools.web.platform.Platform.getBeanFactory().getBean(SecurityManager.class);
    }

    @Override
    protected UserService getUserService() {
        return tgtools.web.platform.Platform.getBeanFactory().getBean(UserService.class);
    }

    @Override
    public String getRest() {
        return "rest";
    }

    @Override
    public String getUrl() {
        return "/websocket";
    }



}
