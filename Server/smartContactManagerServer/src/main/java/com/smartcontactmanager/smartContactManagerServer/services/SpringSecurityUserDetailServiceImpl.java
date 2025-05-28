package com.smartcontactmanager.smartContactManagerServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.repository.UserRepo;
import com.smartcontactmanager.smartContactManagerServer.services.servicesImpl.SpringSecurityUserDetailsImpl;

@Service
public class SpringSecurityUserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found.");
        }
        return new SpringSecurityUserDetailsImpl(user);
        }
        
    
}
