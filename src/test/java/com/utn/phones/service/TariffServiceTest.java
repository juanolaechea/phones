package com.utn.phones.service;


import com.utn.phones.domain.City;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.Tariff;
import com.utn.phones.persistence.TariffRepository;
import org.h2.command.dml.MergeUsing;
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

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TariffServiceTest {

    @InjectMocks
    private TariffService tariffService;
    @Mock
    private TariffRepository tariffRepository;
    @Mock
    CityService cityService;

    @Test
    public void getAllTariff(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Tariff> tariffs= new ArrayList<>();
        tariffs.add(aTariff());

        final List<Tariff> response = tariffService.getAllTariff();
        assertNotNull(response);
    }


}
