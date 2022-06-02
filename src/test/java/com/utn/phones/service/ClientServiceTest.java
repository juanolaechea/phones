package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.*;
import com.utn.phones.persistence.CityRepository;
import com.utn.phones.persistence.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientServiceTest {


    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    CityRepository cityRepository;

    @Test
    public void addClient() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final Client aClient= this.aClient();
        final Client aClientSaved = this.aClient();
        aClientSaved.setIdClient(1);

        Mockito.when(clientRepository.save(aClient)).thenReturn(aClientSaved);

        final PostResponse response = clientService.addClient(aClient);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void addClientBadRequest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final Client aClient= null;

        Mockito.when(clientRepository.save(aClient)).thenReturn(aClient);

        final HttpStatus response = BAD_REQUEST;
        assertNotNull(response, "Should be not null.");
    }

    @Test
    public void findByCode(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Integer id= 1;
        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(aClient()));

        final Client response = clientService.findByCode(id);
        assertNotNull(response, "Should be not null.");

    }



    public static Client aClient() {
        return new Client(1,"juan","OLaecvhea",12345, UserType.valueOf("client"),new City(),new User(),new PhoneLine());

    }




}
