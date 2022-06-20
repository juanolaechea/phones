package com.utn.phones.dto;

import com.utn.phones.domain.Call;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Integer id;
    private String name;
    private String lastName;
    private Integer dni;
 


    public static ClientDto to (Client client) {

        ClientDto clientdto = ClientDto.builder()
                .id(client.getIdClient())
                .name(client.getName())
                .lastName(client.getLastName())
                .dni(client.getDni())
                .build();

        return clientdto;

    }


}
