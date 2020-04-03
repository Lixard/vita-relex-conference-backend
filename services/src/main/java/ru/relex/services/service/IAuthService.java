package ru.relex.services.service;

import ru.relex.services.dto.user.CurrentUserDto;

public interface IAuthService {
    CurrentUserDto getCurrentUser();
}
