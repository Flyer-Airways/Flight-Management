package com.matcodem.flightmanagement.application.events;

import com.matcodem.flightmanagement.domain.CabinClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FlightCreatedEvent extends BaseEvent {
    private String designatorCode;
    private String arrivalIcaoCode;
    private String departureIcaoCode;
    private Set<CabinClass> cabins;
    private LocalDateTime departureDateTime;
    private Long callSign;
    private Integer durationMinutes;
}
