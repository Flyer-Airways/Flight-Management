package com.matcodem.flightmanagement.application.dto;

import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class FlightLookupResponse extends BaseResponse {
    private List<FlightEntity> flights;

    public FlightLookupResponse(String message) {
        super(message);
    }
}
