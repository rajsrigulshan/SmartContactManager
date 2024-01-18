package com.example.SmartContactManager.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.example.SmartContactManager.Entity.User;
import com.example.SmartContactManager.Repository.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;





@Controller
public class UserService {
      
    @Autowired
    private UserRepo userRepo;
    

    @PostMapping("/addUser")
    @ResponseBody
    public  User addUser(@RequestBody User user) {

    System.out.println(user);
    userRepo.save(user);

     return user;
    }


    @GetMapping("/users/{Id}")
    @ResponseBody
    public Optional<User> getAllContacts(@PathVariable("Id") Long Id) {
        Optional<User> contacts=userRepo.findById(Id);
        return  contacts;
    }
    
    

}
