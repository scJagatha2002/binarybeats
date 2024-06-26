package com.dsa.binarybeats.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL,orphanRemoval = true)
    @Column(name="cart_items")
    private List<CartItem> cartItems=new ArrayList<>();

    @Column(name="total_price")
    private double totalPrice;

    @Column(name="total_item")
    private int totalItem;

    private int totalDiscountedPrice;

    private int discount;
    
}
