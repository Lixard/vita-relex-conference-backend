package ru.relex.db.model;

import java.sql.Timestamp;

public class PhotoArchive {
    private int photoId;
    private int conferenceId;
    private String url;
    private String description;
    private Timestamp createdAt;


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

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getConferenceId() {
        return conferenceId;
    }
}
