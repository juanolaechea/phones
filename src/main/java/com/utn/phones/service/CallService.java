package com.utn.phones.service;

import com.utn.phones.domain.*;
import com.utn.phones.persistence.CallRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import static java.time.LocalDateTime.now;
@Slf4j
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
        City cityOrigin = cityService.getCodeByNumber(call.getPhoneLineOrigin().getNumberLine());
        City cityDestinatio = cityService.getCodeByNumber(call.getPhoneLineDestination().getNumberLine());
        Tariff t = tariffService.getTariffByCities(cityOrigin.getCode(), cityDestinatio.getCode());
        Float totalPrice = totalPrice(call, t.getPriceXminute());
        PhoneLine plo = phoneLineService.getPhoneLineByNumberLine(call.getPhoneLineOrigin().getNumberLine());
        PhoneLine pld = phoneLineService.getPhoneLineByNumberLine(call.getPhoneLineDestination().getNumberLine());
        Client c = clientService.getClientByNumber(plo);
        call.setCityOrigin(cityOrigin);
        call.setCityDestination(cityDestinatio);
        call.setPriceXmin(t.getPriceXminute());
        call.setTotalPrice(totalPrice);
        call.setPhoneLineOrigin(plo);
        call.setPhoneLineDestination(pld);
        call.setClient(c);
        call.setInvoice(false);
        this.callRepository.save(call);
    }

    public Float totalPrice(Call call, Float precexmin) {
        return call.getDuration() * precexmin;
    }

    public List<Call> getAllCalls() {
        return this.callRepository.findAll();
    }

}

