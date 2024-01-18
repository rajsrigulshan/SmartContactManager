package com.example.SmartContactManager.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SmartContactManager.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{




}

    

