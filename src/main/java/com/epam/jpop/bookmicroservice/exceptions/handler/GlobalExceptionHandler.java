package com.epam.jpop.bookmicroservice.exceptions.handler;

import com.epam.jpop.bookmicroservice.exceptions.NoObjectFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String exceptionHandler(Exception e) {
        return "A BIIIG EXCEPPTIONNNNNN!!!!!!!!!!!!";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException e) {
        return "No records found";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String NoObjectFoundExceptionHandler(NoObjectFoundException e) {
        return e.getLocalizedMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ValidatonException(ValidationException e) {
        return e.getMessage();
    }



}
