package com.utn.phones.service;

import com.utn.phones.domain.Call;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Tariff;
import com.utn.phones.persistence.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {


    private CallRepository callRepository;
    private CityService cityService;
    private PhoneLineService phoneLineService;
    private TariffService tariffService;

    @Autowired
    public CallService(CallRepository callRepository, CityService cityService, PhoneLineService phoneLineService, TariffService tariffService) {
        this.callRepository = callRepository;
        this.cityService = cityService;
        this.phoneLineService = phoneLineService;
        this.tariffService = tariffService;
    }

    public void newCall(Call call) {
        City cityOrigin = cityService.getCodeByNumber(call.getPhoneLineOrigin());
        City cityDestinatio = cityService.getCodeByNumber(call.getPhoneLineDestination());
        Tariff t = tariffService.getTariffByCities(cityOrigin.getCode(), cityDestinatio.getCode());
        Float totalPrice = totalPrice(call,t.getPriceXminute());

        call.setCityOrigin(cityOrigin);
        call.setCityDestination(cityDestinatio);
        call.setPriceXmin(t.getPriceXminute());
        call.setTotalPrice(totalPrice);
        this.callRepository.save(call);
    }

    public Float totalPrice(Call call,Float precexmin){
        return call.getDuration() * precexmin;
    }
}
