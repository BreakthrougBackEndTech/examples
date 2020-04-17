package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @description:
 * @author: zhegong
 * @create: 2020-04-17 15:45
 **/
@RestController
@Slf4j
public class AsyncTestController {

    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public String sync() throws InterruptedException {
        Thread.sleep(1000);
        return "sync";
    }

    @RequestMapping("/async")
    public Callable<String> callable() {
        // 使用异步将不会阻塞tomcat的io读写线程池、使得增加系统的吞吐量
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("call async method");
                Thread.sleep(1000);
                return "hello";
            }
        };
    }
}

