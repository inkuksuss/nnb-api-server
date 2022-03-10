package com.smartFarm.was.web.interceptor;

import com.smartFarm.was.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MemberFromAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null)  return false;
            if (!(authentication.getPrincipal() instanceof Map)) return false;
            try {
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
            } catch (Exception e) {
                log.error("에러 발생 = {}", e.getMessage());
                throw new IllegalStateException("인증 정보를 확인할 수 없습니다.");
            }
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
