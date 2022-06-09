package com.utn.phones.service;

import com.utn.phones.domain.Bill;
import com.utn.phones.persistence.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillService {

    BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getBillByRank(Integer idClient, LocalDateTime start, LocalDateTime end) {

        return billRepository.findBillByRank(idClient,start,end);
    }


}
