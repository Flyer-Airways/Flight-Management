package com.matcodem.flightmanagement.application.dto;

import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.domain.Page;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class FlightLookupResponse extends BaseResponse {
    private Page<FlightEntity> flights;

    public FlightLookupResponse(String message) {
        super(message);
    }

    public FlightLookupResponse(String message, Page<FlightEntity> flights) {
        super(message);
        this.flights = flights;
    }
}
