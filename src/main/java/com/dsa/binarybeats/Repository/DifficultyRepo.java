package com.dsa.binarybeats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dsa.binarybeats.Entity.Difficulty_level;

import java.util.Optional;


public interface DifficultyRepo extends JpaRepository<Difficulty_level,Long> {

    Optional<Difficulty_level> findById(long id);
    
}
