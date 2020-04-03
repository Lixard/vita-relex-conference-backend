package ru.relex.services.dto.conference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.relex.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.services.constraint.ConstraintMessage.Field;

public class ConferenceDto {

    private int conferenceId;

    @NotBlank(message = Field.CONFERENCE_NAME + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.CONFERENCE_NAME + Constraint.TOO_LONG)
    private String conferenceName;

    private int owner;

    private boolean deleted;

    @Valid
    @NotNull(message = Field.DETAILS + Constraint.IS_NULL)
    private DetailsDto details;

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
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
