package com.sibdever.water_base.service;

import com.sibdever.water_base.data.User;
import com.sibdever.water_base.data.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersOperationsService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UsersOperationsService(UsersRepository usersRepository, PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.encoder = encoder;
    }

    public void createUser(String username, String password) {
        usersRepository.save(new User(username, encoder.encode(password)));
    }
}