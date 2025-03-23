package com.smartcontactmanager.smartContactManagerServer.entity;

import java.util.ArrayList;
import java.util.List;

import com.smartcontactmanager.smartContactManagerServer.enums.Providers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity(name = "user")
@Table(name = "users")
public class User {
    @Id
    private String userId;
    @Column(name = "user_name" ,nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Column(length = 1000)
    private String description;
    @Column(length = 1000)
    private String profileLink;
    private String phoneNumber;
    private String isEnabled;
    private String isEmailVerified;
    private String isPhoneVerified;
    private Providers provider=Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts=new ArrayList<>();

    public User() {
    }


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProfileLink() {
        return profileLink;
    }
    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }
    public String getIsEmailVerified() {
        return isEmailVerified;
    }
    public void setIsEmailVerified(String isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }
    public String getIsPhoneVerified() {
        return isPhoneVerified;
    }
    public void setIsPhoneVerified(String isPhoneVerified) {
        this.isPhoneVerified = isPhoneVerified;
    }
    public Providers getProvider() {
        return provider;
    }
    public void setProvider(Providers provider) {
        this.provider = provider;
    }
    public String getProviderUserId() {
        return providerUserId;
    }
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
    
    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password 
                + ", description=" + description + ", profileLink=" + profileLink + ", phoneNumber=" + phoneNumber
                + ", isEnabled=" + isEnabled + ", isEmailVerified=" + isEmailVerified + ", isPhoneVerified="
                + isPhoneVerified + ", provider=" + provider + ", providerUserId=" + providerUserId + "]";
    }    
    
}
