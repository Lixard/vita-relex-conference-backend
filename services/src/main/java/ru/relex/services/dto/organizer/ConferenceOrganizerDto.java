package ru.relex.services.dto.organizer;

import ru.relex.services.constraint.annotation.ConferenceExists;
import ru.relex.services.constraint.annotation.UserExists;

import java.sql.Timestamp;

public class ConferenceOrganizerDto {

    @UserExists
    private int userId;

    @ConferenceExists
    private int conferenceId;

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

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
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

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
