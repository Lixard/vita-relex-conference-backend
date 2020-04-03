package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.commons.model.CurrentUser;
import ru.relex.services.dto.user.CurrentUserDto;
import ru.relex.services.service.IAuthService;

@Service
public class AuthService implements IAuthService {

    private final CurrentUser currentUser;

    @Autowired
    public AuthService(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public CurrentUserDto getCurrentUser() {
        return new CurrentUserDto(currentUser);
    }
}
