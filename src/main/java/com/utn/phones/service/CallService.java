package com.utn.phones.service;

import com.utn.phones.domain.Call;
import com.utn.phones.persistence.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {


    private CallRepository callRepository;


    @Autowired
    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    public void newCall(Call call) {
        this.callRepository.save(call);
    }
}
