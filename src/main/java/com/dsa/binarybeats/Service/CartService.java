package com.dsa.binarybeats.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.CartItem;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.CartException;
import com.dsa.binarybeats.Interface.IBookService;
import com.dsa.binarybeats.Interface.ICartService;
import com.dsa.binarybeats.Repository.CartRepo;
import com.dsa.binarybeats.Request.AddItemRequest;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepo cart_rep;

    @Autowired
    private IBookService bookService;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public Cart CreateCart(User u) {
        Cart cart=new Cart();
        cart.setUser(u);
        return cart_rep.save(cart);
    }

    @Override
    public String AddCart(Long userId, AddItemRequest req) throws CartException {
        Cart caart=cart_rep.findCartByUserId(userId);
        Books book=bookService.getBook2(req.getBookId());
        CartItem isPresent=cartItemService.isExist(caart, book,req.getDays(), userId);
        if(isPresent==null)
        {
            CartItem cartItem=new CartItem();
            cartItem.setBook(book);
            cartItem.setCart(caart);
            cartItem.setDays(req.getDays());
            cartItem.setUserId(userId);

            int price=req.getDays()*book.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setDays(req.getDays());

            CartItem createdCartItem=cartItemService.createCartItem(cartItem);
            caart.setTotalDiscountedPrice(caart.getTotalDiscountedPrice()+createdCartItem.getDiscountedPrice());
            caart.setTotalItem(caart.getTotalItem()+1);
            caart.setTotalPrice(caart.getTotalPrice()+createdCartItem.getPrice());
            caart.getCartItems().add(createdCartItem);
            cart_rep.save(caart);
            
        }
        return "Item added to cart";
    }

    @Override
    public Cart FindUserCard(Long userId) {
        Cart cart=cart_rep.findCartByUserId(userId);
        int totalPrice=0;
        int totalDiscountPrice=0;
        int totalItem=0;
       

        for(CartItem cartItem:cart.getCartItems()){
            totalPrice+=cartItem.getPrice();
            totalDiscountPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getDays();
            
        }

        cart.setTotalDiscountedPrice(totalDiscountPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
       
        

        return cart_rep.save(cart);
    }
    
}
