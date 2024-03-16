package com.matcodem.flightmanagement.application.controllers.params;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FlightSearchFilter(String departureIcao,
                                 String arrivalIcao,
                                 LocalDate departureDate,
                                 LocalDate returnDate) {
}