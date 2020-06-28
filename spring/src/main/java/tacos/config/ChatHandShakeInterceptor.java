package tacos.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author: zhegong
 **/
@Component
public class ChatHandShakeInterceptor implements HandshakeInterceptor {

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false.
     * 通过attributes参数设置WebSocketSession的属性
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // http协议转换websoket协议进行前，可以在这里通过session信息判断用户登录是否合法
        String lessonId = ((ServletServerHttpRequest) request).getServletRequest().getParameter("lessonId");
        String token =((ServletServerHttpRequest) request).getServletRequest().getParameter("token");

    /*    if (StringUtils.isNotBlank(lessonId) || StringUtils.isNotBlank(token)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }*/
        attributes.put("user", "Anonymity");
        attributes.put("lessonId", lessonId);

        //TODO
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        //握手成功后,
    }

}

