package com.sibdever.water_base.controller;

import com.sibdever.water_base.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// TESTS ONLY
@RestController
@RequestMapping("/login")
public class LoginController {
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Autowired
    public LoginController(CustomUserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @PostMapping
    @ResponseBody
    public String login(String username, String password) {
        UserDetails details = userDetailsService.loadUserByUsername(username);
        System.out.println(encoder.matches(password, details.getPassword()));
        return "OK";
    }

}
