package com.dsa.binarybeats.Response;

import lombok.Data;

@Data
public class CodeResponse {

    private String topic;
    private String description;
    private String difficulty_id;
    private String code;
    private String referenceLink;
    private String solutionLink;
    private String title;
    
}
