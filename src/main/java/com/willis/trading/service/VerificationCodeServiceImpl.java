package com.willis.trading.service;

import com.willis.trading.model.VerificationCode;
import com.willis.trading.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Override
    public VerificationCode sendVerificationCode(VerificationCode verificationCode) {
        return null;
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) {
        return null;
    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long userId) {
        return null;
    }

    @Override
    public void deleteVerificatonCodeById(VerificationCode verificationCode) {

    }
}
