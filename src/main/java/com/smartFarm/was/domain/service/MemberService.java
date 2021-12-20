package com.smartFarm.was.domain.service;

import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.web.dto.request.JoinDto;
import com.smartFarm.was.domain.repository.MemberRepository;
import com.smartFarm.was.web.exception.custom.ExistMemberException;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.callback.TraceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final TraceTemplate traceTemplate;

    private final String USER_AUTHORITY = "USER";
    private final String ADMIN_AUTHORITY = "ADMIN";

    public MemberService(MemberRepository memberRepository, LogTrace trace) {
        this.memberRepository = memberRepository;
        this.traceTemplate = new TraceTemplate(trace);
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        return traceTemplate.execute("MemberService.loadUserByUsername()", () -> {
            Optional<Member> exist = memberRepository.findByEmail(memberEmail);
            exist.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
            Member member = new Member(exist);
            return member;
        });
    }

    public void addMember(JoinDto joinDTO) {
        traceTemplate.execute("MemberService.addMember()", () -> {
            memberRepository.findByEmail(joinDTO.getMemberEmail())
                    .ifPresent(m -> {
                        throw new ExistMemberException("이미 존재하는 회원입니다.");
                    });
            Member member = new Member(joinDTO, USER_AUTHORITY);
            memberRepository.save(member);
            return null;
        });
    }
}


