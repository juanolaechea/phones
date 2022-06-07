package com.utn.phones.controller;

import com.utn.phones.controller.BackController.ClientBackController;
import com.utn.phones.service.CityService;
import com.utn.phones.service.ClientService;
import com.utn.phones.service.PhoneLineService;
import com.utn.phones.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ClientBackController.class)
public class ClientBackControllerTest extends Abstrascttest {


    @MockBean
    ClientService clientService;
    @MockBean
    CityService cityService;
    @MockBean
    PhoneLineService phoneLineService;

    @Test //ok
    public void addClient()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                .post("/api/client/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aClientJson()))
                .andExpect(status().isCreated());


        assertEquals(HttpStatus.CREATED.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test //ok
    public void addClientBadRequest()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }

    @Test //ok
    public void findAllClient()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }



    @Test //ok
    public void findClientById()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(), resultActions.andReturn().getResponse().getStatus());

    }


    @Test //ok
    public void findClientByIdException()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn().getResponse().getStatus());
    }

    @Test //ok
    public void deleteClientById()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .delete("/api/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void deleteClientByIdeException()throws Exception{
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .delete("/api/client/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn().getResponse().getStatus());

    }



    @Test //ok
    public void putCityInClient()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                .put("/api/client/1/city/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aClientJson()).content(aCityJson()))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }

    @Test
    public void putCityInClientBadRequest()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/client/a/city/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aClientJson()).content(aCityJson()))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 400");

    }



    /*
    @Test //ok
    public void putLineInClient()throws Exception{
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/client/1/phoneLine/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aPhoneJson()).content(aPhoneJson()))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }

     */



}
