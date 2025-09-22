package com.smartcontactmanager.smartContactManagerServer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcontactmanager.smartContactManagerServer.dto.UserLoginResponseDTO;
import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.exceptions.DuplicateUserException;
import com.smartcontactmanager.smartContactManagerServer.helper.ApiResponse;
import com.smartcontactmanager.smartContactManagerServer.services.HomeService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
public class Homecontroller {

    @Autowired
    private HomeService homeService;


    @GetMapping("test")
    public String testFunction(HttpServletRequest httpServlet ){
        
        return "Home controller fetched:   "+httpServlet.getSession().getId();
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> userSignup(@RequestBody User user) {
        try {

            User createdUser = homeService.registerUser(user);
            System.out.println("createdUser: " + createdUser);
            if (createdUser == null) {
                return new ResponseEntity<>(ApiResponse.<Void>builder()
                        .success(false)
                        .message("signup unsuccessful.")
                        .build(), HttpStatus.valueOf(400));
            }
            return new ResponseEntity<>(ApiResponse.<Void>builder()
                        .success(true)
                        .message("user created.")
                        .build(), HttpStatus.CREATED);

        } catch (DuplicateUserException dUser) {

            return new ResponseEntity<>(ApiResponse.<Void>builder()
                        .success(false)
                        .message("email already exists.")
                        .build(), HttpStatus.valueOf(409));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(ApiResponse.<Void>builder()
                        .success(false)
                        .message("something went wrong.")
                        .build(), HttpStatus.valueOf(500));
        }

    }

    //@raj: check for the necessacity of the user object or we can create loginObject. 
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponseDTO>> userLogin(@RequestBody User user){
            System.out.println("Login triggered....");
            UserLoginResponseDTO userLoginResponseDTO=homeService.doLogin(user);
            if(userLoginResponseDTO!=null){
                return new ResponseEntity<>(ApiResponse.<UserLoginResponseDTO>builder()
                                                             .success(true)
                                                             .message("validated")
                                                             .data(userLoginResponseDTO)
                                                             .build(),HttpStatus.valueOf(200));
            }
           return new ResponseEntity<>(ApiResponse.<UserLoginResponseDTO>builder()
                        .success(false)
                        .message("Invalid User")
                        .build(), HttpStatus.valueOf(401));
            
                                                         
    }
}
