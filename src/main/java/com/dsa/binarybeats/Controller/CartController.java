package com.dsa.binarybeats.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.BookException;
import com.dsa.binarybeats.Exceptions.CartException;
import com.dsa.binarybeats.Exceptions.UserException;
import com.dsa.binarybeats.Request.AddItemRequest;
import com.dsa.binarybeats.Response.ApiResponse;
import com.dsa.binarybeats.Service.CartService;
import com.dsa.binarybeats.Service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;


    //find cart
    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart (@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartService.FindUserCard(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    //Add item to cart
    @PostMapping("/add/{userId}")
    public ResponseEntity<ApiResponse> addCart(@PathVariable Long userId,@RequestBody AddItemRequest req) throws BookException , UserException, CartException{
        cartService.AddCart(userId, req);
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);

    }
    
}
