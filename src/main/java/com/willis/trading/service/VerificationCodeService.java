package com.willis.trading.service;

import com.willis.trading.model.VerificationCode;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(VerificationCode verificationCode);
    VerificationCode getVerificationCodeById(Long id);
    VerificationCode getVerificationCodeByUser(Long userId);
    void deleteVerificatonCodeById(VerificationCode verificationCode);


}
