package com.dsa.binarybeats.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.UserException;
import com.dsa.binarybeats.Interface.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> UserProfile(@RequestHeader("Authorization") String jwt) throws UserException{
        User u = userService.findUserByJwt(jwt);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity <List<User>> getAllUsersHandler() throws UserException{
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity <User> getUserByEmailHandler(@PathVariable String email) throws UserException{
        User u = userService.AlreadyExist(email);
        if (u != null) {
            return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
        } else {
            // Return a 404 Not Found response when no user is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
