package com.matcodem.flightmanagement.infrastructure;

import com.matcodem.flightmanagement.application.commands.BaseCommand;
import com.matcodem.flightmanagement.application.commands.handler.CommandHandlerMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link CommandDispatcher} specifically tailored for flight-related commands.
 * It manages the registration of command handlers and dispatches commands to their respective handlers for processing.
 */
@Service
public class FlightCommandDispatcher implements CommandDispatcher {

    /**
     * Mapping of command types to their corresponding handlers.
     */
    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    /**
     * Registers a handler for a specific type of command.
     *
     * @param <T>     The type of the command.
     * @param type    The class object representing the type of command.
     * @param handler The handler method for the command.
     */
    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, commandHandler -> new LinkedList<>());
        handlers.add(handler);
    }

    /**
     * Sends a command to its corresponding handler for processing.
     * Only one handler should be registered for a command type.
     *
     * @param command The command to be processed.
     * @throws RuntimeException if no handler is registered for the command or if more than one handler is registered.
     */
    @Override
    public void send(BaseCommand command) {
        var handlers = routes.get(command.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send command to more than one handler!");
        }
        handlers.get(0).handle(command);
    }
}
