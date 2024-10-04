package com.willis.trading.service;

import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.ForgotPasswordToken;
import com.willis.trading.model.Users;
import com.willis.trading.repository.ForgotPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;


    @Override
    public ForgotPasswordToken createToken(Users user, String id, String otp, VerificationType verificationType, String sendTo) {
        ForgotPasswordToken token=new ForgotPasswordToken();
        token.setUser(user);
        token.setSendTo(sendTo);
        token.setVerificationType(verificationType);
        token.setOtp(otp);
        token.setId(id);

        return forgotPasswordRepository.save(token);
    }

    @Override
    public ForgotPasswordToken findById(String id) {
        return null;
    }

    @Override
    public ForgotPasswordToken findByUser(Long userId) {
        return null;
    }

    @Override
    public void deleteToken(ForgotPasswordToken token) {

    }
}
