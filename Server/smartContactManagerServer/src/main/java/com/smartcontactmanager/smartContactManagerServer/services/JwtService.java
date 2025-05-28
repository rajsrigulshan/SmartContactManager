package com.smartcontactmanager.smartContactManagerServer.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
   public String generateToken(String username);
   public String extractUserName(String token);
   public boolean validateToken(String token, UserDetails userDetails);
}
