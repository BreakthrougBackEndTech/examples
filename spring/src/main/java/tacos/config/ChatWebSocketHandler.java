package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import tacos.data.ChatMessage;

/**
 * @author: zhegong
 **/
@Component
public class ChatWebSocketHandler implements WebSocketHandlerDecoratorFactory {
    @Autowired
    private ChatMessagingTemplate chatMessagingTemplate;

    @Override
    public WebSocketHandler decorate(final WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                Object lessonId = session.getAttributes().get("lessonId");
                if (lessonId != null)
                    chatMessagingTemplate.convertAndSend("/topic/chat/" + lessonId, new ChatMessage(session.getAttributes().get("user") + " 进入了直播间"));

                //On-line related operations
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
                    throws Exception {
                Object lessonId = session.getAttributes().get("lessonId");

                if (lessonId != null)
                    chatMessagingTemplate.convertAndSend("/topic/chat/" + lessonId, new ChatMessage(session.getAttributes().get("user") + " 离开了直播间"));

                //Offline related operations
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }
}
