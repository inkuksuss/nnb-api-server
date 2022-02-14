package com.smartFarm.was.web.config.security.interceptor;

import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.web.config.security.context.MemberContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Slf4j
public class GetMemberIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


//        UserDetails principal = (UserDetails) authentication.getPrincipal();

//        if (authentication == null) {
//            log.debug("Security Context에 인증 정보가 없습니다.");
//            return false;
//        }

//        UserDetails principal = (UserDetails) authentication.getPrincipal();
//        String username1 = principal.getUsername();
//        log.info("user = {}", userDetails.getUsername());
//        if (authentication.getPrincipal() instanceof UserDetails) {
////            String name = (String) authentication.getPrincipal();
//        } else if (authentication.getPrincipal() instanceof String) {
//            String username = (String) authentication.getPrincipal();
//            log.info("username = {}", username);
//        }
//        request.setAttribute("id", 1);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("hello");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("hello");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
