package com.matcodem.flightmanagement.infrastructure.handlers;

import com.matcodem.flightmanagement.domain.aggregate.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);
    T getById(String id);
    void republishEvents();
}
