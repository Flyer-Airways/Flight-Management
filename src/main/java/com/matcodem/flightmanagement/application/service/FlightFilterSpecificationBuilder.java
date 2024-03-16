package com.matcodem.flightmanagement.application.service;

import com.matcodem.flightmanagement.application.controllers.params.FlightSearchFilter;
import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import com.matcodem.flightmanagement.domain.entity.FlightEntity_;
import com.matcodem.flightmanagement.infrastructure.validator.IcaoCodeValidator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * A builder class for constructing specifications to filter flight entities based on search criteria.
 */
@Component
public class FlightFilterSpecificationBuilder {

    /**
     * Builds a JPA Specification for filtering flight entities based on the provided search filter.
     *
     * @param filter The flight search filter containing the criteria for filtering flights.
     * @return A Specification object representing the filtering criteria.
     */
    public Specification<FlightEntity> buildFlightSpecification(FlightSearchFilter filter) {
        Specification<FlightEntity> specification = Specification.where(null);

        if (isNotEmpty(filter.departureIcao())) {
            validateIcaoCode(filter.departureIcao());
            specification = specification.and((root, query, cb) -> cb.equal(root.get(FlightEntity_.departureIcaoCode), filter.departureIcao()));
        }

        if (isNotEmpty(filter.arrivalIcao())) {
            validateIcaoCode(filter.arrivalIcao());
            specification = specification.and((root, query, cb) -> cb.equal(root.get(FlightEntity_.arrivalIcaoCode), filter.arrivalIcao()));
        }

        if (Objects.nonNull(filter.departureDate())) {
            LocalDateTime departureDate = filter.departureDate().atStartOfDay();
            LocalDateTime nextDay = departureDate.plusDays(1).withHour(2).withMinute(0).withSecond(0);

            specification = specification.and((root, query, cb) -> cb.and(
                    cb.greaterThanOrEqualTo(root.get(FlightEntity_.departureDateTime), departureDate),
                    cb.lessThan(root.get(FlightEntity_.departureDateTime), nextDay)
            ));
        }

        if (isRoundTrip(filter)) {
            specification = buildRoundTripSpecification(filter, specification);
        }
        return specification;
    }

    /**
     * Checks if the flight search filter represents a round-trip journey.
     *
     * @param filter The flight search filter to check.
     * @return true if the filter represents a round-trip journey, otherwise false.
     */
    private boolean isRoundTrip(FlightSearchFilter filter) {
        return filter.returnDate() != null &&
                filter.departureIcao() != null &&
                filter.arrivalIcao() != null &&
                filter.departureDate() != null;
    }

    /**
     * Builds a Specification for filtering round-trip flights based on the provided search filter.
     *
     * @param filter       The flight search filter containing the criteria for filtering flights.
     * @param specification The base specification to extend with round-trip criteria.
     * @return A Specification object representing the round-trip filtering criteria.
     */
    private Specification<FlightEntity> buildRoundTripSpecification(FlightSearchFilter filter, Specification<FlightEntity> specification) {
        FlightSearchFilter returnFilter = FlightSearchFilter.builder()
                .departureDate(filter.returnDate())
                .departureIcao(filter.arrivalIcao())
                .arrivalIcao(filter.departureIcao())
                .returnDate(null)
                .build();

        return Specification.where(specification).or(buildFlightSpecification(returnFilter));
    }

    /**
     * Validates the format of the ICAO code.
     *
     * @param icaoCode The ICAO code to validate.
     * @throws IllegalArgumentException if the ICAO code format is not correct.
     */
    private void validateIcaoCode(String icaoCode) {
        if (!IcaoCodeValidator.isValidIcaoCode(icaoCode)) {
            throw new IllegalArgumentException("ICAO code is not correct.");
        }
    }
}
