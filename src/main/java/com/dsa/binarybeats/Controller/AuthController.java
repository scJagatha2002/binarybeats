package com.dsa.binarybeats.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsa.binarybeats.Entity.Cart;
import com.dsa.binarybeats.Entity.User;
import com.dsa.binarybeats.Exceptions.UserException;
import com.dsa.binarybeats.JWT.JWTProvider;
import com.dsa.binarybeats.Repository.UserRepo;
import com.dsa.binarybeats.Request.LoginRequest;
import com.dsa.binarybeats.Response.AuthResponse;
import com.dsa.binarybeats.Service.CartService;
import com.dsa.binarybeats.Service.CustomUserService;





@RestController
@RequestMapping("/auth")

@CrossOrigin(origins = "http://localhost:5500")
public class AuthController {

    private UserRepo userrepo;
    private JWTProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserService customUserService;

    @Autowired
    private CartService cartService;
    

    public AuthController(UserRepo u,JWTProvider j,PasswordEncoder p,CustomUserService c)
    {
        this.userrepo=u;
        this.jwtProvider=j;
        this.passwordEncoder=p;
        this.customUserService=c;
        
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User u) throws UserException{

        String email=u.getEmail();
        String password=u.getPassword();
        String firstname=u.getFirstName();
        String lastname=u.getLastName();
        Long mobile = u.getPhoneNo();

        User isEmailExist=userrepo.findByEmail(email);
        

        if(isEmailExist!=null)
        {
            throw new UserException("Email is already used");
        }

        User createdUser=new  User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstname);
        createdUser.setLastName(lastname);
        createdUser.setPhoneNo(mobile);

        User saveduser=userrepo.save(createdUser);
        Cart cart = cartService.CreateCart(saveduser);
        

        Authentication authentication=new UsernamePasswordAuthenticationToken(saveduser.getEmail(),saveduser.getPassword());    //Creates an authentication token
        SecurityContextHolder.getContext().setAuthentication(authentication);                                                   //Sets it for authentication token for authentication
        String token=jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignUp Successful");
        
        
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>LoginHandler(@RequestBody LoginRequest loginRequest) throws UserException {
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Successful");
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }


    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customUserService.loadUserByUsername(username);
        if(userDetails==null)
        {
            throw new BadCredentialsException("Invalid UserName");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}