package com.matcodem.flightmanagement.infrastructure.consumers;

import com.matcodem.flightmanagement.application.events.FlightCanceledEvent;
import com.matcodem.flightmanagement.application.events.FlightCreatedEvent;
import com.matcodem.flightmanagement.infrastructure.handlers.EventHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightEventConsumer implements EventConsumer {

    private final EventHandler eventHandler;

    @Override
    @KafkaListener(topics = "FlightCreatedEvent", groupId = "${kafka.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(FlightCreatedEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }

    @Override
    @KafkaListener(topics = "FlightCanceledEvent", groupId = "${kafka.group-id}")
    public void consume(FlightCanceledEvent event, Acknowledgment ack) {
        eventHandler.on(event);
        ack.acknowledge();
    }
}
