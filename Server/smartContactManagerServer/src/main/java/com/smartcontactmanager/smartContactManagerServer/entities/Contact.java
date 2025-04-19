package com.smartcontactmanager.smartContactManagerServer.entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name="contact")
@Table(name="contacts")
public class Contact {
    @Id
    private String contactId;
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    @Column(length = 1000)
    private String profilPhotoLink;
    @Column(length = 1000)
    private String description;
    private boolean isFavourite=false;
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<SocialLink> socialLinks=new ArrayList<>();


    public Contact() {
    }


    public String getContactId() {
        return contactId;
    }
    public void setContactId(String contactId) {
        this.contactId = contactId;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getProfilPhotoLink() {
        return profilPhotoLink;
    }
    public void setProfilPhotoLink(String profilPhotoLink) {
        this.profilPhotoLink = profilPhotoLink;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isFavourite() {
        return isFavourite;
    }
    public void setFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }


    public List<SocialLink> getSocialLinks() {
        return socialLinks;
    }
    public void setSocialLinks(List<SocialLink> socialLinks) {
        this.socialLinks = socialLinks;
    }
    public User getUser() {
        return user;
    }
    @Override
    public String toString() {
        return "Contact [contactId=" + contactId + ", name=" + name + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", address=" + address + ", profilPhotoLink=" + profilPhotoLink + ", description="
                + description +", isFavourite="
                + isFavourite + "]";
    }


    


    
}