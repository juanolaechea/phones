package com.utn.phones.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Timer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallSenderDto {

    private Integer originNumber;
    private Integer destinationNumber;
    private Timer duration;
    private Date dateAndTime;


}
