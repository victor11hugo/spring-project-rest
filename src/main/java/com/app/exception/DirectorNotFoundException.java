package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DirectorNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public DirectorNotFoundException(String message){
        super(message);
    }
}