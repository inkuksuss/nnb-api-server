package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.entity.sub.Authority;
import com.smartFarm.was.domain.request.member.LoginForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.member.LoginResponse;
import com.smartFarm.was.domain.dto.member.MemberDto;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.domain.request.member.JoinForm;
import com.smartFarm.was.web.config.security.JwtAuthenticationToken;
import com.smartFarm.was.web.config.security.filter.JwtFilter;
import com.smartFarm.was.web.config.security.provider.TokenProvider;
import com.smartFarm.was.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final MessageSource messageSource;


    @PostMapping("/join")
    public ResultResponse join(@RequestBody @Validated JoinForm joinForm) throws Exception {

        joinForm.setMemberPassword(passwordEncoder.encode(joinForm.getMemberPassword()));

        String memberAuthority = joinForm.getMemberAuthority();

        if (!(Authority.ADMIN.getRole().equals(memberAuthority) || Authority.MEMBER.getRole().equals(memberAuthority))) {
            throw new IllegalArgumentException(messageSource.getMessage("illegal.parameter", new Object[]{"권한"}, null));
        }

        memberService.addMember(joinForm);

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    @PostMapping("/login")
    public ResultResponse login(@RequestBody @Validated LoginForm loginForm) throws Exception {

        JwtAuthenticationToken jwtAuthenticationToken =
                new JwtAuthenticationToken(loginForm.getMemberEmail(), loginForm.getMemberPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(jwtAuthenticationToken);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        LoginResponse loginResponse = LoginResponse.of(memberDto, token);

        return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), loginResponse);
    }
}