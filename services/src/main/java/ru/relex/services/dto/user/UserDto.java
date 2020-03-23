package ru.relex.services.dto.user;

import ru.relex.commons.model.Role;
import ru.relex.services.constraint.UniqueUsername;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.relex.services.constraint.ConstraintMessage.Constraint;
import static ru.relex.services.constraint.ConstraintMessage.Field;

public class UserDto {

    private int userId;

    @NotBlank(message = Field.USERNAME + Constraint.IS_EMPTY)
    @UniqueUsername(message = Field.USERNAME + Constraint.NOT_UNIQUE)
    private String username;

    @Size(min = 8, message = Field.PASSWORD + Constraint.TOO_SHORT)
    @NotBlank(message = Field.PASSWORD + Constraint.IS_EMPTY)
    private String password;

    @NotNull(message = Field.ROLE + Constraint.IS_NULL)
    private Role role;

    private Boolean deleted;

    @Valid
    @NotNull(message = Field.PERSONAL_INFO + Constraint.IS_NULL)
    private PersonalInfoDto personalInfo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public PersonalInfoDto getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoDto personalInfo) {
        this.personalInfo = personalInfo;
    }
}
