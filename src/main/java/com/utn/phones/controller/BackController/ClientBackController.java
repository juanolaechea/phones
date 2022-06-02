package com.utn.phones.controller.BackController;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.User;
import com.utn.phones.exceptions.*;

import com.utn.phones.service.CityService;
import com.utn.phones.service.ClientService;
import com.utn.phones.service.PhoneLineService;
import com.utn.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class ClientBackController {

    private CityService cityService;
    private PhoneLineService phoneLineService;
    private ClientService clientService;

    @Autowired
    public ClientBackController(CityService cityService, PhoneLineService phoneLineService, ClientService clientService) {
        this.cityService = cityService;
        this.phoneLineService = phoneLineService;
        this.clientService = clientService;
    }



    //Agregar cliente /testeOk
    @PostMapping(URL_CLIENT + "/")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse addCient(@RequestBody Client user){
        return clientService.addClient(user);
    }


    //Traer todos los clientes
    @GetMapping(URL_CLIENT)
    public List<Client> findAllClient(){
        return clientService.findAllClient();
    }

    //Traer client por id
    @GetMapping(path=URL_CLIENT + "/{idClient}")
    public Client findClientById (Authentication auth, @PathVariable("idClient") final Integer idClient)throws ElementDoesNotExistsException,BadRequestException, DeauthorizedException {
        User u = (User) auth.getPrincipal();
        Client c =this.clientService.findByCode(idClient);
        if(c.getUser().equals(u)){
            return this.clientService.findByCode(idClient);
        }else {
            throw new DeauthorizedException();
        }

    }

   //Agregar ciudad a client
    @PutMapping(path = URL_CLIENT + "/{idClient}" + URL_CITY +"/{idCity}")
    public PostResponse putCityInClient(@PathVariable("idClient")Integer idClient, @PathVariable("idCity")Integer idCity )throws ElementDoesNotExistsException, BadRequestException {
        return clientService.putCityInUser(idClient,idCity);
    }

    //Agregar line a client
    @PutMapping(path = URL_CLIENT + "/{idClient}" +URL_PHONE_LINE +"/{idLine}")
    public PostResponse putLineInClient(@PathVariable("idClient")Integer idClient,@PathVariable("IdLine")Integer idLine)throws ElementDoesNotExistsException, BadRequestException {
        return clientService.putPhoneLineInUser(idClient,idLine);
    }

    //Eliminar cliente
    @DeleteMapping(path =URL_CLIENT + "/{idClient}" )
    public PostResponse deleteClientById(@PathVariable("idClient")Integer idClient){
        return  this.clientService.deleteClient(idClient);
    }







}
