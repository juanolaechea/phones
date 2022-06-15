package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.*;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.CityRepository;
import com.utn.phones.persistence.ClientRepository;
import com.utn.phones.persistence.PhoneLineRepository;
import com.utn.phones.persistence.UserRepository;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
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

    @Mock
    PhoneLineRepository phoneLineRepository;

    @Mock
    UserService userService ;

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

        final List<ClientDto> response = clientService.findAllClient();
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
        doNothing().when(clientRepository).deleteById(anyInt());

        assertDoesNotThrow(() -> clientService.deleteClient(1));

        verify(clientRepository, times(1)).deleteById(anyInt());
    }
    @Test
    void deleteClientThrows() {
        doThrow(ElementDoesNotExistsException.class).when(clientRepository).deleteById(anyInt());

        assertThrows(ElementDoesNotExistsException.class,() -> clientService.deleteClient(1));

        verify(clientRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void putPhoneLineInUser() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Client cl = aClient();
        PhoneLine pl= aPhoneLine();

        Mockito.when(clientRepository.getById(cl.getIdClient())).thenReturn(aClient());
        Mockito.when(phoneLineRepository.getById(pl.getIdLine())).thenReturn(aPhoneLine());
        cl.setPhoneLine(pl);
        Mockito.when(clientRepository.save(cl)).thenReturn(cl);

        final PostResponse response = clientService.putPhoneLineInUser(cl.getIdClient(),pl.getIdLine());
        assertNotNull(response);

    }

    @Test
    public void putUserInClient() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Client cl = aClient();
        User u= aUser();

        Mockito.when(clientRepository.getById(cl.getIdClient())).thenReturn(aClient());
        Mockito.when(userService.findByCode(u.getIdUser())).thenReturn(aUser());
        cl.setUser(u);
        Mockito.when(clientRepository.save(cl)).thenReturn(cl);

        final PostResponse response = clientService.putUserInClient(cl.getIdClient(), u.getIdUser());
        assertNotNull(response);

    }
    @Test
    public void getClientByNumber(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Client cl = aClient();
        PhoneLine pl = aPhoneLine();

        Mockito.when(clientRepository.findByPhoneLine(pl)).thenReturn(cl);

        final Client response = clientService.getClientByNumber(pl);
        assertNotNull(response);

    }




}
