package com.matcodem.flightmanagement.application.controllers;

import com.matcodem.flightmanagement.application.dto.BaseResponse;
import com.matcodem.flightmanagement.infrastructure.exceptions.AggregateNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice(basePackageClasses = FlightAdministrationController.class)
public class FlightAdministrationExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Client made a bad request - {}", ex.toString(), ex);
        return ResponseEntity.badRequest().body(new BaseResponse(ex.getMessage()));
    }

    @ExceptionHandler({IllegalStateException.class, AggregateNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BaseResponse> handleIllegalStateException(Exception ex) {
        log.warn("Client made a bad request - {}", ex.toString(), ex);
        return ResponseEntity.badRequest().body(new BaseResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse> handleException(Exception ex) {
        log.error("Internal server error - {}", ex.toString(), ex);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new BaseResponse("Internal server error occurred."));
    }
}
