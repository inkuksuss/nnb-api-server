package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.domain.entity.sub.Authority;
import com.smartFarm.was.domain.request.member.JoinForm;
import com.smartFarm.was.web.repository.MemberRepository;
import com.smartFarm.was.web.exception.custom.ExistedMemberException;
import com.smartFarm.was.domain.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MessageSource messageSource;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getMemberAuthority()));

        MemberDto memberDto = new MemberDto(member, roles);

        return memberDto;
    }

    @Transactional
    public void addMember(JoinForm joinForm) throws Exception {

        memberRepository.findByEmail(joinForm.getMemberEmail())
                .orElseThrow(() -> new ExistedMemberException(messageSource.getMessage("duplicate.parameter", new Object[]{"이메일"}, null)));

        Member member = Member.from(joinForm);

        memberRepository.addMember(member);
    }
}


