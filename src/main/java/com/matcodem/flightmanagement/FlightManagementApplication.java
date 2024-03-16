package com.matcodem.flightmanagement;

import com.matcodem.flightmanagement.application.commands.CancelFlightCommand;
import com.matcodem.flightmanagement.application.commands.CommandHandler;
import com.matcodem.flightmanagement.application.commands.CreateFlightCommand;
import com.matcodem.flightmanagement.application.queries.FindAllFlightsQuery;
import com.matcodem.flightmanagement.application.queries.QueryHandler;
import com.matcodem.flightmanagement.infrastructure.CommandDispatcher;
import com.matcodem.flightmanagement.infrastructure.QueryDispatcher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class FlightManagementApplication {

    private final CommandDispatcher commandDispatcher;
    private final QueryDispatcher queryDispatcher;
    private final CommandHandler commandHandler;
    private final QueryHandler queryHandler;

    public static void main(String[] args) {
        SpringApplication.run(FlightManagementApplication.class, args);
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateFlightCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(CancelFlightCommand.class, commandHandler::handle);
        queryDispatcher.registerHandler(FindAllFlightsQuery.class, queryHandler::handle);
    }
}
