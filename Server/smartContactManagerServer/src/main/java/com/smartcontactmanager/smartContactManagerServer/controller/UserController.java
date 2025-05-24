package com.smartcontactmanager.smartContactManagerServer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {


    @GetMapping("/users")
    public String getUsersDetails(){
        return "Users fetch successfully...";
    }
    
}
