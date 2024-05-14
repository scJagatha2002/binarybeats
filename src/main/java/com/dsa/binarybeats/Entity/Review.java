package com.dsa.binarybeats.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String review;

    @ManyToOne
    @JoinColumn(name="book_id")
    @JsonIgnore
    private Books books;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
