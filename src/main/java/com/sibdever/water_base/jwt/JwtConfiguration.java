package com.sibdever.water_base.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.sql.Date;

@Configuration
public class JwtConfiguration {

    private final JwtProperties jwtProperties;

    public JwtConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    public SecretKey secretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }

    @Bean
    public Long tokenDuration() {
        return jwtProperties.getTokenDuration();
    }
}
