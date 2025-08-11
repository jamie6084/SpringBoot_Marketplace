package com.example.demo.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        // Log detailed error information

        //fr errors stuff
        System.out.println("Authentication failed: " + exception.getMessage());

        //adding to fix bug and find the route of problem
        request.setAttribute("errorMessage", "Authentication failed: " + exception.getMessage());
        
        //putting back to login to fix.
        response.sendRedirect("/login?error=true&message=" + exception.getMessage());
    }
}

