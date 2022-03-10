package com.smartFarm.was.domain.response.member;

import com.smartFarm.was.domain.dto.member.MemberDto;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String memberAuthority;
    private String token;

    public static MemberResponse from(MemberDto memberDto, String token) {
        MemberResponse memberResponse = new MemberResponse();
        memberResponse.memberName = memberDto.getMember().getMemberName();
        memberResponse.memberAddress = memberDto.getMember().getMemberAddress();
        memberResponse.memberAuthority = memberDto.getMember().getMemberAuthority();
        memberResponse.memberEmail = memberDto.getMember().getMemberEmail();
        memberResponse.memberPhone = memberDto.getMember().getMemberPhone();
        memberResponse.token = token;
        return memberResponse;
    }
}
