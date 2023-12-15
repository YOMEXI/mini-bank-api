package com.example.bank.Exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AccessDenied implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println(response.getStatus());

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED; // 401

        Map<String, Object> errorDetails = new HashMap<>();

        errorDetails.put("code", httpStatus.value());
        errorDetails.put("status", httpStatus.name());
        errorDetails.put( "message", "UnAuthorized to perform action");

        response
                .getOutputStream()
                .println(
                        objectMapper.writeValueAsString(errorDetails)
                );
    }
}
