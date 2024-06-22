package com.dsa.binarybeats.Interface;

import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.CartException;
import com.dsa.binarybeats.Request.AddItemRequest;

public interface ICartService {
    public Cart CreateCart(User u);
    public String AddCart(Long userId,AddItemRequest req) throws CartException;
    public Cart FindUserCard(Long userId);
}
