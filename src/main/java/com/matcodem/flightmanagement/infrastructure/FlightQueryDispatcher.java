package com.matcodem.flightmanagement.infrastructure;

import com.matcodem.flightmanagement.application.queries.BaseQuery;
import com.matcodem.flightmanagement.application.queries.handler.QueryHandlerMethod;
import com.matcodem.flightmanagement.domain.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link QueryDispatcher} specifically tailored for flight-related queries.
 * It manages the registration of query handlers and dispatches queries to their respective handlers for processing.
 */
@Service
public class FlightQueryDispatcher implements QueryDispatcher {

    /**
     * Mapping of query types to their corresponding handlers.
     */
    private final Map<Class<? extends BaseQuery>, List<QueryHandlerMethod>> routes = new HashMap<>();

    /**
     * Registers a handler for a specific type of query.
     *
     * @param <T>     The type of the query.
     * @param type    The class object representing the type of query.
     * @param handler The handler method for the query.
     */
    @Override
    public <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler) {
        var handlers = routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    /**
     * Sends a query to its corresponding handler for processing.
     * Only one handler should be registered for a query type.
     *
     * @param query The query to be processed.
     * @param <U>   The type of the result returned by the handler.
     * @return The result of processing the query.
     * @throws RuntimeException if no handler is registered for the query or if more than one handler is registered.
     */
    @Override
    public <U extends BaseEntity> Page<U> send(BaseQuery query) {
        var handlers = routes.get(query.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No query handler was registered!");
        }
        if (handlers.size() > 1) {
            throw new RuntimeException("Cannot send query to more than one handler!");
        }
        return handlers.get(0).handle(query);
    }
}
