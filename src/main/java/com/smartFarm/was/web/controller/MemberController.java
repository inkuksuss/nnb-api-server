package com.smartFarm.was.web.controller;

import com.smartFarm.was.domain.model.Member;
import com.smartFarm.was.domain.service.TokenService;
import com.smartFarm.was.web.dto.request.JoinDto;
import com.smartFarm.was.web.dto.request.LoginDto;
import com.smartFarm.was.domain.service.MemberService;
import com.smartFarm.was.web.logtrace.LogTrace;
import com.smartFarm.was.web.logtrace.callback.TraceTemplate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@Slf4j
@RestController
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final TraceTemplate traceTemplate;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder, LogTrace trace, TokenService tokenService) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.traceTemplate = new TraceTemplate(trace);
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<Result<String>> join(@RequestBody @Validated JoinDto joinDto) {

        return traceTemplate.execute("MemberController.join()", () -> {
            joinDto.setMemberPassword(passwordEncoder.encode(joinDto.getMemberPassword()));
            memberService.addMember(joinDto);
            return ResponseEntity.ok().body(new Result<>("success"));
        });
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Result<Member>> login(@RequestBody @Validated LoginDto loginDto) {

        return traceTemplate.execute("MemberController.login()", () -> {
            Member member = (Member) memberService.loadUserByUsername(loginDto.getMemberEmail());

            if (!passwordEncoder.matches(loginDto.getMemberPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호 불일치");
            }
            String token = tokenService.saveToken(member.getMemberId(), member.getMemberName(), member.getAuthorities());

            return ResponseEntity.ok().body(new Result<>(member, token));
        });
    }

    @GetMapping("/logout/{id}")
    public ResponseEntity<Result<>> logout(@RequestBody String token, @PathVariable Long id) {

    }


    @Getter
    static class Result<T> {
        private T data;
        private String token;
        private Timestamp timestamp;

        public Result(T data) {
            this.data = data;
            this.timestamp = new Timestamp(System.currentTimeMillis());
        }

        public Result(T data, String token) {
            this.data = data;
            this.token = token;
            this.timestamp = new Timestamp(System.currentTimeMillis());
        }
    }
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