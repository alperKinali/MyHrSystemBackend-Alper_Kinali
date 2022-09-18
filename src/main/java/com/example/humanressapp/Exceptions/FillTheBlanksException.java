package com.example.humanressapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_EXTENDED)
public class FillTheBlanksException extends RuntimeException {
    public FillTheBlanksException(String message) {
        super(message);
    }
}
