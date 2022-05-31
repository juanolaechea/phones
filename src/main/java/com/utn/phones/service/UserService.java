package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.City;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.User;
import com.utn.phones.domain.UserType;
import com.utn.phones.exceptions.*;
import com.utn.phones.persistence.CityRepository;
import com.utn.phones.persistence.PhoneLineRepository;
import com.utn.phones.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.EntityURLBuilder.buildURL;

@Service
public class UserService {

    private static final String currentPath = "user";

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final PhoneLineRepository phoneLineRepository;


    public UserService(UserRepository userRepository, CityRepository cityRepository, PhoneLineRepository phoneLineRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.phoneLineRepository = phoneLineRepository;
    }
            //preguntar si es un cliente
    public PostResponse addClient(User user) throws ElementDoesNotAClient{

        if(user.getUserType() == UserType.client){
            User u = userRepository.save(user);
            return PostResponse.builder()
                    .httpStatus(HttpStatus.CREATED)
                    .link(buildURL(currentPath, u.getIdUser().toString()))
                    .build();
        }else {
            throw new ElementDoesNotAClient();
        }
    }

    public List<User> findAllClient() {
        List<User> users = new ArrayList<User>();
        List<User>clients= new ArrayList<User>();
        users=this.userRepository.findAll();
        for (User user1:users) {
            if(user1.getUserType()== UserType.client){
                clients.add(user1);
            }
        }
        return clients;
    }

    public User findByCode(Integer idClient) {
        return userRepository.findById(idClient)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User not Exists "));
    }

    public PostResponse putCityInUser(Integer idClient, Integer idCity) {
        User u= this.userRepository.getById(idClient);
        City c= this.cityRepository.getById(idCity);
        u.setCity(c);
        userRepository.save(u);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(currentPath, u.getCity().getName()))
                .build();
    }

    public void anableClient(Integer idClient) {

        if(idClient != null && idClient >0){
           User c= this.userRepository.getById(idClient);
            c.setValid(true);
            this.userRepository.save(c);

        }else {
            throw new ValidationClientException();
        }
    }

    public PostResponse deleteClient(Integer idClient) {

        userRepository.deleteById(idClient);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .build();
    }



    public PostResponse putLineInClient(Integer idClient, Integer idLine) {
        User u = userRepository.getById(idClient);
        PhoneLine pl= phoneLineRepository.getById(idClient);
        u.setPhoneLine(pl);
        userRepository.save(u);

        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(currentPath,u.getPhoneLine().getNumberLine()))
                .build();

    }


}
