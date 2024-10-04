package com.willis.trading.controller;

import com.willis.trading.ForgotPasswordTokenRequest;
import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.ForgotPasswordToken;
import com.willis.trading.model.Users;
import com.willis.trading.model.VerificationCode;
import com.willis.trading.response.AuthResponse;
import com.willis.trading.service.EmailService;
import com.willis.trading.service.ForgotPasswordService;
import com.willis.trading.service.UserService;
import com.willis.trading.service.VerificationCodeService;
import com.willis.trading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<Users> getUserProfile(
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }

    @PostMapping("/api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOtp(
            @RequestHeader("Authorization") String jwt,
            @PathVariable VerificationType verificationType) throws Exception {

        Users user=userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());
        if(verificationCode==null){
            verificationCode=verificationCodeService.
                    sendVerificationCode(user,verificationType);

        }
        if(verificationType.equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(user.getEmail(),verificationCode.getOtp());
        }




        return new ResponseEntity<>("verification otp sent successfully", HttpStatus.OK);
    }

    @PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<Users> enableTwoFactorAuthentication(
            @PathVariable String otp,
            @RequestHeader("Authorization") String jwt) throws Exception {
        Users user=userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode=verificationCodeService.getVerificationCodeByUser(user.getId());
        String sendTo=verificationCode.getVerificationType().equals(VerificationType.EMAIL)?
                verificationCode.getEmail():verificationCode.getMobile();

        boolean isVerified=verificationCode.getOtp().equals(otp);

        if(isVerified){
            Users updatedUser=userService.enableTwoFactorAuthentication(
                    verificationCode.getVerificationType(),sendTo,user);
            verificationCodeService.deleteVerificatonCodeById(verificationCode);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }


        throw new Exception("wrong otp");
    }

    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendForgotPasswordOtp(

            @RequestBody ForgotPasswordTokenRequest req) throws Exception {
        Users user=userService.findUserByEmail(req.getSendTo());
        String otp= OtpUtils.generateOTP();
        UUID uuid=UUID.randomUUID();
        String id=uuid.toString();

        ForgotPasswordToken token=forgotPasswordService.findByUser(user.getId());
        if(token==null){
            token=forgotPasswordService.createToken(user,id,otp,req.getVerificationType(), req.getSendTo());


        }

        if(req.getVerificationType().equals(VerificationType.EMAIL)){
            emailService.sendVerificationOtpEmail(
                    user.getEmail(),
                    token.getOtp());
        }
        AuthResponse response=new AuthResponse();
        response.setSession(token.getId());
        response.setMessage("Password reset otp sent successfully");


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
