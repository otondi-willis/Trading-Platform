package com.willis.trading.service;

import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.ForgotPasswordToken;
import com.willis.trading.model.Users;

public interface ForgotPasswordService {
    ForgotPasswordToken createToken(Users user,
                                    String id,
                                    String otp,
                                    VerificationType verificationType,
                                    String sendTo);
    ForgotPasswordToken findById(String id);
    ForgotPasswordToken findByUser(Long userId);
    void deleteToken(ForgotPasswordToken token);


}
