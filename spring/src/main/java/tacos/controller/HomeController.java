package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.TestService;

import java.beans.ConstructorProperties;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-10 14:38
 **/
@Controller
public class HomeController {

    @Autowired
    TestService testFMService;

    @Autowired
    TestService testPMService;

    @Autowired
    TestService testService;


    @GetMapping("/")
    public String home() {

        System.out.println(testFMService.value);
        System.out.println(testPMService.value);

        System.out.println(testService.value);
        return "index";
    }
}

