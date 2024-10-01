package com.willis.trading.service;

import com.willis.trading.model.TwoFactorOTP;
import com.willis.trading.model.Users;

public class TwoFactorOtpServiceImpl implements TwoFactorOtpService{

    @Override
    public TwoFactorOTP createTwoFactorOtp(Users user, String otp, String jwt) {
        return null;
    }

    @Override
    public TwoFactorOTP findByUser(Long userId) {
        return null;
    }

    @Override
    public TwoFactorOTP findById(String id) {
        return null;
    }

    @Override
    public boolean verifyTwoFactoOTP(TwoFactorOTP twoFactorOtp, String otp) {
        return false;
    }

    @Override
    public void deletedTwoFactorOtp(TwoFactorOTP twoFactorOtp) {

    }
}
