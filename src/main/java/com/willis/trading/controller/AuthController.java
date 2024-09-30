package com.willis.trading.controller;

import com.willis.trading.config.JwtProvider;
import com.willis.trading.model.Users;
import com.willis.trading.repository.UserRepository;
import com.willis.trading.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody Users users) throws Exception {


        Users isEmailExist=userRepository.findByEmail(users.getEmail());

        if(isEmailExist!=null){
            throw new Exception("email already used with another account");

        }
        Users newUsers = new Users();
        newUsers.setEmail(users.getEmail());
        newUsers.setFullName(users.getFullName());
        newUsers.setPassword(users.getPassword());


        Users savedUsers = userRepository.save(newUsers);

        Authentication auth=new UsernamePasswordAuthenticationToken(
                users.getEmail(),
                users.getPassword()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("register successful");


        return new ResponseEntity<>(res, HttpStatus.CREATED);

        

    }
}
