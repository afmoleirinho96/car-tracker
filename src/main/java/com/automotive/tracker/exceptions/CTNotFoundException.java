package com.automotive.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CTNotFoundException extends RuntimeException {

    public CTNotFoundException(final String message, final Throwable error) {
        super(message, error);
    }

    public CTNotFoundException(final String message) {
        super(message);
    }

}
