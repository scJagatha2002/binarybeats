package com.dsa.binarybeats.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsa.binarybeats.Entity.Address;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.UserException;
import com.dsa.binarybeats.Interface.IUserService;
import com.dsa.binarybeats.JWT.JWTProvider;
import com.dsa.binarybeats.Repository.AddressRepo;
import com.dsa.binarybeats.Repository.UserRepo;
import com.dsa.binarybeats.Request.AddressRequest;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo user_repo;

    @Autowired
    private JWTProvider jwt_provider;

    @Autowired
    private AddressRepo addressRepo; 

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = user_repo.findById(userId);
        if (user.get() == null) {
            throw new UserException("User not found");
        } else {
            return user.get();
        }
    }

    @Override
    public User findUserByJwt(String Jwt) throws UserException {
        String email = jwt_provider.getEmail(Jwt);
        User user = user_repo.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found");
        } else {
            return user;
        }
    }

    @Override
    public List<User> getAllUsers() throws UserException {
        return user_repo.get_all_users();
    }

    @Override
    public User AlreadyExist(String Email) throws UserException {
        return user_repo.findByEmail(Email);
    }

    @Override
    public String addAddress(Long userId, AddressRequest addressReq) throws UserException {

        Optional<User> isUser = user_repo.findById(userId);
        if(isUser.isEmpty()){
            throw new UserException("User not found");
        }
        User u = isUser.get();
        Address address = new Address();
        address.setAddress(addressReq.getAddress());
        address.setCity(addressReq.getCity());
        address.setEmail(addressReq.getEmail());
        address.setFullName(address.getFullName());
        address.setPhoneNo(addressReq.getPhoneNo());
        address.setPinCode(addressReq.getPinCode());
        address.setState(addressReq.getState());
        address.setUser(u);
        addressRepo.save(address);

        u.getAddress().add(address);
        user_repo.save(u);
        return "Address Saved Successfully";
    }
    
}