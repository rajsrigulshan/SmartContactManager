package com.smartcontactmanager.smartContactManagerServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.exceptions.DuplicateUserException;
import com.smartcontactmanager.smartContactManagerServer.helper.ApiResponse;
import com.smartcontactmanager.smartContactManagerServer.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> userSignup(@RequestBody User user) {
        try {

            User createdUser = userService.registerUser(user);
            System.out.println("createdUser: " + createdUser);
            if (createdUser == null) {
                return new ResponseEntity<>(ApiResponse.<String>builder()
                        .success(false)
                        .message("Email already exists.")
                        .build(), HttpStatus.valueOf(400));
            }
            return new ResponseEntity<>(ApiResponse.<String>builder()
                        .success(true)
                        .message("Email already exists.")
                        .build(), HttpStatus.CREATED);

        } catch (DuplicateUserException dUser) {

            return new ResponseEntity<>(ApiResponse.<String>builder()
                        .success(false)
                        .message("email already exists.")
                        .build(), HttpStatus.valueOf(409));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(ApiResponse.<String>builder()
                        .success(false)
                        .message("Email already exists.")
                        .build(), HttpStatus.valueOf(500));
        }

    }
}
