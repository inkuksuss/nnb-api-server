package com.smartFarm.was.web.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartFarm.was.web.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AjaxAuthenticationFailHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final String ENCODING_UTF_8 = "application/json; charset=UTF-8";


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "잘못된 이메일 혹은 비밀번호입니다.";


        if (exception instanceof BadCredentialsException) {
            errorMessage = "잘못된 이메일 혹은 비밀번호입니다.";
        } else if (exception instanceof DisabledException) {
            errorMessage = "잠긴 계정입니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "잘못된 비밀번호 입니다.";
        }

        response.setContentType(ENCODING_UTF_8);
        ResponseEntity<ErrorResult> errorResultResponseEntity = onAuthenticationResponse(errorMessage);

        objectMapper.writeValue(response.getWriter(), errorResultResponseEntity);
    }

    private ResponseEntity<ErrorResult> onAuthenticationResponse(String errorMessage) {

        ErrorResult errorResult = new ErrorResult(errorMessage);
        return ResponseEntity.badRequest().body(errorResult);
    }
}
