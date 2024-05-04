package com.example.SmartContactManager.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SmartContactManager.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{


    @Query("select u from User u where u.email =:email ")
    public User getUserByEmail(@Param("email") String email);
    
    @Modifying
    @Query("Update User u set u.name = :name,u.email = :email,u.about = :about where u.id = :id")
    public void updateUserData(Long id,String name,String email,String about);

    @Modifying
    @Query("Update User u set u.imageUrl = :imageUrl where u.id = :id")
    public void updateUSerImage(Long id,String imageUrl);
    
}

    

