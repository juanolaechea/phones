package com.utn.phones.controller;

import com.utn.phones.service.JwtService.JwtService;
import com.utn.phones.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UserController.class)
public class UserControllerTest extends Abstrascttest{

    @MockBean
    UserService userService;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    JwtService jwtService;

    @Test
    public void addUser()throws Exception{

        when(userService.addUser(aUser())).thenReturn(ResponseEntity.ok(aUser()));

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aUserJson()))
                .andExpect(status().isOk());


        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 201");
    }



}
