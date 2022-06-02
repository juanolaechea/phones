package com.utn.phones.controller;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.User;
import com.utn.phones.dto.LoginRequestDto;
import com.utn.phones.dto.LoginResponseDto;
import com.utn.phones.service.JwtService;
import com.utn.phones.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.utn.phones.constants.ControllerConstants.URl_USER;

@RestController
@AllArgsConstructor
public class UserController {


    UserService userService;
    AuthenticationManager authenticationManager;
    JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
            authenticationManager.authenticate(authenticationToken);
        } catch(BadCredentialsException badCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails = userService.loadUserByUsername(loginRequestDto.getUsername());
        String token = jwtService.createToken(userDetails);
        return ResponseEntity.ok(LoginResponseDto.builder().token(token).build());
    }

    @PostMapping(URl_USER)
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/api/users/{username}")
    public ResponseEntity<UserDetails> findByUsername(Authentication auth, @PathVariable("username") String username) {
        User loggedUser = (User) auth.getPrincipal();
        if (loggedUser.getUsername().equals(username)) {
            return ResponseEntity.ok(userService.loadUserByUsername(username));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }

}
