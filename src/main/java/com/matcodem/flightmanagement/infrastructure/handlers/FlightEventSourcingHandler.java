package com.matcodem.flightmanagement.infrastructure.handlers;

import com.matcodem.flightmanagement.application.events.BaseEvent;
import com.matcodem.flightmanagement.domain.aggregate.AggregateRoot;
import com.matcodem.flightmanagement.domain.aggregate.FlightAggregate;
import com.matcodem.flightmanagement.infrastructure.EventStore;
import com.matcodem.flightmanagement.infrastructure.producers.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightEventSourcingHandler implements EventSourcingHandler<FlightAggregate> {

    private final EventStore eventStore;
    private final EventProducer eventProducer;

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public FlightAggregate getById(String aggregateId) {
        var aggregate = new FlightAggregate();
        var events = eventStore.getEvents(aggregateId);
        if (events != null && !events.isEmpty()) {
            Optional<Integer> latestVersion = events.stream()
                    .map(BaseEvent::getVersion)
                    .max(Comparator.naturalOrder());
            latestVersion.ifPresent(aggregate::setVersion);
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        aggregateIds.forEach(aggregateId -> {
            var aggregate = getById(aggregateId);
            if (aggregate == null) return;
            var events = eventStore.getEvents(aggregateId);
            events.forEach(event -> eventProducer.produce(event.getClass().getSimpleName(), event));
        });
    }
}
