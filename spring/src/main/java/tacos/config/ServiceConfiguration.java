package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tacos.TestService;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-16 13:49
 **/
@Configuration
public class ServiceConfiguration {

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

