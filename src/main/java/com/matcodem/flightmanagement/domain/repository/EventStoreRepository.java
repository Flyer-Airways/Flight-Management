package com.matcodem.flightmanagement.domain.repository;

import com.matcodem.flightmanagement.application.events.EventModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventStoreRepository extends MongoRepository<EventModel, String> {

    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
}
