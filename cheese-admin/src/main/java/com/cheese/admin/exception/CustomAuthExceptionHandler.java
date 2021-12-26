package com.cheese.admin.exception;

import com.cheese.admin.model.response.CustomErrorResponse;
import com.cheese.admin.model.response.ErrorResponse;
import com.cheese.core.exception.CheeseCode;
import com.cheese.core.model.response.CheeseErrorResponse;
import com.cheese.core.model.response.CheeseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class CustomAuthExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        CheeseCode cheeseCode = CheeseCode.UNAUTHORIZED;
        CheeseErrorResponse res = CheeseErrorResponse.builder()
                                        .message(cheeseCode.getMessage())
                                        .code(cheeseCode.getCode())
                                        .status(cheeseCode.getStatus())
                                        .build();

        String serializer = new Gson().toJson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(serializer);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Throwable cause = authException.getCause();
        CheeseCode cheeseCode = CheeseCode.UNAUTHORIZED;
        if (cause instanceof InvalidTokenException) {
            cheeseCode = CheeseCode.UNAUTHORIZED_INVALID_ACCESS_TOKEN;
        }
        CheeseErrorResponse res = CheeseErrorResponse.builder()
                .message(cheeseCode.getMessage())
                .code(cheeseCode.getCode())
                .status(cheeseCode.getStatus())
                .build();

        String serializer = new Gson().toJson(res);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(serializer);
    }
}