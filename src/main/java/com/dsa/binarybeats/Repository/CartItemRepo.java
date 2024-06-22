package com.dsa.binarybeats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem,Long> {

    @Query("SELECT ci FROM CartItem ci Where ci.cart=:cart And ci.book=:book And ci.days=:days And ci.userId=:userId")
    public CartItem isCartItemExist(@Param("cart")Cart cart,@Param("book")Books book,@Param("days")int days,@Param("userId")Long userId);
    
}
