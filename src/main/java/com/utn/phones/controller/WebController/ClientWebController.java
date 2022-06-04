package com.utn.phones.controller.WebController;


import com.utn.phones.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class ClientWebController {

    private ClientService clientService;

    @Autowired
    public ClientWebController(ClientService clientService) {
        this.clientService = clientService;
    }

    //Consulta de facturas del usuario logueado por rango de fechas.

    //Consulta de llamadas por usuarios logeados por rango de fecha
}
