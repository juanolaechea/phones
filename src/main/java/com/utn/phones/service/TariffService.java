package com.utn.phones.service;

import com.utn.phones.domain.City;
import com.utn.phones.domain.Tariff;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.BandRepository;
import com.utn.phones.persistence.TariffRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public Tariff getTariffByCities(String codeO, String codeD)throws ElementDoesNotExistsException {
        try{
            City o= this.cityService.findByCode(codeO);
            City d= this.cityService.findByCode(codeD);
            return this.tariffRepository.findByCityOriginAndCityDestination(o,d);
        }
        catch (ElementDoesNotExistsException e) {
            throw new ElementDoesNotExistsException();
        }
    }



}
