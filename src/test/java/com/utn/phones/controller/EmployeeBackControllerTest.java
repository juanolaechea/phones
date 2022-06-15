package com.utn.phones.controller;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.controller.BackController.EmployeeBackController;
import com.utn.phones.domain.Employee;
import com.utn.phones.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.utn.phones.Utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeBackController.class)
public class EmployeeBackControllerTest extends Abstrascttest {

    private EmployeeBackController employeeBackController;


    @MockBean
    EmployeeService employeeService;

    @Test //ok
    public void addEmployee() throws Exception {

        when(employeeService.addEmployee(aEmployee())).thenReturn(PostResponse.builder().httpStatus(HttpStatus.CREATED).build());

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/employee/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aEmployeeJson()))
                .andExpect(status().isCreated());


        assertEquals(HttpStatus.CREATED.value(), resultActions.andReturn()
                .getResponse()
                .getStatus(), "Is should be 201");
    }

    @Test //ok
    public void addEmployeeBadRequest() throws Exception {

        Employee employee = new Employee();

        when(employeeService.addEmployee(employee)).thenReturn(PostResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).build());

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .post("/api/employee/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(), resultActions.andReturn()
                .getResponse()
                .getStatus(), "Is should be 201");
    }

    @Test //ok
    public void getAll() throws Exception{
        List<Employee> employees = new ArrayList<>();
        employees.add(aEmployee());
        when(employeeService.getAll()).thenReturn(employees);
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/employee/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(HttpStatus.OK.value(),resultActions.andReturn().getResponse().getStatus());

    }
    @Test //ok
    public void getAllEmpty() throws Exception{
        List<Employee> employees = new ArrayList<>();

        when(employeeService.getAll()).thenReturn(employees);
        final  ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/employee/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        assertEquals(HttpStatus.NO_CONTENT.value(),resultActions.andReturn().getResponse().getStatus());

    }

    @Test //ok
    public void getById()throws Exception{
        when(employeeService.getById(1)).thenReturn(aEmployee());
        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(), resultActions.andReturn().getResponse().getStatus());
    }
    @Test //ok
    public void getByIdBadRequest()throws Exception{


        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .get("/api/employee/A")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(), resultActions.andReturn().getResponse().getStatus());
    }

    @Test //ok
    public void putUserInEmployee()throws Exception{

        when(employeeService.putUserInEmployee(aEmployee().getIdEmployee(), aUser().getIdUser()))
                .thenReturn(PostResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .build());

        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/employee/1/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aEmployeeJson()).content(aUserJson()))
                .andExpect(status().isOk());

        assertEquals(HttpStatus.OK.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }

    @Test //ok
    public void putUserInEmployeeBadRequest()throws Exception{


        final ResultActions resultActions = givenController().perform(MockMvcRequestBuilders
                        .put("/api/employee/1/user/a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(aEmployeeJson()).content(aUserJson()))
                .andExpect(status().isBadRequest());

        assertEquals(HttpStatus.BAD_REQUEST.value(),resultActions.andReturn()
                .getResponse()
                .getStatus(),"Is should be 200");

    }

}
