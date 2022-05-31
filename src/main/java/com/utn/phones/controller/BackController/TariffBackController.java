package com.utn.phones.controller.BackController;

import com.utn.phones.domain.Tariff;
import com.utn.phones.service.PhoneLineService;
import com.utn.phones.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.utn.phones.constants.ControllerConstants.BASE_URL;
import static com.utn.phones.constants.ControllerConstants.URL_TARIFF;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class TariffBackController {


    private TariffService tariffService;
    private PhoneLineService phoneLineService;

    @Autowired
    public TariffBackController(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @GetMapping(path = URL_TARIFF +"/")
    public List<Tariff>findAllTariff(){return this.tariffService.getAllTariff();}






}
