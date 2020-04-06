package ru.relex.security.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.commons.model.CurrentUser;
import ru.relex.services.service.IUserSecurityService;

@Service
public class UserSecurityService implements IUserSecurityService {

    private final CurrentUser currentUser;

    @Autowired
    public UserSecurityService(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean isTheSameUser(int userId) {
        return currentUser.getId() == userId;
    }
}
