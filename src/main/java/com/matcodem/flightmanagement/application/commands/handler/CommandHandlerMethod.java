package com.matcodem.flightmanagement.application.commands.handler;

import com.matcodem.flightmanagement.application.commands.BaseCommand;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
