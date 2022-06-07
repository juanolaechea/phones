package com.utn.phones.dto;

import com.utn.phones.domain.PhoneLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneLineDto {

    String number;


    public static PhoneLine to (PhoneLineDto phoneLineDto){

        PhoneLine phoneLine= PhoneLine.builder().numberLine(phoneLineDto.getNumber())
                .build();

        return phoneLine;
    }
}
