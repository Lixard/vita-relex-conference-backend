package ru.relex.services.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static ru.relex.services.constraint.ConstraintMessage.Field;
import static ru.relex.services.constraint.ConstraintMessage.Constraint;

public class PersonalInfoDto {

    @NotBlank(message = Field.FIRST_NAME + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.FIRST_NAME + Constraint.TOO_LONG)
    private String firstName;

    @NotBlank(message = Field.LAST_NAME + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.LAST_NAME + Constraint.TOO_LONG)
    private String lastName;

    @NotBlank(message = Field.EMAIL + Constraint.IS_EMPTY)
    @Size(max = 50, message = Field.EMAIL + Constraint.TOO_LONG)
    @Email(message = Field.EMAIL + Constraint.NOT_EMAIL)
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
