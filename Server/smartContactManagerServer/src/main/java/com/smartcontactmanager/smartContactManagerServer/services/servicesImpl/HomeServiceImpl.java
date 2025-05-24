package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.exceptions.DuplicateUserException;
import com.smartcontactmanager.smartContactManagerServer.helper.UserHelper;
import com.smartcontactmanager.smartContactManagerServer.repository.UserRepo;
import com.smartcontactmanager.smartContactManagerServer.services.HomeService;

@Component
public class HomeServiceImpl implements HomeService{
     @Autowired
    private UserHelper userHelper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authManager;
      ;


    public User registerUser(User user){
    try {
        if(userHelper.isvalidEmail(user.getEmail())){
          User user2=userRepo.findByEmail(user.getEmail());
          if(user2!=null){
                throw new DuplicateUserException();
          }
        }
        if(!userHelper.validateUserData(user)){
            return null;
          }
          user.setUserId(UUID.randomUUID().toString());
          user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
          User newUser=userRepo.save(user);
          return newUser;
      }catch(DuplicateUserException dUser){
            throw new DuplicateUserException();
      }
       catch (Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return null;
      }
    }


    @Override
    public String doLogin(User user) {
      Authentication authentication=  authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
      if(authentication.isAuthenticated()){
        return "success";
      }
        return "failed";
    }
    

}
