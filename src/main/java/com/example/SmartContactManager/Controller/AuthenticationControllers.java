package com.example.SmartContactManager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class AuthenticationControllers {

     @RequestMapping("/signin")
    public String login(){
        return "signin";
    }

    @RequestMapping("/signout")
    public String logoutPage() {
        return "signout";
    }
    
    
    
}
