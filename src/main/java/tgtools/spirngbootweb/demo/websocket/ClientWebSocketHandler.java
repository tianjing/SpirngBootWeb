package tgtools.spirngbootweb.demo.websocket;

import org.springframework.stereotype.Controller;
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
    public String getRest() {
        return "rest";
    }

    @Override
    public String getUrl() {
        return "/websocket";
    }



}
