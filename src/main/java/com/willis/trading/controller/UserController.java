package com.willis.trading.controller;

import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.Users;
import com.willis.trading.service.EmailService;
import com.willis.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<Users> getUserProfile(
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @PostMapping("/api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<Users> sendVerificationOtp(
            @RequestHeader("Authorization") String jwt,
            @PathVariable VerificationType verificationType) throws Exception {

        Users user=userService.findUserProfileByJwt(jwt);


        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<Users> enableTwoFactorAuthentication(
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

}
