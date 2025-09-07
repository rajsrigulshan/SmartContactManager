package com.smartcontactmanager.smartContactManagerServer.dto;


public class UserLoginResponseDTO {
    private String userName;
    private String name;
    private String token;

    

    public UserLoginResponseDTO() {
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
