package com.smartcontactmanager.smartContactManagerServer.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartcontactmanager.smartContactManagerServer.entities.User;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserService {
    

    @PostMapping("/signup" )
    public ResponseEntity<String> userSignup(@RequestBody User user){
        System.out.println(user.toString());
        System.out.println("-------------------------------");
       return new ResponseEntity<String>("Signup successful",HttpStatus.CREATED);
    }
}
