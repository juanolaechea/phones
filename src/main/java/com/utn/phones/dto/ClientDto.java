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

    private String name;
    private String lastName;
    private Integer dni;
    private String user;
 


    public static ClientDto to (Client client) {

        ClientDto clientdto = ClientDto.builder()
                .name(client.getName())
                .lastName(client.getLastName())
                .dni(client.getDni())
                .user(client.getUser().getUsername())
                .build();

        return clientdto;

    }


}
