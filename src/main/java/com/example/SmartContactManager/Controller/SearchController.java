package com.example.SmartContactManager.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.SmartContactManager.Entity.Contact;
import com.example.SmartContactManager.Entity.User;
import com.example.SmartContactManager.Repository.ContactRepo;
import com.example.SmartContactManager.Repository.UserRepo;

@RestController
public class SearchController {
    
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;



    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal){

        System.out.println("QUERY: "+query);
        User user=userRepo.getUserByEmail(principal.getName());
        List<Contact> contacts=contactRepo.findByNameContainingAndUser(query, user);
        return ResponseEntity.ok(contacts);

    }
    
}
