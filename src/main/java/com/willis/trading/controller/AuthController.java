package com.willis.trading.controller;

import com.willis.trading.config.JwtProvider;
import com.willis.trading.model.TwoFactorOTP;
import com.willis.trading.model.Users;
import com.willis.trading.repository.UserRepository;
import com.willis.trading.response.AuthResponse;
import com.willis.trading.service.CustomUserDetailsService;
import com.willis.trading.service.TwoFactorOtpService;
import com.willis.trading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Users users) throws Exception {

       String userName=users.getEmail();
       String password=users.getPassword();


        Authentication auth=authenticateM(userName,password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        Users authUser=userRepository.findByEmail(userName);

        if(users.getTwoFactorAuth().isEnabled()){
            AuthResponse res=new AuthResponse();
            res.setMessage("Two factor auth is enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp= OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP=twoFactorOtpService.findByUser(authUser.getId());
            if(oldTwoFactorOTP!=null){
                twoFactorOtpService.deletedTwoFactorOtp(oldTwoFactorOTP);
            }
            TwoFactorOTP newTwoFactorOTP = twoFactorOtpService.createTwoFactorOtp(authUser,otp,jwt);
            res.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
        }

        AuthResponse res=new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login successful");


        return new ResponseEntity<>(res, HttpStatus.CREATED);



    }

    private Authentication authenticateM(String userName, String password) {
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
        if(userDetails==null){
            throw new BadCredentialsException("invalid username");

        }
        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }
}
