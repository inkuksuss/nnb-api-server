package com.smartFarm.was.web.utils;

import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.domain.entity.sub.Authority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MemberAuthenticationUtils {

    private MemberAuthenticationUtils() {}

    public static boolean isAnonymous() {
        return getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(Authority.ANONYMOUS.getRole()));
    }

    public static boolean isAdmin() {
        return getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(Authority.ADMIN.getRole()));
    }

    public static boolean isMember() {
        return getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority(Authority.MEMBER.getRole()));
    }

    public static Member getMemberAuthentication() {
        return getMemberFromAuthentication(getAuthentication());
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private static Member getMemberFromAuthentication(Authentication authentication) {
        try {
            Map<String, Object> principal = (LinkedHashMap) authentication.getPrincipal();
            Integer memberId = (Integer) principal.get("memberId");
            return Member.of(
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
        } catch (Exception e) {
            log.error("에러 발생 = {}", e.getMessage());
            throw new IllegalStateException("인증 정보를 확인할 수 없습니다.");
        }
    }
}
