package tgtools.spirngbootweb.common.config;

import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 常规WebSocket 启动方式
 * 现已被 TgtoolsConfig 替代
 * @author 田径
 * @Title
 * @Description
 * @date 9:04
 */
//@Configuration
//@EnableWebSocket
@Deprecated
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 此处与客户端的 URL 相对应
        //registry.addHandler(new MessageHandle(), "/websocket");
        System.out.println("111");
    }

}
