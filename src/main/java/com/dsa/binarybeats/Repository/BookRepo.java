package com.dsa.binarybeats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsa.binarybeats.Entity.Books;

public interface BookRepo extends JpaRepository<Books, Long> {
    
}
