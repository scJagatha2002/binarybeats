package com.dsa.binarybeats.Response;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    
}
