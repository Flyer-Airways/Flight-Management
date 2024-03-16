package com.matcodem.flightmanagement.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of FlightNotFoundException with a custom error message and a cause.
     *
     * @param message The custom error message.
     * @param cause   The cause of the exception.
     */
    public FlightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
