package com.example.SmartContactManager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/test")
    public String test(){
        return "admin/test";
    }
}
