package com.utn.phones.service;

import com.utn.phones.domain.*;
import com.utn.phones.persistence.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {


    private CallRepository callRepository;
    private CityService cityService;
    private PhoneLineService phoneLineService;
    private TariffService tariffService;
    private ClientService clientService;

    @Autowired
    public CallService(CallRepository callRepository, CityService cityService, PhoneLineService phoneLineService, TariffService tariffService, ClientService clientService) {
        this.callRepository = callRepository;
        this.cityService = cityService;
        this.phoneLineService = phoneLineService;
        this.tariffService = tariffService;
        this.clientService = clientService;
    }

    public void newCall(Call call) {
        City cityOrigin = cityService.getCodeByNumber(call.getPhoneLineOrigin());
        City cityDestinatio = cityService.getCodeByNumber(call.getPhoneLineDestination());
        Tariff t = tariffService.getTariffByCities(cityOrigin.getCode(), cityDestinatio.getCode());
        Float totalPrice = totalPrice(call,t.getPriceXminute());
        PhoneLine pl=phoneLineService.getPhoneLineByNumberLine(call.getPhoneLineOrigin());
        Client c =clientService.getClientByNumber(pl);

        call.setCityOrigin(cityOrigin);
        call.setCityDestination(cityDestinatio);
        call.setPriceXmin(t.getPriceXminute());
        call.setTotalPrice(totalPrice);
        call.setInvoice(false);
        this.callRepository.save(call);
    }

    public Float totalPrice(Call call,Float precexmin){
        return call.getDuration() * precexmin;
    }
}
