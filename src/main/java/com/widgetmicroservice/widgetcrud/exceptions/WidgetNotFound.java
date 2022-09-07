package com.widgetmicroservice.widgetcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST)
public class WidgetNotFound extends IllegalStateException {
    public WidgetNotFound(String message) {
        super(message);
    }
}
