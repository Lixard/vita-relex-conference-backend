package ru.relex.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.relex.security.model.AuthenticationErrorModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AppEntryPoint implements AuthenticationEntryPoint {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (response.getStatus() != HttpServletResponse.SC_FORBIDDEN) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            AuthenticationErrorModel error = new AuthenticationErrorModel();
            error.setAuthenticated(false);
            response.getWriter().print(OBJECT_MAPPER.writeValueAsString(error));
        }
    }
}
