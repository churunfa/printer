package cn.edu.printer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewMap {

    @RequestMapping("*")
    String index() {
        return "userView";
    }

    @RequestMapping("/admin")
    String admin() {
        return "adminView";
    }

    @RequestMapping("/admin/*")
    String admin2() {
        return "adminView";
    }
}
