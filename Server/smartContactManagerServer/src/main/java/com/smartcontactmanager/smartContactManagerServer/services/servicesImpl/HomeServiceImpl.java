package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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


    public User registerUser(User user){
    try {
        if(userHelper.isvalidEmail(user.getEmail())){
          if(!userRepo.findByEmail(user.getEmail()).isEmpty()){
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
            throw new DuplicateUserException();
      }
       catch (Exception e) {
        System.out.println("ERROR: "+e.getMessage());
        return null;
      }
    }
    

}
