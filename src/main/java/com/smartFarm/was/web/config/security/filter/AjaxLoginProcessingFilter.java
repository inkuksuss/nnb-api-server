//package com.smartFarm.was.web.config.security.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.smartFarm.was.domain.dto.request.LoginDto;
//import com.smartFarm.was.web.config.security.JwtAuthenticationToken;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//    private final String XMLHTTPREQUEST = "XMLHttpRequest";
//    private final String X_REQUEST = "X-Requested-With";
//
//    public AjaxLoginProcessingFilter() {
//        super(new AntPathRequestMatcher("/login"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//
//        if(!isAjax(request)) {
//            throw new IllegalStateException("지원하지 않는 규약입니다.");
//        }
//
//        LoginDto loginDto = objectMapper.readValue(request.getReader(), LoginDto.class);
//        JwtAuthenticationToken ajaxAuthenticationToken = new JwtAuthenticationToken(loginDto.getMemberEmail(), loginDto.getMemberPassword());
//
//        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
//    }
//
//
//}
