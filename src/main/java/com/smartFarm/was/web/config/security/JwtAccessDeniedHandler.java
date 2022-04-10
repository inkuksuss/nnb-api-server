package com.smartFarm.was.web.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final static String DEFAULT_ENCODING = "UTF-8";
    private final static String DEFAULT_CONTENT_TYPE = "application/json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {


        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(DEFAULT_CONTENT_TYPE);
        response.setCharacterEncoding(DEFAULT_ENCODING);

        ResultResponse resultResponse = new ResultResponse(HttpStatus.BAD_REQUEST, ResultCode.DENIED.getCode(), "접근 권한을 확인해주세요.");

        response.getWriter().write(objectMapper.writeValueAsString(resultResponse));
    }
}
