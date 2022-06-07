package com.utn.phones.dto;


import com.utn.phones.domain.Call;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.service.PhoneLineService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallSenderDto {


    String origin;
    String destination;
    String datetime;
    Long duration;


    public static Call to (CallSenderDto callSenderDto){

        Call call = Call.builder()
                .phoneLineOrigin(PhoneLineDto.to(new PhoneLineDto(callSenderDto.getOrigin())))
                .phoneLineDestination(PhoneLineDto.to(new PhoneLineDto(callSenderDto.getDestination())))
                .dateTime(LocalDateTime.parse(callSenderDto.getDatetime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .duration(callSenderDto.getDuration())
                .build();

        return call;
    }



}
