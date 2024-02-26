package com.example.SmartContactManager.Configuration;

import com.example.SmartContactManager.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.SmartContactManager.Repository.UserRepo;

@Component
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    

    //Here we can only able to inplemrnt
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        
        User user=userRepo.getUserByEmail(username);
        System.out.println("USER name is "+username);
        if(user == null)
        throw new UnsupportedOperationException("user name not found.....");

        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        return customUserDetails;
    }



  
    
}
