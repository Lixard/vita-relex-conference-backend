package ru.relex.services.dto.user;

import ru.relex.commons.model.CurrentUser;
import ru.relex.commons.model.Role;

public class CurrentUserDto implements CurrentUser {

    private int id;
    private String username;
    private Role role;

    public CurrentUserDto(CurrentUser currentUser) {
        this.id = currentUser.getId();
        this.username = currentUser.getUsername();
        this.role = currentUser.getRole();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Role getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
