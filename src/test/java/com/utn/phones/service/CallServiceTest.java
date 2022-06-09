package com.utn.phones.service;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Client;
import com.utn.phones.persistence.CallRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.utn.phones.Utils.TestUtils.aClient;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CallServiceTest {

    @InjectMocks
    CallService callService;

    @Mock
     CallRepository callRepository;
    @Mock
     CityService cityService;
    @Mock
     PhoneLineService phoneLineService;
    @Mock
     TariffService tariffService;
    @Mock
     ClientService clientService;

    /*
    @Test
    public void newCall() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Mockito.when(clientService.addClient(aClient())).thenReturn(PostResponse.builder().build());
    }

     */

}
