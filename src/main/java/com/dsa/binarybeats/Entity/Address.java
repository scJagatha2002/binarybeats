package com.dsa.binarybeats.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private String phoneNo;

    private String address;

    private String city;

    private String state;

    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
}
