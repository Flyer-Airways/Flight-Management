package com.matcodem.flightmanagement.infrastructure.producers;

import com.matcodem.flightmanagement.application.events.BaseEvent;

@FunctionalInterface
public interface EventProducer {
    void produce(String topicName, BaseEvent event);
}
