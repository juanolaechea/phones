package com.utn.phones.controller;

import com.utn.phones.controller.BackController.CityBackController;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Employee;
import com.utn.phones.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


import static com.utn.phones.Utils.TestUtils.aCity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CityBackController.class)
public class CityControllerTest extends Abstrascttest{

    @MockBean
    CityService cityService;


    @Test //ok
    public void findAllCity() throws Exception{
        List<City> cities = new ArrayList<>();
        cities.add(aCity());
        when(cityService.findAll()).thenReturn(cities);
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/city/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
}
