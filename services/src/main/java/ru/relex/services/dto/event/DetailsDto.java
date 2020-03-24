package ru.relex.services.dto.event;

import ru.relex.services.constraint.annotation.UserExists;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

import static ru.relex.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.services.constraint.ConstraintMessage.Field;

public class DetailsDto {

    @NotBlank(message = Field.HTML_DESCRIPTION + Constraint.IS_EMPTY)
    private String htmlDescription;

    @NotBlank(message = Field.LOCATION + Constraint.IS_EMPTY)
    @Size(max = 100, message = Field.LOCATION + Constraint.TOO_LONG)
    private String location;

    @NotNull(message = Field.TIME_START + Constraint.IS_NULL)
    private Timestamp timeStart;

    @NotNull(message = Field.TIME_END + Constraint.IS_NULL)
    private Timestamp timeEnd;

    @UserExists
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
