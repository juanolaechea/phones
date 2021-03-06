package com.utn.phones.controller.BackController;


import com.utn.phones.domain.Call;
import com.utn.phones.domain.User;
import com.utn.phones.dto.BillDto;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.BadRequestException;
import com.utn.phones.exceptions.DeauthorizedException;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class ClientWebController {

    private ClientService clientService;


    @Autowired
    public ClientWebController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(URL_WEB+"/bill"+"/{idClient}")
    public ResponseEntity<List<BillDto>> findAllBillByClientRank(Authentication auth, @PathVariable("idClient") Integer idClient,
                                                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from,
                                                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to) throws DeauthorizedException, ElementDoesNotExistsException {
        String username=clientService.findByCode(idClient).getUser().getUsername();
        User u = (User) auth.getPrincipal();
        if(u.getUsername().equals(username)){
            List<BillDto>bills= this.clientService.getBillsByRank(idClient,from,to);
            return bills.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bills);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }
    //
    @GetMapping(URL_WEB+"/{idClient}")
    public ResponseEntity<List<Call>> findAllCallByClientRank(Authentication auth,@PathVariable("idClient") Integer idClient,
                                                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from,
                                                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to)throws DeauthorizedException, ElementDoesNotExistsException{

        String username=clientService.findByCode(idClient).getUser().getUsername();
        User u = (User) auth.getPrincipal();
        if(u.getUsername().equals(username)){
            List<Call>calls= this.clientService.getClientByRank(idClient,from,to);
            return calls.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(calls);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    //Traer client por id
    @GetMapping(path =URL_WEB + URL_CLIENT + "/{idClient}")
    public Object findClientById(Authentication auth, @PathVariable("idClient") Integer idClient) throws DeauthorizedException, ElementDoesNotExistsException, BadRequestException {

        String username=clientService.findByCode(idClient).getUser().getUsername();
        User u = (User) auth.getPrincipal();
        if(u.getUsername().equals(username)){
            return ClientDto.to(clientService.findByCode(idClient));
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }


}
