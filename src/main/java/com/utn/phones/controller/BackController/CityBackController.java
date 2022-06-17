package com.utn.phones.controller.BackController;


import com.utn.phones.domain.City;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.DeauthorizedException;
import com.utn.phones.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;

@Controller
@RestController
@RequestMapping(BASE_URL)
public class CityBackController {

    CityService cityService;

    @Autowired
    public CityBackController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(URL_CITY+"/")
    public ResponseEntity<List<City>> findAllCity()throws DeauthorizedException {
        List<City>clients= this.cityService.findAll();
        return clients.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(clients);
    }
}
