package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.City;
import com.utn.phones.domain.PhoneLine;
import com.utn.phones.domain.User;
import com.utn.phones.domain.UserType;
import com.utn.phones.persistence.CityRepository;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {


    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    CityRepository cityRepository;

    @Test
    public void addClient() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final User aUser = this.aUser();
        final User aUserSaved = this.aUser();
        aUserSaved.setIdUser(1);

        Mockito.when(userRepository.save(aUser)).thenReturn(aUserSaved);

        final PostResponse response = userService.addClient(aUser);
        assertNotNull(response, "Should be not null.");
    }
    @Test
    public void addClientBadRequest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final User aUser = null;

        Mockito.when(userRepository.save(aUser)).thenReturn(aUser);

        final HttpStatus response = BAD_REQUEST;
        assertNotNull(response, "Should be not null.");
    }



    public static User aUser() {
        return new User(1,"juan","OLaecvhea",12345,"1234",true, UserType.valueOf("client"),new City(),new PhoneLine());

    }




}
