package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.*;
import com.utn.phones.exceptions.*;
import com.utn.phones.persistence.CityRepository;
import com.utn.phones.persistence.PhoneLineRepository;
import com.utn.phones.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.EntityURLBuilder.buildURL;

@Service
public class UserService implements UserDetailsService {

    private static final String currentPath = "user";



    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = (User) userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
        return u ;
    }

    public PostResponse addUser(User user) {
        User u = userRepository.save(user);
        return PostResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .link(buildURL(currentPath, u.getUsername().toString()))
                .build();
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public User findByCode(Integer idUser) {
        return this.userRepository.getById(idUser);
    }
}
