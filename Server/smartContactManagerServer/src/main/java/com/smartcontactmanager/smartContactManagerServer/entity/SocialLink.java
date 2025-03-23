package com.smartcontactmanager.smartContactManagerServer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity(name="socialLink")
@Table(name="social_links")
public class SocialLink {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long slId;
    @Column(length = 1000)
    private String link;
    private String linkType;
    @ManyToOne
    private Contact contact;

    public SocialLink(){

    }


    public Long getSlId() {
        return slId;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getLinkType() {
        return linkType;
    }
    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }


    @Override
    public String toString() {
        return "SocialLink [slId=" + slId + ", link=" + link + ", linkType=" + linkType + "]";
    }


    

    

    
}
