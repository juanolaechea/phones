package com.utn.phones.controller.BackController;


import com.utn.phones.domain.City;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;
@Controller
@RestController
@RequestMapping(BASE_URL)
public class CityBackController {


    //no testear porque no va ELIMINAR

    private CityService cityService;

    @Autowired
    public CityBackController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(URL_CITY)
    public ResponseEntity<List<City>> findAllCities(){
        List<City>cities= this.cityService.findAll();
        return cities.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(cities);
    }
    @GetMapping(path = URL_CITY + "/{idCity}")
    public ResponseEntity<City>getCityById(@PathVariable("idCity")Integer idCity)throws ElementDoesNotExistsException {
        return ResponseEntity.ok(this.cityService.findById(idCity));
    }

    //eliminar
    @GetMapping(URL_CITY + "/code"+"/{number}")
    public City getCodeByNumber(@PathVariable("number")String  number){
        return this.cityService.getCodeByNumber(number);
    }




}
