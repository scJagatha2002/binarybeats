package com.dsa.binarybeats.Request;

import lombok.Data;

@Data
public class CodeRequest {

    private String topic;
    private long difficulty_id;
    private String code;
    private String description;
    private String referenceLink;
    private String solutionLink;

}
