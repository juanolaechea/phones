package com.utn.phones.controller;

import com.utn.phones.controller.BackController.CallBackController;
import com.utn.phones.dto.CallSenderDto;
import com.utn.phones.service.CallService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.utn.phones.Utils.TestUtils.aCallDtoJson;
import static com.utn.phones.Utils.TestUtils.aClientJson;
import static com.utn.phones.constants.ControllerConstants.URL_CALL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CallBackController.class)
public class CallBackControllTest extends Abstrascttest{


    @MockBean
    CallService callService;
    @Test
    public void newCall()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/calls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aCallDtoJson()))
                        .andExpect(status().isOk());


        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }

    @Test
    public void newCallException()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/calls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("aCallDtoJson()"))
                .andExpect(status().isBadRequest());


        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
}
