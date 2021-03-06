package com.utn.phones.service;

import com.utn.phones.domain.City;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private CityRepository cityRepository;


    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll() {
       return this.cityRepository.findAll();
    }


    public City findById(Integer idCity) throws ElementDoesNotExistsException{
        return  this.cityRepository.findById(idCity).orElseThrow(ElementDoesNotExistsException::new);
    }

    public City findByCode(String code){
        return this.cityRepository.findByCode(code);
    }


    public City getCodeByNumber(String number) {
        return this.cityRepository.getCodeByNumber(number);
    }


}
