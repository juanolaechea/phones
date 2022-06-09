package com.utn.phones.controller.BackController;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.User;
import com.utn.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.utn.phones.constants.ControllerConstants.URl_USER;

@Controller
@RestController
@RequestMapping("/backoffice/user")
public class UserBackController {


    UserService userService;

    @Autowired
    public UserBackController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path= URl_USER+"/")
    public PostResponse addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
