package ru.relex.services.dto.event;

import ru.relex.commons.model.EventType;
import ru.relex.services.constraint.annotation.ConferenceExists;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.relex.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.services.constraint.ConstraintMessage.Field;

public class EventDto {

    private int eventId;

    @NotBlank(message = Field.EVENT_NAME + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.EVENT_NAME + Constraint.TOO_LONG)
    private String eventName;

    @NotNull(message = Field.EVENT_TYPE + Constraint.IS_NULL)
    private EventType eventType;

    @ConferenceExists
    private int conferenceId;

    private boolean deleted;

    @Valid
    @NotNull(message = Field.DETAILS + Constraint.IS_NULL)
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
