package com.utn.phones.controller.WebController;

import com.utn.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }










}
