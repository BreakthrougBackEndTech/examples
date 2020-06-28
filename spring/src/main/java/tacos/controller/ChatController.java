package tacos.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import tacos.data.ChatMessage;

import java.security.Principal;

/**
 * @author: zhegong
 **/
@Controller
public class ChatController {

    /**
     * 默认消息代理目的地为"/topic/chat/{lessonId}
     */
    @MessageMapping("/chat/{lessonId}")
    public ChatMessage chatInSameLesson(Principal principal, ChatMessage message) {

        return new ChatMessage(principal.getName() + ":" + HtmlUtils.htmlEscape(message.getContext()));
    }
}
