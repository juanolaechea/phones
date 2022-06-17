package com.utn.phones.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.utn.phones.Utils.LocalDateDeserializer;
import com.utn.phones.Utils.LocalDateSerializer;
import com.utn.phones.Utils.PostResponse;
import com.utn.phones.controller.BackController.PhoneLineBackController;
import com.utn.phones.domain.Bill;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.PhoneLineType;
import com.utn.phones.domain.Client;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.service.PhoneLineService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
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
                        .content(aPhoneJson()))
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
        List<PhoneLine> phoneLines = new ArrayList<>();
        phoneLines.add(aPhoneLine());
        when(phoneLineService.getAll()).thenReturn(phoneLines);
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/phoneLine/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());
    }

    @Test //ok
    public void getAllNoContent()throws Exception{
        List<PhoneLine> phoneLines = new ArrayList<>();
        when(phoneLineService.getAll()).thenReturn(phoneLines);
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/phoneLine/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(HttpStatus.NO_CONTENT.value(),resultActions.andReturn().getResponse().getStatus());
    }

    @Test //ok
    public void getPhoneLineById()throws Exception {
        when(phoneLineService.findByCode(aPhoneLine().getIdLine())).thenReturn(aPhoneLine());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/phoneLine/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(), resultActions.andReturn().getResponse().getStatus());
    }
    @Test //ok
    public void getPhoneLineByIdBadRequest()throws Exception {
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/phoneLine/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(), resultActions.andReturn().getResponse().getStatus());
    }
    @Test //ok
    public void deletePhoneLine()throws Exception {
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .delete("/api/phoneLine/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());
    }
    @Test //ok
    public void enablePhoneLine()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/phoneLine/enable/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void enablePhoneLineException()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/phoneLine/enable/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void disable()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/phoneLine/disable/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void disableException()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/phoneLine/disable/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn().getResponse().getStatus());

    }








}


