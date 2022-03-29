package com.smartFarm.was.web.config.security;

import com.smartFarm.was.domain.dto.member.MemberDto;
import com.smartFarm.was.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String memberEmail = authentication.getName();
        String memberPassword = (String) authentication.getCredentials();

        MemberDto memberDto = (MemberDto) memberService.loadUserByUsername(memberEmail);

        if (!passwordEncoder.matches(memberPassword, memberDto.getPassword())) {
            throw new BadCredentialsException("사용자를 찾을 수 없습니다.");
        }

        return new JwtAuthenticationToken(memberDto, "", memberDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
