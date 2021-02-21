package com.sibdever.water_base.controller;

import com.sibdever.water_base.service.UsersOperationsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UsersOperationsService usersService;

    public RegisterController(UsersOperationsService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    @ResponseBody
    public String test() {
        return "Hello!!!";
    }

    @PostMapping
    @ResponseBody
    public String register(String username, String password) {
        System.out.println("Try to register");
        usersService.createUser(username, password);
        return "OK";
    }
}
