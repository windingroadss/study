package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String test() {
        System.out.println("/index");
        return "index";
    }

    @RequestMapping(value = "/test")
    public String test2() {
        System.out.println("/test");
        return "test";
    }

    @RequestMapping(value = "/center1")
    public String center1() {
        System.out.println("/center1");
        return "center1";
    }

    @RequestMapping(value = "/center2")
    public String center2() {
        System.out.println("/center2");
        return "center2";
    }
}
