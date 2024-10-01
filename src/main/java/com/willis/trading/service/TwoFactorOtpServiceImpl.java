package com.willis.trading.service;

import com.willis.trading.model.TwoFactorOTP;
import com.willis.trading.model.Users;
import com.willis.trading.repository.TwoFactorOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TwoFactorOtpServiceImpl implements TwoFactorOtpService{

    @Autowired
    private TwoFactorOtpRepository twoFactorOtpRepository;

    @Override
    public TwoFactorOTP createTwoFactorOtp(Users user, String otp, String jwt) {
        UUID uuid = UUID.randomUUID();
        String id=uuid.toString();
        TwoFactorOTP twoFactorOTP=new TwoFactorOTP();

        twoFactorOTP.setOtp(otp);
        twoFactorOTP.setJwt(jwt);
        twoFactorOTP.setId(id);
        twoFactorOTP.setUser(user);
       return twoFactorOtpRepository.save(twoFactorOTP);


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
