package com.utn.phones.dto;

import antlr.StringUtils;
import com.utn.phones.domain.City;
import com.utn.phones.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String name;
    private String lastName;
    private Integer dni;
    private String password;
    private String userType;
    private City city;


}
