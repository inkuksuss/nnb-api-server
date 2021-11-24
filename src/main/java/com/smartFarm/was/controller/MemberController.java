package com.smartFarm.was.controller;


import com.smartFarm.was.dto.JoinDTO;
import com.smartFarm.was.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinDTO joinDTO) {
        System.out.println("joinDto = " + joinDTO);
        memberService.addMember(joinDTO);
        return "success";
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        return "hello";
    }
//
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
