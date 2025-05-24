package com.smartcontactmanager.smartContactManagerServer.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartcontactmanager.smartContactManagerServer.entities.User;
import com.smartcontactmanager.smartContactManagerServer.repository.UserRepo;

@Service
public class SpringSecurityUserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // UserDetails userDetails=new UserDetails();
        User user=userRepo.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found.");
        }
        
        return new SpringSecurityUserDetailsImpl(user);

        }
        
    
}
