package com.matcodem.flightmanagement.application.commands.handler;

import com.matcodem.flightmanagement.application.commands.CancelFlightCommand;
import com.matcodem.flightmanagement.application.commands.CreateFlightCommand;
import com.matcodem.flightmanagement.domain.aggregate.FlightAggregate;
import com.matcodem.flightmanagement.infrastructure.handlers.EventSourcingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightCommandHandler implements CommandHandler {

    private final EventSourcingHandler<FlightAggregate> eventSourcingHandler;
    @Override
    public void handle(CreateFlightCommand command) {
        FlightAggregate flightAggregate = new FlightAggregate(command);
        eventSourcingHandler.save(flightAggregate);
    }

    @Override
    public void handle(CancelFlightCommand command) {
        FlightAggregate aggregate = eventSourcingHandler.getById(command.getId());
        aggregate.cancelFlight();
        eventSourcingHandler.save(aggregate);
    }
}
