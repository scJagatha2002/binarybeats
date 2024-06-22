package com.dsa.binarybeats.Interface;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.CartItem;
import com.dsa.binarybeats.Exceptions.CartItemException;
import com.dsa.binarybeats.Exceptions.UserException;

public interface ICartItemService  {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;

    public CartItem isExist(Cart cart,Books book,int days,Long userId);

    public void RemoveCartItem(Long userid,Long cartItemId) throws CartItemException,UserException;

    public CartItem findCartItemById(Long cartItemid) throws CartItemException;
    
}
