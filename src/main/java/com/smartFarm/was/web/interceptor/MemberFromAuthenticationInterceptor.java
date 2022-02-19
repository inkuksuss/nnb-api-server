package com.smartFarm.was.web.interceptor;

import com.smartFarm.was.domain.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MemberFromAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Map<String, Object> principal = (LinkedHashMap) authentication.getPrincipal();
            Integer memberId = (Integer) principal.get("memberId");
            Member member = Member.of(
                    memberId.longValue(),
                    (String) principal.get("memberName"),
                    "",
                    (String) principal.get("memberEmail"),
                    (String) principal.get("memberPhone"),
                    (String) principal.get("memberAddress"),
                    (String) principal.get("privacyConsent"),
                    (String) principal.get("memberAuthority"),
                    new Timestamp((long) principal.get("memberBirthday")),
                    new Timestamp((long) principal.get("memberCreated")),
                    new Timestamp((long) principal.get("memberLastUpdated")),
                    new Timestamp((long) principal.get("memberLastAccessed")));
            request.setAttribute("member", member);
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
