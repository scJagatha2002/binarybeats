package com.dsa.binarybeats.Response;

import lombok.Data;

@Data
public class BookResponse {

    private long id;

    private String title;

    private int price;

    private String description;

    private int discountedPrice;

    private int discountedPercent;

    private int quantity;

    private String author;

    private String image_URL;
    
}
