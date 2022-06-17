package com.utn.phones.controller.BackController;


import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Employee;
import com.utn.phones.exceptions.BadRequestException;
import com.utn.phones.exceptions.DeauthorizedException;
import com.utn.phones.exceptions.ElementDoesNotExistsException;
import com.utn.phones.exceptions.ElementExistsException;
import com.utn.phones.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utn.phones.constants.ControllerConstants.*;


@Controller
@RestController
@RequestMapping(BASE_URL)
public class EmployeeBackController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeBackController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(URL_EMPLOYEE)
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse addEmployee(@RequestBody Employee employee)  throws DeauthorizedException, ElementExistsException
    {
        return employeeService.addEmployee(employee);
    }

    @GetMapping(path = URL_EMPLOYEE +"/")
    public ResponseEntity<List<Employee>> getAll( )throws DeauthorizedException  {
        List<Employee>employees = this.employeeService.getAll();
        return employees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(employees);
    }

    @GetMapping(path = URL_EMPLOYEE+ "/{idEmployee}")
    public Employee getById(@PathVariable("idEmployee") Integer idEmployee)throws DeauthorizedException,ElementDoesNotExistsException{
        return this.employeeService.getById(idEmployee);
    }

    @PutMapping(path = URL_EMPLOYEE + "/{idEmployee}" + URl_USER + "/{idUser}")
    public PostResponse putUserInEmployee(@PathVariable("idEmployee") Integer idEmployee, @PathVariable("idUser") Integer idUser) throws DeauthorizedException,ElementDoesNotExistsException, BadRequestException {
        return this.employeeService.putUserInEmployee(idEmployee, idUser);
    }
}
