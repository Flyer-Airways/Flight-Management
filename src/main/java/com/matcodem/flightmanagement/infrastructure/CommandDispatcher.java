package com.matcodem.flightmanagement.infrastructure;

import com.matcodem.flightmanagement.application.commands.BaseCommand;
import com.matcodem.flightmanagement.application.commands.handler.CommandHandlerMethod;

/**
 * Interface representing a command dispatcher responsible for routing commands to their respective handlers.
 * Commands are used to encapsulate a request for action, and the dispatcher ensures that the appropriate handler
 * processes each command.
 */
public interface CommandDispatcher {

    /**
     * Registers a handler for a specific type of command.
     *
     * @param <T>     The type of the command.
     * @param type    The class object representing the type of command.
     * @param handler The handler method for the command.
     */
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    /**
     * Sends a command to the appropriate handler for processing.
     *
     * @param command The command to be processed.
     */
    void send(BaseCommand command);
}
