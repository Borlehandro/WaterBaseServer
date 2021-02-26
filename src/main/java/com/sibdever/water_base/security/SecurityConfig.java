package com.sibdever.water_base.security;

import com.sibdever.water_base.jwt.JwtCredentialsFilter;
import com.sibdever.water_base.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import java.sql.Date;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder encoder;
    private final CustomUserDetailsService userDetailsService;
    private final SecretKey secretKey;
    private final Long tokenDuration;

    @Autowired
    public SecurityConfig(PasswordEncoder encoder, CustomUserDetailsService userDetailsService, SecretKey secretKey, Long tokenDuration) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
        this.secretKey = secretKey;
        this.tokenDuration = tokenDuration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtCredentialsFilter(authenticationManager(), secretKey, tokenDuration))
                .addFilterAfter(new JwtTokenFilter(secretKey), JwtCredentialsFilter.class)
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}