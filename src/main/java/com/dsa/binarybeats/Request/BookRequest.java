package com.dsa.binarybeats.Request;

import lombok.Data;

@Data
public class BookRequest {

    private String title;

    private int price;

    private int discountedPrice;

    private int discountedPercent;

    private int quantity;

    private String author;

    private String image_URL;

}
