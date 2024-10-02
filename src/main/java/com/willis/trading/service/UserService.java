package com.willis.trading.service;

import com.willis.trading.model.Users;

public interface UserService {
    public Users findUserProfileByJwt(String jwt);
    public Users findUserByEmail(String email);
    public Users findUserById(Long userId);
    public Users enableTwoFactorAuthentication(Users user);
    Users updatePassword(Users user, String newPassword);

}
