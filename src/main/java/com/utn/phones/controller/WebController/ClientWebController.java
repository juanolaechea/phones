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

    /*
    //Consulta de facturas del usuario logueado por rango de fechas.
    @GetMapping(path=URL_CLIENT+"/bill/"+"{idClient}")
    public List<Bill>getBillRankDate(@PathVariable Integer idClient,
                                     @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime start,
                                     @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime end) throws ElementDoesNotExistsException {
        return billService.getBillByRank(idClient,start,end);

    }

     */


    //Consulta de llamadas por usuarios logeados por rango de fecha
}
