package ru.relex.services.dto.conference;

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

    @NotNull(message = Field.DATE_START + Constraint.IS_NULL)
    private Timestamp dateStart;

    @NotNull(message = Field.DATE_END + Constraint.IS_NULL)
    private Timestamp dateEnd;

    private Timestamp createdAt;

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

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
