package com.matcodem.flightmanagement.infrastructure.producers;

import com.matcodem.flightmanagement.application.events.BaseEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightEventProducer implements EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topicName, BaseEvent event) {
        kafkaTemplate.send(topicName, event);
        log.info("Sending {} event to the {}", event, topicName);
    }
}
