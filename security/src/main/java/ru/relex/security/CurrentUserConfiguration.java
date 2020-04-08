package ru.relex.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import ru.relex.commons.model.CurrentUser;
import ru.relex.commons.model.Role;

@Configuration
public class CurrentUserConfiguration {
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    CurrentUser currentUser() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == "anonymousUser") {
            return new CurrentUser() {
                @Override
                public int getId() {
                    return 0;
                }

                @Override
                public String getUsername() {
                    return null;
                }

                @Override
                public Role getRole() {
                    return null;
                }

                @Override
                public boolean isAuthenticated() {
                    return false;
                }
            };
        }
        return (CurrentUser) principal;
    }
}
