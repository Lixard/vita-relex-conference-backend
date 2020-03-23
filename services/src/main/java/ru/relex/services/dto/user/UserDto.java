package ru.relex.services.dto.user;

import org.hibernate.validator.constraints.UniqueElements;
import ru.relex.commons.model.Role;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.relex.services.constraint.ConstraintMessage.Field;
import static ru.relex.services.constraint.ConstraintMessage.Constraint;

public class UserDto {

    private int userId;

    //TODO Нужно сделать свою аннотацию для проверки никнейма на уникальность
    @NotBlank(message = Field.USERNAME + Constraint.IS_EMPTY)
    private String username;

    @Size(min = 8, message = Field.PASSWORD + Constraint.TOO_SHORT)
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
