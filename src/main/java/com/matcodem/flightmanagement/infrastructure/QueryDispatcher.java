package com.matcodem.flightmanagement.infrastructure;

import com.matcodem.flightmanagement.application.queries.BaseQuery;
import com.matcodem.flightmanagement.application.queries.QueryHandlerMethod;
import com.matcodem.flightmanagement.domain.entity.BaseEntity;

import java.util.List;

/**
 * Interface representing a query dispatcher responsible for routing queries to their respective handlers.
 * Queries are used to retrieve information from the system, and the dispatcher ensures that the appropriate handler
 * processes each query and returns the result.
 */
public interface QueryDispatcher {

    /**
     * Registers a handler for a specific type of query.
     *
     * @param <T>     The type of the query.
     * @param type    The class object representing the type of query.
     * @param handler The handler method for the query.
     */
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    /**
     * Sends a query to its corresponding handler for processing.
     *
     * @param query The query to be processed.
     * @param <U>   The type of the result returned by the handler.
     * @return The result of processing the query.
     */
    <U extends BaseEntity> List<U> send(BaseQuery query);
}
