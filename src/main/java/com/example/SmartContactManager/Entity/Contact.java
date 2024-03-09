package com.example.SmartContactManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_Id")
    private Long contactId;
    private String name;
    private String secondName;
    private String email;
    private String work;
    private String phone;
    private String image;
    @Column(length = 500000)
    private String description;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_user_id")
    private User user;




   
    public Long getContactId() {
        return contactId;
    }
    public String getName() {
        return name;
    }
    public String getSecondName() {
        return secondName;
    }
    public String getEmail() {
        return email;
    }
    public String getWork() {
        return work;
    }
    public String getPhone() {
        return phone;
    }
    public String getImage() {
        return image;
    }
    public String getDescription() {
        return description;
    }
    // public void setContactId(Long contactId) {
    //     this.contactId = contactId;
    // }
    public void setName(String name) {
        this.name = name;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setDescription(String description) {
        this.description = description;
    }
     public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Contact [contactId=" + contactId + ", name=" + name + ", secondName=" + secondName + ", email=" + email
                + ", work=" + work + ", phone=" + phone + ", image=" + image + ", description=" + description + "]";
    }


    



    



    



    
}
