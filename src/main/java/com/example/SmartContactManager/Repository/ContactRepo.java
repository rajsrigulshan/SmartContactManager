package com.example.SmartContactManager.Repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.SmartContactManager.Entity.Contact;
import com.example.SmartContactManager.Entity.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long>{

   @Query("from Contact c where c.user.id =:userId")
   public Page<Contact> getContactByUserId(@Param("userId")Long userId,Pageable pageable);

   public List<Contact> findByNameContainingAndUser(String name,User user);
    
}
