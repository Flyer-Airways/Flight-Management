package com.matcodem.flightmanagement.domain.aggregate;

import com.matcodem.flightmanagement.application.commands.CreateFlightCommand;
import com.matcodem.flightmanagement.application.events.FlightCreatedEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FlightAggregate extends AggregateRoot {

    public FlightAggregate(CreateFlightCommand command) {
        raiseEvent(FlightCreatedEvent.builder()
                .designatorCode(command.getId())
                .arrivalIcaoCode(command.getArrivalIcaoCode())
                .departureIcaoCode(command.getDepartureIcaoCode())
                .cabins(command.getCabins())
                .departureDateTime(command.getDepartureDateTime())
                .callSign(command.getCallSign())
                .durationMinutes(command.getDurationMinutes())
                .build());
    }

    public void apply(FlightCreatedEvent event) {
        this.id = event.getId();
    }
}
