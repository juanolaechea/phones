package com.utn.phones.controller.BackController;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Call;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.User;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.*;

import com.utn.phones.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class ClientBackController {

    private CityService cityService;
    private PhoneLineService phoneLineService;
    private ClientService clientService;
    private CallService callService;

    public ClientBackController(CityService cityService, PhoneLineService phoneLineService, ClientService clientService, CallService callService) {
        this.cityService = cityService;
        this.phoneLineService = phoneLineService;
        this.clientService = clientService;
        this.callService = callService;
    }

    //Agregar cliente /testeOk
    @PostMapping(URL_CLIENT + "/")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse addCient(@RequestBody Client client) {
        return clientService.addClient(client);
    }


    //Traer todos los clientes
    @GetMapping(URL_CLIENT)
    public ResponseEntity<List<ClientDto>> findAllClients(){
        List<ClientDto>clients= this.clientService.findAllClient();
        return clients.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clients);
    }

    //Traer client por id
    @GetMapping(path = URL_CLIENT + "/{idClient}")
    public ClientDto findClientById(Authentication auth, @PathVariable("idClient") final Integer idClient) throws ElementDoesNotExistsException, BadRequestException, DeauthorizedException {
        User u = (User) auth.getPrincipal();
        Client c = this.clientService.findByCode(idClient);
        ClientDto cd =new ClientDto();
        if (c.getUser().equals(u)) {
            return ClientDto.to(clientService.findByCode(idClient));
        } else {
            throw new DeauthorizedException();
        }

    }

    //Agregar ciudad a client
    @PutMapping(path = URL_CLIENT + "/{idClient}" + URL_CITY + "/{idCity}")
    public PostResponse putCityInClient(@PathVariable("idClient") Integer idClient, @PathVariable("idCity") Integer idCity) throws ElementDoesNotExistsException, BadRequestException {
        return clientService.putCityInUser(idClient, idCity);
    }

    //Agregar line a client   ojo lo que devuelve
    @PutMapping(path = URL_CLIENT + "/{idClient}" + URL_PHONE_LINE + "/{idLine}")
    public PostResponse putLineInClient(@PathVariable("idClient") Integer idClient, @PathVariable("idLine") Integer idLine) throws ElementDoesNotExistsException, BadRequestException {
        return this.clientService.putPhoneLineInUser(idClient, idLine);
    }

    @PutMapping(path = URL_CLIENT + "/{idClient}" + URl_USER + "/{idUser}")
    public PostResponse putUserInClient(@PathVariable("idClient") Integer idClient, @PathVariable("idUser") Integer idUser) throws ElementDoesNotExistsException, BadRequestException {
        return this.clientService.putUserInClient(idClient, idUser);
    }


    //Eliminar cliente
    @DeleteMapping(path = URL_CLIENT + "/{idClient}")
    public PostResponse deleteClientById(@PathVariable("idClient") Integer idClient) throws ElementDoesNotExistsException  {
        return this.clientService.deleteClient(idClient);
    }

    @GetMapping(URL_WEB)
    public List<Call> findAllCallDate() {
        return callService.getAllCalls();
    }







}
