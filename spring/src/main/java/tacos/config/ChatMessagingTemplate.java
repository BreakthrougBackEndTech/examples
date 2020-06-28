package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import tacos.TestService;

/**
 * 仅仅为了解决循环依赖
 *
 * @author: zhegong
 **/
@Service
public class ChatMessagingTemplate {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void convertAndSend(String destination, Object msg){
        simpMessagingTemplate.convertAndSend(destination,msg);
    }

    /**
     * @description:
     * @author: zhegong
     * @create: 2020-04-16 13:49
     **/
    @Configuration
    public static class ServiceConfiguration {

        @Bean
        public TestService testFMService(){
            TestService testService = new TestService("FM");

            return testService;
        }

        @Bean
        public TestService testPMService(){
            TestService testService = new TestService("PM");

            return testService;
        }
    }
}
