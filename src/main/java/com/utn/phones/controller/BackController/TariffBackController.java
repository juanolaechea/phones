package com.utn.phones.controller.BackController;

import com.utn.phones.domain.Tariff;
import com.utn.phones.exceptions.DeauthorizedException;
import com.utn.phones.service.PhoneLineService;
import com.utn.phones.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;
import static com.utn.phones.constants.ControllerConstants.URl_USER;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class TariffBackController {


    private TariffService tariffService;

    @Autowired
    public TariffBackController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping(path = URL_TARIFF +"/")
    public List<Tariff>findAllTariff()throws DeauthorizedException {return this.tariffService.getAllTariff();}









}
