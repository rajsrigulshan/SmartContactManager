package com.smartcontactmanager.smartContactManagerServer.services;

import org.springframework.stereotype.Service;

@Service
public interface JwtService {
   public String generateToken(String username);
}
