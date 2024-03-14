package com.matcodem.flightmanagement.application.commands;

public interface CommandHandler {
    void handle(CreateFlightCommand command);
    void handle(CancelFlightCommand command);
}
