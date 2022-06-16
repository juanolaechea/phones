package com.utn.phones.controller;


import com.utn.phones.controller.BackController.ClientWebController;
import com.utn.phones.domain.User;
import com.utn.phones.dto.BillDto;
import com.utn.phones.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.aClient;
import static com.utn.phones.constants.ControllerConstants.URL_WEB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ClientWebController.class)
public class ClientWebControllerTest extends Abstrascttest {

    @MockBean
    ClientService clientService;







}

/*
    @GetMapping(URL_WEB+"/bill"+"/{idClient}")
    public ResponseEntity<List<BillDto>> findAllBillByClientRank(Authentication auth, @PathVariable("idClient") Integer idClient,
                                                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from,
                                                                 @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to){
        String username=clientService.findByCode(idClient).getUser().getUsername();
        User u = (User) auth.getPrincipal();
        if(u.getUsername().equals(username)){
            List<BillDto>bills= this.clientService.getBillsByRank(idClient,from,to);
            return bills.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(bills);
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }

 */