package com.utn.phones.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.Utils.LocalDateDeserializer;
import com.utn.phones.Utils.LocalDateSerializer;
import com.utn.phones.controller.BackController.ClientBackController;
import com.utn.phones.domain.City;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.User;
import com.utn.phones.domain.UserType;
import com.utn.phones.service.CityService;
import com.utn.phones.service.PhoneLineService;
import com.utn.phones.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.PortUnreachableException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ClientBackController.class)
public class ClientBackControllerTest extends Abstrascttest {


    @MockBean
    UserService userService;
    @MockBean
    CityService cityService;
    @MockBean
    PhoneLineService phoneLineService;

    @Test
    public void addClient()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                .post("/api/client/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aUserJson()))
                .andExpect(status().isCreated());


        assertEquals(HttpStatus.CREATED.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test
    public void addClientBadRequest()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test
    public void findAllClient()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }






    public static String aUserJson() {
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aUser());
    }

    public static User aUser() {
        return new User(1,"juan","OLaecvhea",12345,"1234",true, UserType.valueOf("client"),new City(),new PhoneLine());

    }
}
