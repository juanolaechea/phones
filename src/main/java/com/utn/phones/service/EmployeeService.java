package com.utn.phones.service;

import com.utn.phones.Utils.PostResponse;
import com.utn.phones.domain.Employee;
import com.utn.phones.domain.User;
import com.utn.phones.persistence.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;
import static com.utn.phones.Utils.EntityURLBuilder.buildURL;
import static com.utn.phones.constants.ControllerConstants.URL_EMPLOYEE;


@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    UserService userService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }

    public PostResponse addEmployee(Employee employee) {

        Employee e= employeeRepository.save(employee);
        return PostResponse.builder()
                .httpStatus(HttpStatus.CREATED)
                .link(buildURL(URL_EMPLOYEE, e.getIdEmployee().toString()))
                .build();
    }

    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public Employee getById(Integer idEmployee) {
        return this.employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Employee not Exists "));
    }

    public PostResponse putUserInEmployee(Integer idEmployee, Integer idUser) {
        Employee e= employeeRepository.getById(idEmployee);
        User u = userService.findByCode(idUser);
        e.setUser(u);
        this.addEmployee(e);
        return PostResponse.builder()
                .httpStatus(HttpStatus.OK)
                .link(buildURL(URL_EMPLOYEE, e.getUser().getUsername()))
                .build();
    }
}
