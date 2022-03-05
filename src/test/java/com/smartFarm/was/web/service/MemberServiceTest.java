package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.request.member.JoinForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@Slf4j
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void addMember() {
        JoinForm build = JoinForm.builder().
                memberAddress("상동").
                memberEmail("3@naver.com").
                memberName("sua").
                memberAuthority("ROLE_USER").
                memberPhone("010").
                memberBirthday(new Timestamp(System.currentTimeMillis())).
                memberPassword("123").
                build();
        build.setMemberPassword(passwordEncoder.encode(build.getMemberPassword()));
        memberService.addMember(build);
    }

    @Test
    void loadedByUserName() {
        UserDetails userDetails = memberService.loadUserByUsername("44433@naver.com");
        log.info("userDetails = {}" , userDetails.toString());
    }
}
