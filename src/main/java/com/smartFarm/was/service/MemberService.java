package com.smartFarm.was.service;

import com.smartFarm.was.dto.JoinDTO;
import com.smartFarm.was.repository.MemberRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

@Service
@MapperScan(basePackages = "com.smartFarm.was.repository")
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void addMember(JoinDTO joinDto) {
        memberRepository.addMember(joinDto);
    }
}
