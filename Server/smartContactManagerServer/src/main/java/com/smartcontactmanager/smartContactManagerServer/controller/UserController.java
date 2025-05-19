package com.smartcontactmanager.smartContactManagerServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/users")
    public String getUsersDetails(){
        return "Users fetch successfully...";
    }
    
}
