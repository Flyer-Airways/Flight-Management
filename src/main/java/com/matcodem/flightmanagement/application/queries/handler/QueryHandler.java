package com.matcodem.flightmanagement.application.queries.handler;

import com.matcodem.flightmanagement.application.queries.FindAllFlightsQuery;
import com.matcodem.flightmanagement.application.queries.SearchFlightQuery;
import com.matcodem.flightmanagement.domain.entity.BaseEntity;
import org.springframework.data.domain.Page;

/**
 * Interface for handling various query types related to flight management.
 */
public interface QueryHandler {

    /**
     * Handles the FindAllFlightsQuery to retrieve all flights.
     *
     * @param query The FindAllFlightsQuery instance to handle.
     * @return A Page containing BaseEntity instances representing the result of the query.
     */
    Page<BaseEntity> handle(FindAllFlightsQuery query);

    /**
     * Handles the SearchFlightQuery to search for flights based on specified criteria.
     *
     * @param query The SearchFlightQuery instance to handle.
     * @return A Page containing BaseEntity instances representing the result of the query.
     */
    Page<BaseEntity> handle(SearchFlightQuery query);

}
