package ru.relex.services.dto.conference;

import ru.relex.services.constraint.annotation.ConferenceExists;

import java.sql.Timestamp;

public class PhotoArchiveDto {
    private int photoId;
    @ConferenceExists
    private int conferenceId;
    private String url;
    private String description;
    private Timestamp createdAt;

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
