package com.matcodem.flightmanagement.application.commands;

import com.matcodem.flightmanagement.domain.CabinClass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateFlightCommand extends BaseCommand {
    private String arrivalIcaoCode;
    private String departureIcaoCode;
    private Set<CabinClass> cabins;
    private LocalDateTime departureDateTime;
    private Long callSign;
    private Integer durationMinutes;
}
