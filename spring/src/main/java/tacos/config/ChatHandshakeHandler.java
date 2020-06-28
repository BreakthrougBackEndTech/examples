package tacos.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * @author: zhegong
 **/
@Component
public class ChatHandshakeHandler extends DefaultHandshakeHandler {
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        //设置认证用户
        return ()->String.valueOf(attributes.get("user"));
    }

}
