package com.dsa.binarybeats.Request;

import java.util.List;

import lombok.Data;

@Data
public class QuizRequest {

    private String question;

    private List<OptionRequest> options;

    private String topic;
}
