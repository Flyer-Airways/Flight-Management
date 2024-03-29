package com.matcodem.flightmanagement.infrastructure.handlers;

import com.matcodem.flightmanagement.application.events.FlightCanceledEvent;
import com.matcodem.flightmanagement.application.events.FlightCreatedEvent;

public interface EventHandler {
    void on(FlightCreatedEvent event);
    void on(FlightCanceledEvent event);
}
