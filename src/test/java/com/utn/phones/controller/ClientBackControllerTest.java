package com.utn.phones.controller;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.controller.BackController.ClientBackController;
import com.utn.phones.domain.Client;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ClientBackController.class)
public class ClientBackControllerTest extends Abstrascttest {


    @MockBean
    ClientService clientService;
    @MockBean
    CityService cityService;
    @MockBean
    PhoneLineService phoneLineService;
    @MockBean
    CallService callService;

    @Test //ok
    public void addClient()throws Exception{

        when(clientService.addClient(aClient())).thenReturn(ResponseEntity.ok(aClient()));

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                .post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aClientJson()))
                .andExpect(status().isCreated());


        assertEquals(HttpStatus.CREATED.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test //ok
    public void addClientBadRequest()throws Exception{

        Client  client= new Client();
        client=null;
        when(clientService.addClient(client)).thenReturn(ResponseEntity.badRequest().body(client));

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/client")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }

    @Test //ok
    public void findAllClient()throws Exception{
        List<ClientDto>clientDtos = new ArrayList<>();
        clientDtos.add(ClientDto.to(aClient()));
        when(clientService.findAllClient()).thenReturn(clientDtos);
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void findAllClientEmpty()throws Exception{


        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(HttpStatus.NO_CONTENT.value(),resultActions.andReturn().getResponse().getStatus());

    }



    @Test //ok
    public void findClientById()throws Exception{
        when(clientService.findByCode(1)).thenReturn(aClient());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(), resultActions.andReturn().getResponse().getStatus());
    }

    @Test //ok
    public void findClientByIdBadRequest()throws Exception{

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/client/A")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(), resultActions.andReturn().getResponse().getStatus());
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

        when(clientService.putCityInUser(aClient().getIdClient(), aCity().getIdCity()))
                .thenReturn(PostResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .build());

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

    @Test //ok
    public void putLineInClient()throws Exception{
        when(clientService.putPhoneLineInUser(aClient().getIdClient(),aPhoneLine().getIdLine()))
                .thenReturn(PostResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .build());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/client/1/phoneLine/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aClientJson()).content(aPhoneJson()))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }


    @Test //ok
    public void putLineInClientBadRequest()throws Exception{
        when(clientService.putPhoneLineInUser(aClient().getIdClient(),aPhoneLine().getIdLine()))
                .thenReturn(PostResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/client/1/phoneLine/a")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }

    @Test //ok
    public void putUserInClient()throws Exception{
        when(clientService.putUserInClient(aClient().getIdClient(),aUser().getIdUser()))
                .thenReturn(PostResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .build());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/client/1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aClientJson()).content(aPhoneJson()))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }

    @Test //ok
    public void putUserInClientBarRequest()throws Exception{
        when(clientService.putUserInClient(aClient().getIdClient(),aUser().getIdUser()))
                .thenReturn(PostResponse.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/client/1/user/a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aClientJson()).content(aPhoneJson()))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }





}
