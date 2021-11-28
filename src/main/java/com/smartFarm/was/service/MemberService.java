package com.smartFarm.was.service;

import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.dto.request.JoinDTO;
import com.smartFarm.was.dto.response.MemberDTO;
import com.smartFarm.was.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@MapperScan(basePackages = "com.smartFarm.was.repository")
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(memberEmail);
        if(member == null) {
            new UsernameNotFoundException("bbb");
        }
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return member;
    }

    public void addMember(JoinDTO joinDTO) {
        Member registered = memberRepository.findByEmail(joinDTO.getMemberEmail());
        Member member = new Member(joinDTO, "USER");
        memberRepository.save(member);
    }
}


