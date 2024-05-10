package com.dsa.binarybeats.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.dsa.binarybeats.Entity.User;


public interface UserRepo extends JpaRepository<User,Long> {

    public User findByEmail(String email);

    @Query("SELECT u FROM User u")
    public List<User> get_all_users();

    
}
