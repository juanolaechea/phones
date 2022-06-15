package com.utn.phones.controller;


import com.utn.phones.controller.BackController.ClientWebController;
import com.utn.phones.service.ClientService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ClientWebController.class)
public class ClientWebControllerTest extends Abstrascttest {

    @MockBean
    ClientService clientService;




}
