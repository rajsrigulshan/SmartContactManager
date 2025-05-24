package com.smartcontactmanager.smartContactManagerServer.services;

import org.springframework.stereotype.Service;

import com.smartcontactmanager.smartContactManagerServer.entities.User;


@Service
public interface HomeService {
    public User registerUser(User user);
    public String doLogin(User user);


}
