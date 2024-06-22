package com.dsa.binarybeats.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsa.binarybeats.Entity.Books;
import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.CartItem;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.CartItemException;
import com.dsa.binarybeats.Exceptions.UserException;
import com.dsa.binarybeats.Interface.ICartItemService;
import com.dsa.binarybeats.Repository.CartItemRepo;
import com.dsa.binarybeats.Repository.CartRepo;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserService userService;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setDays(cartItem.getDays());
        cartItem.setPrice(cartItem.getBook().getPrice()*cartItem.getDays());
        cartItem.setDiscountedPrice(cartItem.getBook().getDiscountedPrice()*cartItem.getDays());
        CartItem created = cartItemRepo.save(cartItem);
        return created;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());
        if(user.getId().equals(userId))
        {
            item.setDays(cartItem.getDays());
            item.setPrice(item.getDays()*item.getBook().getPrice());
            item.setDiscountedPrice(item.getBook().getDiscountedPrice()*item.getDays());
        }
        
        return cartItemRepo.save(item);
    }

    @Override
    public CartItem isExist(Cart cart, Books book, int days, Long userId) {
        CartItem cartItem = cartItemRepo.isCartItemExist(cart, book, days, userId);
        return cartItem;
    }

    @Override
    public void RemoveCartItem(Long userid, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userid);
        Cart cart = cartRepo.findCartByUserId(userid);
        if(user.getId().equals(reqUser.getId()))
        {
            /*cart.setTotalDiscountedPrice(cart.getTotalDiscountedPrice()-cartItem.getDiscountedPrice());
            cart.setTotalPrice(cart.getTotalPrice()-cartItem.getPrice());
            cart.setTotalItem(cart.getTotalItem()-1);
            cart.setDiscount(cart.getDiscount()-cartItem.getDiscount());*/
            cartItemRepo.deleteById(cartItemId);
        }
        else{
            throw new UserException("You cant Remove other users item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemid) throws CartItemException {
        Optional<CartItem> opt = cartItemRepo.findById(cartItemid);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new CartItemException("Item not found");
    }

}
