package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.repository.MemberRepository;
import com.smartFarm.was.domain.service.MemberService;
import com.smartFarm.was.web.config.jwt.TokenProvider;
import com.smartFarm.was.web.dto.request.JoinDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MemberControllerTest {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void join() {
        JoinDto joinDto = new JoinDto(
                "hello",
                "1@naver.com",
                "1",
                "010",
                "상동",
                "t",
                new Timestamp(System.currentTimeMillis())
                );
        joinDto.setMemberPassword(passwordEncoder.encode(joinDto.getMemberPassword()));
        memberService.addMember(joinDto);
    }

    @Test
    void login() {
    }
}