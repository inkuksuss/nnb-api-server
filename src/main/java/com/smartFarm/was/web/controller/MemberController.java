package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.dto.request.member.LoginForm;
import com.smartFarm.was.domain.dto.response.member.MemberDto;
import com.smartFarm.was.web.config.security.context.MemberContext;
import com.smartFarm.was.domain.dto.response.Result;
import com.smartFarm.was.domain.dto.request.member.JoinForm;
import com.smartFarm.was.web.config.security.filter.JwtFilter;
import com.smartFarm.was.web.config.security.provider.TokenProvider;
import com.smartFarm.was.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result<String>> join(@RequestBody @Validated JoinForm joinForm) {

        joinForm.setMemberPassword(passwordEncoder.encode(joinForm.getMemberPassword()));
        memberService.addMember(joinForm);

        return ResponseEntity.ok().body(new Result<>("success"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Result<MemberDto>> login(@RequestBody @Validated LoginForm loginForm) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginForm.getMemberEmail(), loginForm.getMemberPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token);
        MemberContext memberContext = (MemberContext) authentication.getPrincipal();
        MemberDto memberDto = MemberDto.from(memberContext, token);

        return new ResponseEntity<>(new Result<>(memberDto), httpHeaders, HttpStatus.OK);
    }
}