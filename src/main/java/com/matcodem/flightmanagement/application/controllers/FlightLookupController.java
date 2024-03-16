package com.matcodem.flightmanagement.application.controllers;

import com.matcodem.flightmanagement.application.controllers.params.FlightSearchFilter;
import com.matcodem.flightmanagement.application.controllers.params.PageRequestParams;
import com.matcodem.flightmanagement.application.dto.FlightLookupResponse;
import com.matcodem.flightmanagement.application.queries.FindAllFlightsQuery;
import com.matcodem.flightmanagement.application.queries.SearchFlightQuery;
import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import com.matcodem.flightmanagement.infrastructure.QueryDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/flights")
public class FlightLookupController {

    private final QueryDispatcher queryDispatcher;

    @GetMapping
    public ResponseEntity<FlightLookupResponse> getAllFlights(@ModelAttribute PageRequestParams pageRequestParams) {
        try {
            Page<FlightEntity> flights = queryDispatcher.send(new FindAllFlightsQuery(pageRequestParams));
            if (flights.isEmpty()) {
                return status(NOT_FOUND).body(new FlightLookupResponse("No available flights found!"));
            }
            return ok(new FlightLookupResponse("Successfully retrieved all flights!", flights));
        } catch (Exception e) {
            log.error("Failed to complete get all flights request!", e);
            return status(INTERNAL_SERVER_ERROR).body(new FlightLookupResponse("Failed to complete get all flights request!"));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<FlightLookupResponse> searchFlights(@ModelAttribute FlightSearchFilter searchFilter, @ModelAttribute PageRequestParams pageRequestParams) {
        try {
            Page<FlightEntity> flights = queryDispatcher.send(new SearchFlightQuery(searchFilter, pageRequestParams));
            if (flights.isEmpty()) {
                return status(NOT_FOUND).body(new FlightLookupResponse("No flights available matching the search criteria!"));
            }
            return ok(new FlightLookupResponse("Successfully retrieved flights!", flights));
        } catch (Exception e) {
            log.error("Failed to complete get flights request!", e);
            return status(INTERNAL_SERVER_ERROR).body(new FlightLookupResponse("Failed to complete get flights request!"));
        }
    }
}
