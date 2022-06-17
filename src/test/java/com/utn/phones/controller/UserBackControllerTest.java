package com.utn.phones.controller;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.controller.BackController.UserBackController;

import com.utn.phones.domain.User;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UserBackController.class)
public class UserBackControllerTest extends Abstrascttest{

    @MockBean
    UserService userService;

    @Test
    public void addUser()throws Exception{

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aUserJson()))
                .andExpect(status().isOk());


        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }
    @Test //ok
    public void findAllUser()throws Exception{
        List<User> users = new ArrayList<>();
        users.add(aUser());
        when(userService.getAllUser()).thenReturn(users);

        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void findAllUserIsEmpty()throws Exception{


        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/user/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertEquals(HttpStatus.NO_CONTENT.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test
    public void getPhoneLineById()throws Exception{

        when(userService.findByCode(1)).thenReturn(aUser());

        final ResultActions resultActions=givenController().perform(MockMvcRequestBuilders
                .get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }





}
