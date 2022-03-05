package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.request.member.LoginForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.member.MemberVO;
import com.smartFarm.was.domain.dto.member.MemberDto;
import com.smartFarm.was.domain.response.ResultVO;
import com.smartFarm.was.domain.request.member.JoinForm;
import com.smartFarm.was.web.config.security.filter.JwtFilter;
import com.smartFarm.was.web.config.security.provider.TokenProvider;
import com.smartFarm.was.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // 회원가입
    @PostMapping("/join")
    public ResultVO join(@RequestBody @Validated JoinForm joinForm) {

        joinForm.setMemberPassword(passwordEncoder.encode(joinForm.getMemberPassword()));
        memberService.addMember(joinForm);

        return new ResultVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    // 로그인
    @PostMapping("/login")
    public ResultVO login(@RequestBody @Validated LoginForm loginForm) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getMemberEmail(), loginForm.getMemberPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);

        MemberDto memberDto = (MemberDto) authentication.getPrincipal();
        MemberVO memberVO = MemberVO.from(memberDto, token);

        return new ResultVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), memberVO);
    }
}