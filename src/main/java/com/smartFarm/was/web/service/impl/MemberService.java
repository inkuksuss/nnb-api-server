package com.smartFarm.was.web.service.impl;

import com.smartFarm.was.domain.entity.Member;
import com.smartFarm.was.domain.entity.sub.Authority;
import com.smartFarm.was.domain.entity.sub.Status;
import com.smartFarm.was.domain.request.member.JoinForm;
import com.smartFarm.was.domain.response.ResultCode;
import com.smartFarm.was.domain.response.ResultResponse;
import com.smartFarm.was.web.repository.MemberRepository;
import com.smartFarm.was.web.exception.custom.ExistedMemberException;
import com.smartFarm.was.domain.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
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

    @Transactional
    public ResultResponse<Void> addMember(Member member) throws Exception {

        if(memberRepository.findByEmail(member.getMemberEmail()).isPresent()) {
            throw new ExistedMemberException(messageSource.getMessage("duplicate.parameter", new Object[]{"이메일"}, null));
        } else {
            int result = memberRepository.addMember(member);

            if (result == 1) {
                return new ResultResponse<>(HttpStatus.OK, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
            } else {
                return new ResultResponse<>(HttpStatus.BAD_REQUEST, ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
            }
        }
    }

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
}


