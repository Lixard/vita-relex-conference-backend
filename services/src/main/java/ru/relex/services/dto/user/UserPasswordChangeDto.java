package ru.relex.services.dto.user;

import ru.relex.services.constraint.ConstraintMessage;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserPasswordChangeDto {

    private int id;

    @NotBlank(message = ConstraintMessage.Field.PASSWORD + ConstraintMessage.Constraint.IS_EMPTY)
    private String oldPassword;

    @Size(min = 8, message = ConstraintMessage.Field.PASSWORD + ConstraintMessage.Constraint.TOO_SHORT)
    @NotBlank(message = ConstraintMessage.Field.PASSWORD + ConstraintMessage.Constraint.IS_EMPTY)
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public final int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
