package com.utn.phones.controller.WebController;


import com.utn.phones.domain.Call;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.service.CityService;
import com.utn.phones.service.ClientService;
import org.hibernate.type.LocalDateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @GetMapping(URL_WEB+"/{idClient}")
    public ResponseEntity<List<Call>> findAllCallByClient(@PathVariable("idClient") Integer idClient,
                                                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from,
                                                          @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to){
       // LocalDate from ;
        //from=LocalDate.of(2022,04,20);
        //LocalDate to ;
        //to=LocalDate.of(2022,05,20);

        List<Call>calls= this.clientService.getClientByRank(idClient,from,to);
        return calls.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(calls);
    }
}
