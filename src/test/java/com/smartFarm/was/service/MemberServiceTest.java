package com.smartFarm.was.service;

import com.smartFarm.was.domain.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void findMember() {
        String email = "inguk@naver.com";
        Member member = (Member) memberService.loadUserByUsername(email);
        System.out.println("member = " + member);
    }
}
