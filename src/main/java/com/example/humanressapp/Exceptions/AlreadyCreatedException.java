package com.example.humanressapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyCreatedException extends RuntimeException{
    public AlreadyCreatedException(String message) {
        super(message);
    }
}
