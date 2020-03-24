package ru.relex.services.dto.speaker;


import ru.relex.services.constraint.annotation.EventExists;
import ru.relex.services.constraint.annotation.UserExists;

import java.sql.Timestamp;

public class EventSpeakerDto {

    @UserExists
    private int userId;

    @EventExists
    private int eventId;

    @UserExists
    private int createdBy;

    private Timestamp createdAt;

    private boolean deleted;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}


