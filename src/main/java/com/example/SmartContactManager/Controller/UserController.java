package com.example.SmartContactManager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
   
   
   @RequestMapping("/test") 
    public String testMethod(){
        
        System.out.println("I am in the test");
        
        return  "normal/test";
    }

}
