package com.smartFarm.was.domain.response.member;

import com.smartFarm.was.domain.dto.member.MemberDto;
import com.smartFarm.was.domain.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class LoginResponse {

    private Member member;
    private String token;

    public static LoginResponse of(MemberDto memberDto, String token) {
        LoginResponse memberResponse = new LoginResponse();
        memberResponse.member = memberDto.getMember();
        memberResponse.token = token;
        return memberResponse;
    }

    public LoginResponse(){}
}
