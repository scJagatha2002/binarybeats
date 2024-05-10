package com.dsa.binarybeats.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/*@Entity
@Data
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String difficulty_level;

}*/

@Data
@Entity
public class Difficulty_level {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    private String difficulty_name;
    
}

