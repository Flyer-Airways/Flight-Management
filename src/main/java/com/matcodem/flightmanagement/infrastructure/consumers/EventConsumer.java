package com.matcodem.flightmanagement.infrastructure.consumers;

import com.matcodem.flightmanagement.application.events.FlightCreatedEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {

    void consume(@Payload FlightCreatedEvent event, Acknowledgment ack);
}
