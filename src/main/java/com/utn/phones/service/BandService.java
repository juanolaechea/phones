package com.utn.phones.service;


import com.utn.phones.domain.Band;
import com.utn.phones.persistence.BandRepository;
import com.utn.phones.persistence.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {

    BandRepository bandRepository ;


    @Autowired
    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }


    public  Float getAllBandByTariff(Integer idTariff,Integer hourIni){
        Float response= Float.valueOf(0);
        List<Band> brands = bandRepository.findAllByTariff(idTariff);

        for (Band brand : brands) {
            response=getPriceByHour(brand.getIdBand(),hourIni);
        }

        return response;

    }

    public Float getPriceByHour(Integer idBand, Integer hour ){
        Float response = Float.valueOf(0);

        Band b = this.bandRepository.getById(idBand);
        Integer i= b.getHourInitial();
        Integer f=b.getHourFinished();
        if(hour>= i && hour <=f){
            response=b.getPrice();
        }
        return response;
    }

}
