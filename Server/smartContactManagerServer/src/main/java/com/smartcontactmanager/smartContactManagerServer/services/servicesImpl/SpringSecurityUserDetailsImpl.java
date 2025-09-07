package com.smartcontactmanager.smartContactManagerServer.services.servicesImpl;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smartcontactmanager.smartContactManagerServer.entities.User;

public class SpringSecurityUserDetailsImpl implements UserDetails {

    private User user;
    public SpringSecurityUserDetailsImpl(User user){
        this.user=user;
    }
   
    public String getName(){
        return user.getName();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // roles I will add later....
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
        
    }

    @Override
    public String getUsername() {
        return user.getEmail();
        
    }  

     @Override
    public String toString() {
        return "SpringSecurityUserDetailsImpl [user=" + user + "]";
    }
}
