package com.utn.phones.controller;


import com.utn.phones.controller.BackController.ClientBackController;
import com.utn.phones.controller.WebController.ClientWebController;
import com.utn.phones.dto.BillDto;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.service.ClientService;
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


import static com.utn.phones.Utils.TestUtils.aClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ClientWebController.class)
public class ClientWebControllerTest extends Abstrascttest {

    @MockBean
    ClientService clientService;




}
