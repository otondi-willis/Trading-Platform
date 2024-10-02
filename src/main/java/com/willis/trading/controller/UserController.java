package com.willis.trading.controller;

import com.willis.trading.model.Users;
import com.willis.trading.service.EmailService;
import com.willis.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<Users> getUserProfile(
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Users>(user, HttpStatus.OK)
    }

}
