package com.sibdever.water_base.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

// TODO FIX THIS CONFIGURATION.
//  LOAD SECRET KEY FROM FILE OR ENV VAR. This class should be @ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private Long tokenDuration;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getTokenDuration() {
        return tokenDuration;
    }

    public void setTokenDuration(Long tokenDuration) {
        this.tokenDuration = tokenDuration;
    }
}
