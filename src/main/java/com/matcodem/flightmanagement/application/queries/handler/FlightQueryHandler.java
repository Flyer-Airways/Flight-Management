package com.matcodem.flightmanagement.application.queries.handler;

import com.matcodem.flightmanagement.application.controllers.params.PageRequestParams;
import com.matcodem.flightmanagement.application.queries.FindAllFlightsQuery;
import com.matcodem.flightmanagement.application.queries.SearchFlightQuery;
import com.matcodem.flightmanagement.application.service.FlightFilterSpecificationBuilder;
import com.matcodem.flightmanagement.domain.entity.BaseEntity;
import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import com.matcodem.flightmanagement.domain.repository.FlightRepository;
import com.matcodem.flightmanagement.infrastructure.exceptions.FlightNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.by;

@Service
@RequiredArgsConstructor
public class FlightQueryHandler implements QueryHandler {

    public static final String ERROR_OCCURRED_WHILE_SEARCHING_FOR_FLIGHTS = "An error occurred while searching for flights.";

    private final FlightRepository flightRepository;
    private final FlightFilterSpecificationBuilder filterSpecificationBuilder;

    @Override
    public Page<BaseEntity> handle(FindAllFlightsQuery query) {
        PageRequest pageable = getPageRequest(query.getPageRequestParams());
        try {
            return flightRepository.findAll(pageable).map(flightEntity -> flightEntity);
        } catch (Exception e) {
            throw new FlightNotFoundException(ERROR_OCCURRED_WHILE_SEARCHING_FOR_FLIGHTS, e);
        }
    }

    @Override
    public Page<BaseEntity> handle(SearchFlightQuery query) {
        Specification<FlightEntity> specification = filterSpecificationBuilder.buildFlightSpecification(query.getSearchFilter());

        PageRequest pageable = getPageRequest(query.getPageRequestParams());
        try {
            return flightRepository.findAll(specification, pageable).map(flightEntity -> flightEntity);
        } catch (Exception e) {
            throw new FlightNotFoundException(ERROR_OCCURRED_WHILE_SEARCHING_FOR_FLIGHTS, e);
        }
    }

    private PageRequest getPageRequest(PageRequestParams pageParams) {
        Sort sorting = by(Direction.fromString(pageParams.getSortOrder()), pageParams.getSortBy());
        return PageRequest.of(pageParams.getPage(), pageParams.getSize(), sorting);
    }
}
