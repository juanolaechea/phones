package com.utn.phones.dto;


import com.utn.phones.domain.Call;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

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
                .phoneLineOrigin(callSenderDto.getOrigin())
                .phoneLineDestination(callSenderDto.getDestination())
                .dateTime(LocalDateTime.parse(callSenderDto.getDatetime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .duration(callSenderDto.getDuration())
                .build();


        return call;
    }



}
