package com.sibdever.water_base.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

// TODO FIX THIS CONFIGURATION.
//  LOAD SECRET KEY FROM FILE OR ENV VAR. This class should be @ConfigurationProperties
@Configuration
public class JwtConfig {
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Key getSecretKey() {
        return secretKey;
    }
}
