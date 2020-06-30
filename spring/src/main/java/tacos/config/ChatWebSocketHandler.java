package tacos.config;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
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
    private SimpMessagingTemplate simpMessagingTemplate;

    public static final Multimap<String, WebSocketSession> sessionMultimap = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    @Override
    public WebSocketHandler decorate(final WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                Object lessonId = session.getAttributes().get("lessonId");
                if (lessonId != null) {
                    sessionMultimap.put(lessonId.toString(), session);

                    ChatMessage chatMessage = new ChatMessage(session.getAttributes().get("user") + " 进入了直播间",
                            sessionMultimap.get(lessonId.toString()).size());


                    new Thread(() -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        simpMessagingTemplate.convertAndSend("/topic/chat/" + lessonId, chatMessage);
                    }).start();


//                    session.sendMessage(new TextMessage(JSONUtil.toJsonStr(chatMessage)));
                }

                //On-line related operations
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
                    throws Exception {
                Object lessonId = session.getAttributes().get("lessonId");

                if (lessonId != null) {
                    sessionMultimap.remove(lessonId.toString(), session);
                    simpMessagingTemplate.convertAndSend("/topic/chat/" + lessonId, new ChatMessage(session.getAttributes().get("user") + " 离开了直播间",
                            sessionMultimap.get(lessonId.toString()).size()));
                }

                //Offline related operations
                super.afterConnectionClosed(session, closeStatus);
            }
        };
    }

    @Autowired
    @Lazy
    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }
}
