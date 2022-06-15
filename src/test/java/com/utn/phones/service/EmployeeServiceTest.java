package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.City;
import com.utn.phones.domain.Client;
import com.utn.phones.domain.Employee;
import com.utn.phones.domain.User;
import com.utn.phones.dto.ClientDto;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.persistence.EmployeeRepository;
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

import javax.validation.constraints.Null;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.utn.phones.Utils.TestUtils.*;
import static com.utn.phones.Utils.TestUtils.aCity;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class EmployeeServiceTest {



    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    UserService userService;


    @Test
    public void addEmployee() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final Employee aEmployee= aEmployee();
        final Employee aEmployeeSaved =aEmployee();
        aEmployeeSaved.setIdEmployee(1);

        Mockito.when(employeeRepository.save(aEmployee)).thenReturn(aEmployeeSaved);

        final PostResponse response = employeeService.addEmployee(aEmployee);
        assertNotNull(response, "Should be not null.");
    }

    @Test
    public void addEmployeeBarRequest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final Employee aEmployee= null;

        Mockito.when(employeeRepository.save(aEmployee)).thenReturn(aEmployee);

        final HttpStatus response = BAD_REQUEST;
        assertNotNull(response, "Should be not null.");
    }

    @Test
    public void getAll(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        List<Employee> employees = new ArrayList<>();
        employees.add(aEmployee());
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        final List<Employee> response = employeeService.getAll();
        assertNotNull(response);

    }
    @Test
    public void getById() throws ElementDoesNotExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Integer id= 1;
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(aEmployee()));

        final Employee response = employeeService.getById(id);
        assertNotNull(response, "Should be not null.");

    }



    @Test
    public void getByIdBadRequest() throws ElementDoesNotExistsException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Integer id= null;
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(aEmployee()));

        final HttpStatus response = BAD_REQUEST;
        assertNotNull(response, "Should be not null.");

    }


    @Test
    public void putUserInEmployee() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Employee e = aEmployee();
        User u= aUser();

        Mockito.when(employeeRepository.getById(e.getIdEmployee())).thenReturn(aEmployee());
        Mockito.when(userService.findByCode(u.getIdUser())).thenReturn(aUser());
        e.setUser(u);
        Mockito.when(employeeRepository.save(e)).thenReturn(e);

        final PostResponse response = employeeService.putUserInEmployee(e.getIdEmployee(),u.getIdUser());
        assertNotNull(response);

    }

}
