package com.matcodem.flightmanagement.application.commands.handler;

import com.matcodem.flightmanagement.application.commands.CancelFlightCommand;
import com.matcodem.flightmanagement.application.commands.CreateFlightCommand;

public interface CommandHandler {
    void handle(CreateFlightCommand command);
    void handle(CancelFlightCommand command);
}
