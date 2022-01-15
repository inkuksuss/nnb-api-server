//package com.smartFarm.was.web.config.security.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.smartFarm.was.domain.dto.response.Result;
//import com.smartFarm.was.domain.model.Member;
//import com.smartFarm.was.web.config.security.filter.JwtFilter;
//import com.smartFarm.was.web.config.security.provider.TokenProvider;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.HashMap;
//
//
//@Slf4j
//@RequiredArgsConstructor
//public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//    private final TokenProvider tokenProvider;
//    private final String ENCODING_UTF_8 = "application/json; charset=UTF-8";
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        log.info("auth = {}", authentication);
//        String jwt = tokenProvider.createToken(authentication);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//
//
//        response.setContentType(ENCODING_UTF_8);
//        ResponseEntity<Result<Member>> resultResponseEntity = onAuthenticationResponse(member, id);
//        objectMapper.writeValue(response.getWriter(), resultResponseEntity);
//    }
//
//    private ResponseEntity<Result<Member>> onAuthenticationResponse(Member member, String session) {
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        hashMap.put("member", member);
//        Result result = new Result(hashMap);
//        return ResponseEntity.ok().body(result);
//    }
//}
