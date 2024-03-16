package com.matcodem.flightmanagement.application.queries;

import com.matcodem.flightmanagement.application.controllers.params.FlightSearchFilter;
import com.matcodem.flightmanagement.application.controllers.params.PageRequestParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class SearchFlightQuery extends BaseQuery {
    private FlightSearchFilter searchFilter;
    private PageRequestParams pageRequestParams;
}
