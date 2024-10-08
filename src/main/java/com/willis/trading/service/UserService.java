package com.willis.trading.service;

import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.Users;

public interface UserService {
    public Users findUserProfileByJwt(String jwt) throws Exception;
    public Users findUserByEmail(String email) throws Exception;
    public Users findUserById(Long userId) throws Exception;
    public Users enableTwoFactorAuthentication(VerificationType verificationType,
                                               String sendTo,
                                               Users user);
    Users updatePassword(Users user, String newPassword);

}
