package com.smartFarm.was.domain.response.member;

import com.smartFarm.was.domain.dto.member.MemberDto;
import lombok.Getter;

@Getter
public class LoginResponse {

    private String memberName;
    private String token;

    public static LoginResponse of(MemberDto memberDto, String token) {
        LoginResponse memberResponse = new LoginResponse();
        memberResponse.memberName = memberDto.getMember().getMemberName();
        memberResponse.token = token;
        return memberResponse;
    }
}
