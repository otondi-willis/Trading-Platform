package com.willis.trading.service;

import com.willis.trading.model.Users;
import com.willis.trading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Users findUserProfileByJwt(String jwt) {
        return null;
    }

    @Override
    public Users findUserByEmail(String email) {
        return null;
    }

    @Override
    public Users findUserById(Long userId) {
        return null;
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