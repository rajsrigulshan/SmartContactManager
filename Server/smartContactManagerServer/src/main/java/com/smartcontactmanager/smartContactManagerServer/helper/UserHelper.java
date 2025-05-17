package com.smartcontactmanager.smartContactManagerServer.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.smartcontactmanager.smartContactManagerServer.entities.User;



@Component
public class UserHelper {
    public boolean validateUserData(User user){
    

        if(user.getName().trim() == null ||user.getName().trim().length()<3){
            System.out.println("Invalid User name");
            return false;
        }
        else if(user.getPhoneNumber().trim()==null || user.getPhoneNumber().trim().length()<10 || user.getPhoneNumber().trim().length()>16){
            System.out.println("Invalid contact number");
            return false;
        }
        else if(user.getPassword().trim() ==null || user.getPassword().trim().length()<8){
            System.out.println("Invalid password");
            return false;
        }

        return true;
    }


    public boolean isvalidEmail(String email){
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();  
    }
}


