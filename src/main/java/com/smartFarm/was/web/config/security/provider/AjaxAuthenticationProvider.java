package com.smartFarm.was.web.config.security.provider;

import com.smartFarm.was.web.config.security.JwtAuthenticationToken;
import com.smartFarm.was.web.config.security.context.MemberContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


public class AjaxAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AjaxAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberContext memberContext = (MemberContext) userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, memberContext.getMember().getMemberPassword())) {
            throw new BadCredentialsException("비밀번호 불일치");
        }

        return new JwtAuthenticationToken(memberContext.getMember(), null, memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
