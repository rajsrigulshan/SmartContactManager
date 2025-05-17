package com.smartcontactmanager.smartContactManagerServer.services;

import org.springframework.stereotype.Service;

import com.smartcontactmanager.smartContactManagerServer.entities.User;


@Service
public interface UserService {
    public User registerUser(User user);


}
