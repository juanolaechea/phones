package com.utn.phones.service;

import com.utn.phones.domain.City;
import com.utn.phones.domain.Tariff;
import com.utn.phones.persistence.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {

    private TariffRepository tariffRepository;
    private CityService cityService;

    public TariffService(TariffRepository tariffRepository, CityService cityService) {
        this.tariffRepository = tariffRepository;
        this.cityService = cityService;
    }

    @Autowired


    public List<Tariff> getAllTariff() {
        return this.tariffRepository.findAll();
    }

    public Tariff getTariffByCities(String codeO, String codeD){
        City o= this.cityService.findByCode("223");
        City d= this.cityService.findByCode("280");

         Tariff t = this.tariffRepository.findByCityOriginAndCityDestination(o,d);
         return t;
    }
}