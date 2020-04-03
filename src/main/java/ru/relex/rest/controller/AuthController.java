package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.relex.commons.model.CurrentUser;
import ru.relex.services.service.IAuthService;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {

    private final IAuthService authService;

    @Autowired
    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/auth/this")
    public CurrentUser currentUser() {
        return authService.getCurrentUser();
    }
}
