package com.widgetmicroservice.widgetcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST)
public class NoWidgetsFound extends IllegalStateException {
    public NoWidgetsFound(String message) {
        super(message);
    }
}
