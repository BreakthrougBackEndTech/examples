package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: zhegong
 * @create: 2019-12-10 14:38
 **/
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}

