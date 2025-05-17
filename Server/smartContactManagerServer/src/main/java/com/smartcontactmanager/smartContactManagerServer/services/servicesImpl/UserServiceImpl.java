package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.exceptions.DuplicateUserException;
import com.smartcontactmanager.smartContactManagerServer.helper.UserHelper;
import com.smartcontactmanager.smartContactManagerServer.repository.UserRepo;
import com.smartcontactmanager.smartContactManagerServer.services.UserService;

@Component
public class UserServiceImpl implements UserService{
     @Autowired
    private UserHelper userHelper;
    @Autowired
    private UserRepo userRepo;


    public User registerUser(User user){
    try {
        System.out.println("I am in for signup....");
        boolean val=userHelper.isvalidEmail(user.getEmail());
        if(userHelper.isvalidEmail(user.getEmail()) && val){
            System.out.println("validating Email........");
          if(!userRepo.findByEmail(user.getEmail()).isEmpty()){
                System.out.println("User Already exists");
                throw new DuplicateUserException();
          }
        }
        if(!userHelper.validateUserData(user)){
            return null;
          }
          user.setUserId(UUID.randomUUID().toString());
          User newUser=userRepo.save(user);
          return newUser;
      }catch(DuplicateUserException dUser){
            System.out.println("User already exists."); 
            throw new DuplicateUserException();
      }
       catch (Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return null;
      }
    }
    

}
