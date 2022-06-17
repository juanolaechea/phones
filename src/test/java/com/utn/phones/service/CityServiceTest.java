package com.utn.phones.service;

import com.utn.phones.domain.City;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.persistence.CityRepository;
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
import java.util.Optional;

import static com.utn.phones.Utils.TestUtils.aCity;
import static com.utn.phones.Utils.TestUtils.aPhoneLine;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityServiceTest {

    @InjectMocks
    CityService cityService;

    @Mock
    CityRepository cityRepository;

    @Test
    public void findAll(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<City> cities= new ArrayList<>();
        cities.add(aCity());

        final List<City> response = cityService.findAll();
        assertNotNull(response);
    }
    @Test
    public void findById(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Integer id= 1;
        Mockito.when(cityRepository.findById(id)).thenReturn(Optional.of(aCity()));

        final City response = cityService.findById(id);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void findByCode(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String code= "223";
        Mockito.when(cityRepository.findByCode(code)).thenReturn(aCity());

        final City response = cityService.findByCode(code);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void getCodeByNumber(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        String number= "2236688026";
        Mockito.when(cityRepository.getCodeByNumber(number)).thenReturn(aCity());

        final City response = cityService.getCodeByNumber(number);
        assertNotNull(response, "Should be not null.");
    }


}
