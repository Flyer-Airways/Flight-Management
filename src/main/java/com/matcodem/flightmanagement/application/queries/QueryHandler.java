package com.matcodem.flightmanagement.application.queries;

import com.matcodem.flightmanagement.domain.entity.BaseEntity;

import java.util.List;

public interface QueryHandler {

    List<BaseEntity> handle(FindAllFlightsQuery query);

}
