package com.utn.phones.service;

import com.utn.phones.domain.Band;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Tariff;
import com.utn.phones.persistence.BandRepository;
import com.utn.phones.persistence.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TariffService {

    private TariffRepository tariffRepository;
    private CityService cityService;
    private BandRepository bandRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository, CityService cityService) {
        this.tariffRepository = tariffRepository;
        this.cityService = cityService;
    }


    public List<Tariff> getAllTariff() {
        return this.tariffRepository.findAll();
    }

    public Tariff getTariffByCities(String codeO, String codeD){
        City o= this.cityService.findByCode(codeO);
        City d= this.cityService.findByCode(codeD);
         return this.tariffRepository.findByCityOriginAndCityDestination(o,d);

    }



}
