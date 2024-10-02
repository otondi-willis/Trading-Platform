package com.willis.trading.service;

import com.willis.trading.config.JwtProvider;
import com.willis.trading.model.Users;
import com.willis.trading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Users findUserProfileByJwt(String jwt) throws Exception {
        String email= JwtProvider.getEmailFromToken(jwt);
        Users user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public Users findUserByEmail(String email) throws Exception {
        Users user=userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public Users findUserById(Long userId) throws Exception {
        Optional<Users> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user not found");
        }
        return user.get();
    }

    @Override
    public Users enableTwoFactorAuthentication(Users user) {
        return null;
    }

    @Override
    public Users updatePassword(Users user, String newPassword) {
        return null;
    }
}
