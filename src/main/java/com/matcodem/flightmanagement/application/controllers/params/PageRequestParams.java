package com.matcodem.flightmanagement.application.controllers.params;

import com.matcodem.flightmanagement.domain.entity.FlightEntity_;
import lombok.Getter;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Getter
public class PageRequestParams {
    private final int page;
    private final int size;
    private final String sortBy;
    private final String sortOrder;

    public PageRequestParams() {
        this.page = 0;
        this.size = 10;
        this.sortBy = FlightEntity_.DEPARTURE_DATE_TIME;
        this.sortOrder = ASC.name();
    }
}
