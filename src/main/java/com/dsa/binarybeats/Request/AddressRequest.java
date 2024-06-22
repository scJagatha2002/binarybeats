package com.dsa.binarybeats.Request;

import lombok.Data;

@Data
public class AddressRequest {

    private String fullName;

    private String email;

    private String phoneNo;

    private String address;

    private String city;

    private String state;

    private String pinCode;
    
}
