package com.willis.trading.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willis.trading.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;
    private String email;
    //private int mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private TwoFactorAuth twoFactorAuth=new TwoFactorAuth();

    private USER_ROLE role= USER_ROLE.ROLE_CUSTOMER;



}
