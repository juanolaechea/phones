package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.User;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.UserRepository;
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

import static com.utn.phones.Utils.TestUtils.aClient;
import static com.utn.phones.Utils.TestUtils.aUser;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {


    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void addUser() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final User aUser= aUser();
        final User aUserSaved =aUser();
        aUserSaved.setIdUser(1);

        Mockito.when(userRepository.save(aUser)).thenReturn(aUserSaved);

        final PostResponse response = userService.addUser(aUser);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void getAllUser(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<User> users = new ArrayList<>();
        users.add(aUser());
        Mockito.when(userRepository.findAll()).thenReturn(users);

        final List<User> response = userService.getAllUser();
        assertNotNull(response);

    }
    @Test
    public void findByCode()  {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Integer id= 1;
        Mockito.when(userRepository.getById(id)).thenReturn(aUser());

        final User response = userService.findByCode(id);
        assertNotNull(response, "Should be not null.");

    }
}
