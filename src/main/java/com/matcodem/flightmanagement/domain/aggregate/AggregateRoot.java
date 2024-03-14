package com.matcodem.flightmanagement.domain.aggregate;

import com.matcodem.flightmanagement.application.events.BaseEvent;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AggregateRoot {
    protected String id;
    private int version = -1;

    /**
     * List of changes that are yet to be committed.
     */
    private final List<BaseEvent> changes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesAsCommitted() {
        this.changes.clear();
    }

    public void raiseEvent(BaseEvent event) {
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChange(event, false));
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            log.warn("The apply method was not found in the aggregate for {}", event.getClass().getName());
        } catch (Exception e) {
            log.error("Error applying event to aggregate", e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }
}
