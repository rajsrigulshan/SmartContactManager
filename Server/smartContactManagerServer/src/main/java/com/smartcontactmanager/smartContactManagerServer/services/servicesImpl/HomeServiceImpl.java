package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.smartcontactmanager.smartContactManagerServer.dto.UserLoginResponseDTO;
import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.exceptions.DuplicateUserException;
import com.smartcontactmanager.smartContactManagerServer.helper.UserHelper;
import com.smartcontactmanager.smartContactManagerServer.repository.UserRepo;
import com.smartcontactmanager.smartContactManagerServer.services.HomeService;
import com.smartcontactmanager.smartContactManagerServer.services.JwtService;

@Component
public class HomeServiceImpl implements HomeService{
     @Autowired
    private UserHelper userHelper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired(required = false)
    private JwtService jwtService;
    
    @Override
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
    public UserLoginResponseDTO doLogin(User user) {
      Authentication authentication=  authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
      if(authentication!=null && authentication.isAuthenticated()){
        System.out.println("details:"+authentication.getPrincipal()+"-----"+authentication.getName());
         String token= jwtService.generateToken(user.getEmail());
        //  SpringSecurityUserDetailsImpl userDetails=(SpringSecurityUserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SpringSecurityUserDetailsImpl userDetails=(SpringSecurityUserDetailsImpl)authentication.getPrincipal();
         UserLoginResponseDTO userLoginResponseDTO=new UserLoginResponseDTO();
         userLoginResponseDTO.setToken(token);
         userLoginResponseDTO.setUserName(userDetails.getUsername());
         userLoginResponseDTO.setName(userDetails.getName());
         return userLoginResponseDTO;
      }
        return null;
    }
    

}
