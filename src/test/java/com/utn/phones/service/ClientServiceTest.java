package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.*;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utn.phones.Utils.TestUtils.aCity;
import static com.utn.phones.Utils.TestUtils.aClient;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;


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

        final Client aClient= aClient();
        final Client aClientSaved =aClient();
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
    public void findByCode() throws ElementDoesNotExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Integer id= 1;
        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(aClient()));

        final Client response = clientService.findByCode(id);
        assertNotNull(response, "Should be not null.");

    }
    @Test
    public void findByCodeBadRequeste(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes((new ServletRequestAttributes(request)));
        Integer id=null;
        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(aClient()));
        final HttpStatus response = BAD_REQUEST;
        assertNotNull(response, "Should be not null.");

    }

    @Test
    public void findAllClient(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Client> clients = new ArrayList<>();
        clients.add(aClient());
        Mockito.when(clientRepository.findAll()).thenReturn(clients);

        final List<Client> response = clientService.findAllClient();
        assertNotNull(response);

    }


    @Test
    public void putCityInUser() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Client cl = aClient();
        City c= aCity();


        Mockito.when(clientRepository.getById(cl.getIdClient())).thenReturn(aClient());
        Mockito.when(cityRepository.getById(c.getIdCity())).thenReturn(aCity());
        cl.setCity(c);
        Mockito.when(clientRepository.save(cl)).thenReturn(cl);

        final PostResponse response = clientService.putCityInUser(cl.getIdClient(),c.getIdCity());
        assertNotNull(response);

    }

    @Test
    public void deleteClient() throws ElementDoesNotExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Integer id=1;
        Mockito.when(clientRepository.existsById(1)).thenReturn(true);
        PostResponse reaponse =clientService.deleteClient(id);

        final PostResponse response = clientService.deleteClient(id);
        assertEquals(OK,response.getHttpStatus());

    }












}
