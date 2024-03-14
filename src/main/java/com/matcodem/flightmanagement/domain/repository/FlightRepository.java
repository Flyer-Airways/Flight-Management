package com.matcodem.flightmanagement.domain.repository;

import com.matcodem.flightmanagement.domain.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity, String> {
}
