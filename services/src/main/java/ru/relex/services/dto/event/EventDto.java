package ru.relex.services.dto.event;

import ru.relex.commons.model.EventType;

import java.sql.Timestamp;

public class EventDto {
    private int eventId;
    private String eventName;
    private EventType eventType;
    private int conferenceId;
    private boolean deleted;
    private DetailsDto details;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DetailsDto getDetails() {
        return details;
    }

    public void setDetails(DetailsDto details) {
        this.details = details;
    }
}
