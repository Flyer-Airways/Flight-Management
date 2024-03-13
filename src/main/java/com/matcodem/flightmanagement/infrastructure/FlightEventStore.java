package com.matcodem.flightmanagement.infrastructure;

import com.matcodem.flightmanagement.application.events.BaseEvent;
import com.matcodem.flightmanagement.application.events.EventModel;
import com.matcodem.flightmanagement.domain.EventStoreRepository;
import com.matcodem.flightmanagement.domain.FlightAggregate;
import com.matcodem.flightmanagement.infrastructure.exceptions.AggregateNotFoundException;
import com.matcodem.flightmanagement.infrastructure.exceptions.ConcurrencyException;
import com.matcodem.flightmanagement.infrastructure.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class FlightEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;
    private final EventProducer eventProducer;

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        int version = expectedVersion;
        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timeStamp(now())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(FlightAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            var persistedEvent = eventStoreRepository.save(eventModel);
            if (!persistedEvent.getId().isEmpty()) {
                eventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Wrong flight id provided!");
        }
        return eventStream.stream().map(EventModel::getEventData).toList();
    }

    @Override
    public List<String> getAggregateIds() {
        var events = eventStoreRepository.findAll();
        if (events.isEmpty()) {
            throw new IllegalStateException("Could not retrieve events from the event store.");
        }
        return events.stream()
                .map(EventModel::getAggregateIdentifier)
                .distinct()
                .toList();    }
}
