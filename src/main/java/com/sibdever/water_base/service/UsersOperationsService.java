package com.sibdever.water_base.service;

import com.sibdever.water_base.data.User;
import com.sibdever.water_base.data.UsersRepository;
import com.sibdever.water_base.security.PasswordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersOperationsService {
    private final UsersRepository usersRepository;
    private final PasswordConfig passwordConfig;

    public UsersOperationsService(UsersRepository usersRepository, PasswordConfig passwordConfig) {
        this.usersRepository = usersRepository;
        this.passwordConfig = passwordConfig;
    }

    public void createUser(String username, String password) {
        usersRepository.save(new User(username, passwordConfig.passwordEncoder().encode(password)));
    }
}