package com.smartFarm.was.web.service;

import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.domain.dto.request.JoinForm;
import com.smartFarm.was.web.repository.MemberRepository;
import com.smartFarm.was.web.exception.custom.ExistMemberException;
import com.smartFarm.was.web.config.security.context.MemberContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final String USER_AUTHORITY = "ROLE_USER";
    private final String ADMIN_AUTHORITY = "ROLE_ADMIN";

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member existedMember = memberRepository.findByEmail(memberEmail);

        if (existedMember == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(existedMember.getMemberAuthority()));

        MemberContext memberDto = new MemberContext(existedMember, roles);
        return memberDto;
    }

    public void addMember(JoinForm joinDTO) {
        Member existedMember = memberRepository.findByEmail(joinDTO.getMemberEmail());

        if (!(USER_AUTHORITY.equals(joinDTO.getMemberAuthority()) || ADMIN_AUTHORITY.equals(joinDTO.getMemberAuthority()))) {
            throw new IllegalArgumentException("존재하지 않는 권한입니다.");
        }

        if (existedMember != null) {
            throw new ExistMemberException("이미 존재하는 회원입니다.");
        }

        Member member = new Member(joinDTO);
        memberRepository.saveMember(member);
    }

    public void addToken(String token, String memberEmail) {
        memberRepository.saveToken(token, memberEmail);
    }
}


