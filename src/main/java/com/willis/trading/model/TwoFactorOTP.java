package com.willis.trading.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class TwoFactorOTP {
    @Id
    private String id;
    private String otp;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private Users user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String jwt;

}
