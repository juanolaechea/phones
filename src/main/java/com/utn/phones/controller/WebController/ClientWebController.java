package com.utn.phones.controller.WebController;


import com.utn.phones.domain.Bill;
import com.utn.phones.domain.Call;
import com.utn.phones.domain.Client;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.service.BillService;
import com.utn.phones.service.CallService;
import com.utn.phones.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController(BASE_URL)
public class ClientWebController {

    private ClientService clientService;
    private BillService billService;
    private CallService callService;

    @Autowired
    public ClientWebController(ClientService clientService, BillService billService, CallService callService) {
        this.clientService = clientService;
        this.billService = billService;
        this.callService = callService;
    }

    /*
    //Consulta de facturas del usuario logueado por rango de fechas.
    @GetMapping(path=URL_CLIENT+"/bill/"+"{idClient}")
    public List<Bill> getBillRankDate(@PathVariable("idClient") Integer idClient,
                                      @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime start,
                                      @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime end) throws ElementDoesNotExistsException {
        return billService.getBillByRank(idClient,start,end);

    }

     */

    //Consulta de llamadas por usuarios logeados por rango de fecha
    @GetMapping(path=BASE_web + "/{idClient}")
    public List<Call> getCallRankDate(@PathVariable("idClient") Integer idClient,
                                      @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime from,
                                      @RequestParam @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime to
                                      ) throws ElementDoesNotExistsException {
        return callService.getCallByRank(idClient,from,to);

    }
}
