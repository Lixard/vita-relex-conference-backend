package ru.relex.security.model;

import ru.relex.commons.model.CurrentUser;
import ru.relex.commons.model.Role;

public class LoginSuccessModel implements CurrentUser {

    private int id;
    private String username;
    private Role role;
    private boolean authenticated;

    public LoginSuccessModel(CurrentUser info) {
        this.id = info.getId();
        this.username = info.getUsername();
        this.role = info.getRole();
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
