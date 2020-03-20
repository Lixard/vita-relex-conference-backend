package ru.relex.services.dto.conference;

public class ConferenceDto {
    private int conferenceId;
    private String conferenceName;
    private int owner;
    private boolean deleted = false;
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
