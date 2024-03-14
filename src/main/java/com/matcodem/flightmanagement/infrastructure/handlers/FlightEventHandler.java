package com.matcodem.flightmanagement.infrastructure.handlers;

import com.matcodem.flightmanagement.application.events.FlightCanceledEvent;
import com.matcodem.flightmanagement.application.events.FlightCreatedEvent;
import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import com.matcodem.flightmanagement.domain.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightEventHandler implements EventHandler {

    private final FlightRepository flightRepository;

    @Override
    public void on(FlightCreatedEvent event) {
        FlightEntity flight = FlightEntity.builder()
                .designatorCode(event.getDesignatorCode())
                .arrivalIcaoCode(event.getArrivalIcaoCode())
                .departureIcaoCode(event.getDepartureIcaoCode())
                .cabins(event.getCabins())
                .departureDateTime(event.getDepartureDateTime())
                .callSign(event.getCallSign())
                .durationMinutes(event.getDurationMinutes())
                .build();
        flightRepository.save(flight);
    }

    @Override
    public void on(FlightCanceledEvent event) {
        flightRepository.deleteById(event.getId());
    }
}
