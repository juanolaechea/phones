package com.utn.phones.dto;

import com.utn.phones.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private String name;
    private String lastName;
    private Integer dni;
    private String userType;
    private City city;


}
