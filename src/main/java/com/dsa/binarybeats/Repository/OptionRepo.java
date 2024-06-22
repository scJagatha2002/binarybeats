package com.dsa.binarybeats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsa.binarybeats.Entity.Difficulty_level;
import com.dsa.binarybeats.Entity.Option;

public interface OptionRepo extends JpaRepository<Option,Long>  {
    
}
