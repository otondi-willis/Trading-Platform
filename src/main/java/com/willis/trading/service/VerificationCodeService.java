package com.willis.trading.service;

import com.willis.trading.domain.VerificationType;
import com.willis.trading.model.Users;
import com.willis.trading.model.VerificationCode;
import org.springframework.security.core.userdetails.User;

public interface VerificationCodeService {
    VerificationCode sendVerificationCode(Users user, VerificationType verificationType);
    VerificationCode getVerificationCodeById(Long id) throws Exception;
    VerificationCode getVerificationCodeByUser(Long userId);
    void deleteVerificatonCodeById(VerificationCode verificationCode);


}
