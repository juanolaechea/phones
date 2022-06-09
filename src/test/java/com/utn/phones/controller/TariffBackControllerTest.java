package com.utn.phones.controller;

import com.utn.phones.controller.BackController.PhoneLineBackController;
import com.utn.phones.controller.BackController.TariffBackController;
import com.utn.phones.service.TariffService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TariffBackController.class)
public class TariffBackControllerTest extends Abstrascttest {

    @MockBean
    TariffService tariffService;

    @Test //ok
    public void findAllTariff()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/tariff/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
}
