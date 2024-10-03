package com.willis.trading.service;

import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.Users;
import com.willis.trading.model.VerificationCode;
import com.willis.trading.repository.VerificationCodeRepository;
import com.willis.trading.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Override
    public VerificationCode sendVerificationCode(Users user, VerificationType verificationType) {
        VerificationCode verificationCode1=new VerificationCode();
        verificationCode1.setOtp(OtpUtils.generateOTP());
        verificationCode1.setVerificationType(verificationType);
        verificationCode1.setUser(user);

        return verificationCodeRepository.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
        Optional<VerificationCode> verificationCode=
                verificationCodeRepository.findById(id);
        if(verificationCode.isPresent()){
            return verificationCode.get();
        }
        throw new Exception("verification code not found");


    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long userId) {
        return null;
    }

    @Override
    public void deleteVerificatonCodeById(VerificationCode verificationCode) {

    }
}
