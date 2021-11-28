package com.smartFarm.was.controller;

import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.dto.request.JoinDTO;
import com.smartFarm.was.dto.request.LoginDTO;
import com.smartFarm.was.jwt.TokenProvider;
import com.smartFarm.was.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody JoinDTO joinDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "false";
        }
        joinDTO.setMemberPassword(passwordEncoder.encode(joinDTO.getMemberPassword()));
        memberService.addMember(joinDTO);
        return "Success";
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "false";
        }
        UserDetails member = memberService.loadUserByUsername(loginDTO.getMemberEmail());
        log.info("member ={} ", member.toString());
        if (!passwordEncoder.matches(loginDTO.getMemberPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return tokenProvider.createToken(member.getUsername(), member.getAuthorities());
    }

//    @PostMapping("/login")
//    public String login(@RequestBody LoginDTO )

//    @GetMapping("/api/v2/members")
//    public Result membersV2() {
//        List<Member> findMembers = memberService.findMembers();
//        List<MemberDto> collect = findMembers.stream()
//                .map(m -> new MemberDto(m.getName()))
//                .collect(Collectors.toList());
//        return new Result(collect);
//    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
