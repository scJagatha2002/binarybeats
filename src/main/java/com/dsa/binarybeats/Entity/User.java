package com.dsa.binarybeats.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private long phoneNo;

    private String email;

    private boolean subscribed;

    private String password;

    @OneToOne
    private SubscriptionDetails subscription;

    
    
}
