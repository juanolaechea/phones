package com.utn.phones.dto;


import com.utn.phones.domain.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {

    Integer amountCalls;
    Float cost;
    Float totalPrice;
    LocalDateTime billDate;
    LocalDateTime expirationDate;


    public static BillDto to (Bill bill){

        BillDto billdto= BillDto.builder()
                .amountCalls(bill.getAmountCalls())
                .cost(bill.getCost())
                .totalPrice(bill.getTotalPrice())
                .billDate(bill.getBillDate())
                .expirationDate(bill.getExpirationDate())
                .build();

        return billdto;
    }
}
