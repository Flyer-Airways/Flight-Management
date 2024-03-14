package com.matcodem.flightmanagement.application.queries;

import com.matcodem.flightmanagement.domain.entity.BaseEntity;
import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import com.matcodem.flightmanagement.domain.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightQueryHandler implements QueryHandler {

    private final FlightRepository flightRepository;

    @Override
    public List<BaseEntity> handle(FindAllFlightsQuery query) {
        List<FlightEntity> flights = flightRepository.findAll();
        return new ArrayList<>(flights);
    }
}
