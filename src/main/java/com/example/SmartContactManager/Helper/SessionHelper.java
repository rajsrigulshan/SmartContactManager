package com.example.SmartContactManager.Helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

   public void removeSessionAttribute() {
    System.out.println("removeSession triggered................");
    HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    session.removeAttribute("message");
   
}
 
    
}
