package com.dsa.binarybeats.Request;

import lombok.Data;

@Data
public class AddItemRequest {
    private Long bookId;
    private int days;
    private Integer Price;
}
