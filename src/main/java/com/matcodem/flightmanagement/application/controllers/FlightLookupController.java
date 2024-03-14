package com.matcodem.flightmanagement.application.controllers;

import com.matcodem.flightmanagement.application.dto.FlightLookupResponse;
import com.matcodem.flightmanagement.application.queries.FindAllFlightsQuery;
import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import com.matcodem.flightmanagement.infrastructure.QueryDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/flights")
public class FlightLookupController {

    private final QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<FlightLookupResponse> getAllFlights() {
        try {
            List<FlightEntity> flights = queryDispatcher.send(new FindAllFlightsQuery());
            if (flights == null || flights.isEmpty()) return new ResponseEntity<>(null, NO_CONTENT);
            var response = FlightLookupResponse.builder()
                    .message("Successfully retrieved all flights!")
                    .flights(flights)
                    .build();
            return new ResponseEntity<>(response, OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all flights request!";
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new FlightLookupResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
