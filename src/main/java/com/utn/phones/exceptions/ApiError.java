package com.utn.phones.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError extends Exception
{

    public HttpStatus httpStatus;
    private String message;
    private List<String> errors;
}