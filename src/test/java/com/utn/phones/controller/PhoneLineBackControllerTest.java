package com.utn.phones.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.Utils.LocalDateDeserializer;
import com.utn.phones.Utils.LocalDateSerializer;
import com.utn.phones.controller.BackController.ClientBackController;
import com.utn.phones.controller.BackController.PhoneLineBackController;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.PhoneLineType;
import com.utn.phones.domain.User;
import com.utn.phones.service.PhoneLineService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PhoneLineBackController.class)
public class PhoneLineBackControllerTest extends Abstrascttest{

    @MockBean
    PhoneLineService phoneLineService;

    @Test //ok
    public void addPhoneLine()throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/phoneLine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aPhoneLineJson()))
                .andExpect(status().isCreated());

        assertEquals(HttpStatus.CREATED.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test //ok
    public void addPhoneLineBadRiquest()throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/phoneLine")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test //ok
    public void getAll()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/phoneLine/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());
    }






    public static String aPhoneLineJson() {
        final Gson prettyGson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
        return prettyGson.toJson(aPhoneLine());
    }

    public static PhoneLine aPhoneLine(){
        return new PhoneLine(1,"12345678",true, PhoneLineType.valueOf("home"),new User());
    }
}
