package ru.relex.services.dto.shedule;


import ru.relex.services.constraint.annotation.EventExists;
import ru.relex.services.constraint.annotation.UserExists;

public class EventVisitorDto {

    @UserExists
    private int userId;

    @EventExists
    private int eventId;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
