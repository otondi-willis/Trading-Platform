package com.willis.trading.service;

import com.willis.trading.model.TwoFactorOTP;
import com.willis.trading.model.Users;

public interface TwoFactorOtpService {
    TwoFactorOTP createTwoFactorOtp(Users user,String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactoOTP(TwoFactorOTP twoFactorOtp, String otp);

    void deletedTwoFactorOtp(TwoFactorOTP twoFactorOtp);


}
