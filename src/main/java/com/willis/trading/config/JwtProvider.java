package com.willis.trading.config;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtProvider {

    private static SecretKey key =Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

}
