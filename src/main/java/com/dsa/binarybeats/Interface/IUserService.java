package com.dsa.binarybeats.Interface;

import java.util.List;

import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.UserException;

public interface IUserService {;
    
    public User findUserById(Long userId) throws UserException;
    public User findUserByJwt(String Jwt) throws UserException;
    public List<User> getAllUsers() throws UserException;
    public User AlreadyExist(String Email) throws UserException;
    
    
}