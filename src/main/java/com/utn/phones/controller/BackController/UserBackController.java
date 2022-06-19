package com.utn.phones.controller.BackController;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.Tariff;
import com.utn.phones.domain.User;
import com.utn.phones.exceptions.DeauthorizedException;
import com.utn.phones.exceptions.ElementExistsException;
import com.utn.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class UserBackController {

    UserService userService;

    @Autowired
    public UserBackController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path= URl_USER+"/")
    public ResponseEntity<User> addUser(@RequestBody User user)throws DeauthorizedException, ElementExistsException {
        return userService.addUser(user);
    }

    @GetMapping(path = URl_USER +"/")
    public ResponseEntity<List<User>> findAllUser()throws DeauthorizedException{
        List<User>users=this.userService.getAllUser();
        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @GetMapping(path = URl_USER + "/{idUser}")
    public User getById(@PathVariable("idUser") Integer idUser)throws DeauthorizedException {
        return this.userService.findByCode(idUser);
    }
}
