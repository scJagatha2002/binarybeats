package com.dsa.binarybeats.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.CartItem;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.CartItemException;
import com.dsa.binarybeats.Exceptions.UserException;
import com.dsa.binarybeats.Response.ApiResponse;
import com.dsa.binarybeats.Service.CartItemService;
import com.dsa.binarybeats.Service.UserService;


@RestController
@RequestMapping("/api/cartItem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private UserService userService;

     @PostMapping("/create")
    public ResponseEntity<CartItem> createCartItemHandler (@RequestBody CartItem cartitem){
        CartItem c = cartItemService.createCartItem(cartitem);
        return new ResponseEntity<>(c,HttpStatus.CREATED);
    }

    //update cart item
    @PutMapping("/update/{CartItemId}")
    public ResponseEntity<CartItem> updateCartItemHandler (@RequestHeader("Authorization") String jwt,@PathVariable("CartItemId") Long CartItemId,@RequestBody CartItem cartItem) throws UserException,CartItemException{
        User u = userService.findUserByJwt(jwt);
        CartItem c=cartItemService.updateCartItem(u.getId(), CartItemId, cartItem);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }


    //delete cart item
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> removeCartItem(@RequestHeader("Authorization") String jwt,@PathVariable Long cartItemId) throws UserException,CartItemException{
        User u = userService.findUserByJwt(jwt);
        cartItemService.RemoveCartItem(u.getId(), cartItemId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }


    //get cart item
    @GetMapping("/get/{cartItemId}")
    public ResponseEntity<CartItem> findbtId(@PathVariable("cartItemId")Long cartItemId) throws CartItemException
    {
        CartItem c = cartItemService.findCartItemById(cartItemId);
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
}
