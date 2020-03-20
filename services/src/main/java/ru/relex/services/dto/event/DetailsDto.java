package ru.relex.services.dto.event;

import java.sql.Timestamp;

public class DetailsDto {
    private String htmlDescription;
    private String location;
    private Timestamp timeStart;
    private Timestamp timeEnd;
    private int createdBy;

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}
