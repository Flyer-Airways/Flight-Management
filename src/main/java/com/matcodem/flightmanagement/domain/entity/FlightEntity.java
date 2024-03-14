package com.matcodem.flightmanagement.domain.entity;

import com.matcodem.flightmanagement.domain.CabinClass;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "flights")
public class FlightEntity extends BaseEntity {

    @Id
    @Column(name = "designator_code")
    private String designatorCode;

    /**
     * The airport where the flight arrives.
     */
    @Column(name = "arrival_icao_code")
    private String arrivalIcaoCode;

    /**
     * The airport where the flight departs from.
     */
    @Column(name = "departure_icao_code")
    private String departureIcaoCode;

    /**
     * The cabin class of the flight.
     */
    @Column(name = "cabins")
    @Enumerated(EnumType.STRING)
    private Set<CabinClass> cabins;

    /**
     * The departure date and time of the flight.
     */
    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    /**
     * The aircraft operating the flight.
     */
    @Column(name = "call_sign")
    private Long callSign;

    /**
     * The duration of the flight in minutes.
     */
    @Column(name = "duration_minutes")
    private Integer durationMinutes;
}

