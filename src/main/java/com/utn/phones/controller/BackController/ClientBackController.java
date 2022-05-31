package com.utn.phones.controller.BackController;


import com.mysql.cj.xdevapi.Client;
import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.City;
import com.utn.phones.domain.User;
import com.utn.phones.dto.UserDto;
import com.utn.phones.exceptions.ClientException;

import com.utn.phones.exceptions.ValidationClientException;
import com.utn.phones.service.CityService;
import com.utn.phones.service.PhoneLineService;
import com.utn.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.PublicKey;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class ClientBackController {


    private UserService userService;
    private CityService cityService;
    private PhoneLineService phoneLineService;


    @Autowired
    public ClientBackController(UserService userService, CityService cityService, PhoneLineService phoneLineService) {
        this.userService = userService;
        this.cityService = cityService;
        this.phoneLineService = phoneLineService;
    }
    //Agregar cliente
    @PostMapping(URL_CLIENT + "/")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse addCient(@RequestBody User user){
        return userService.addClient(user);
    }

    //Traer todos los clientes
    @GetMapping(URL_CLIENT)
    public List<User> findAllClient(){
        return userService.findAllClient();
    }

    //Traer client por id
    @GetMapping(path=URL_CLIENT + "/{idClient}")
    public User findClientById (@PathVariable("idClient") final Integer idClient){
        return this.userService.findByCode(idClient);
    }

   //Agregar ciudad a client
    @PutMapping(path = URL_CLIENT + "/{idClient}" + URL_CITY +"/{idCity}")
    public PostResponse putCityInClient(@PathVariable("idClient")Integer idClient, @PathVariable("idCity")Integer idCity ){
        return  userService.putCityInUser(idClient,idCity);
    }

    //Agregar line a client
    @PutMapping(path = URL_CLIENT + "/{idClient}" +URL_PHONE_LINE + "/{idLine}")
    public PostResponse putLineInClient( @PathVariable("idClient")Integer idClient,@PathVariable("IdLine")Integer idLine){
        return userService.putLineInClient(idClient,idLine);
    }



    //Eliminar cliente
    @DeleteMapping(path =URL_CLIENT + "/{idClient}" )
    public PostResponse deleteClientById(@PathVariable("idClient")Integer idClient){
        return  this.userService.deleteClient(idClient);
    }

    //Habilitar cliente
    @PutMapping(path=URL_CLIENT + "/{idClient}")
    public void enableClient(@PathVariable("idClient") Integer idClient ) throws ValidationClientException, ClientException {
        try {
            this.userService.anableClient(idClient);
        }catch (JpaSystemException e ){
            throw new ClientException(e.getCause().getCause().getMessage());
        }
    }











}
