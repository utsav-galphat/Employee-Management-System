package org.utech.employeemanagementsystem.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.utech.employeemanagementsystem.exception.EmployeeNotFoundException;
import org.utech.employeemanagementsystem.exception.ExceptionDto;

@ControllerAdvice
@Slf4j
public class EmployeeControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleEmployeeNotFoundException(EmployeeNotFoundException exception){
        ExceptionDto exceptionDto =  new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        exceptionDto.setStatus("Failure");
        return exceptionDto;
    }
}
