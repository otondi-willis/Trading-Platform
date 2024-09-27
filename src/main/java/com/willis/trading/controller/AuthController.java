package com.willis.trading.controller;

import com.willis.trading.model.Users;
import com.willis.trading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Users> register(@RequestBody Users users) throws Exception {


        Users isEmailExist=userRepository.findByEmail(users.getEmail());

        if(isEmailExist!=null){
            throw new Exception("email already used with another account");

        }
        Users newUsers = new Users();
        newUsers.setEmail(users.getEmail());
        newUsers.setFullName(users.getFullName());
        newUsers.setPassword(users.getPassword());


        Users savedUsers = userRepository.save(newUsers);;
        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);

        

    }
}
