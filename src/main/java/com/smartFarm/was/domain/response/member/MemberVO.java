package com.smartFarm.was.domain.response.member;

import com.smartFarm.was.domain.dto.member.MemberDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {

    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String memberAuthority;
    private String token;

    public static MemberVO from(MemberDto memberContext, String token) {
        MemberVO memberDto = new MemberVO();
        memberDto.memberName = memberContext.getMember().getMemberName();
        memberDto.memberAddress = memberContext.getMember().getMemberAddress();
        memberDto.memberAuthority = memberContext.getMember().getMemberAuthority();
        memberDto.memberEmail = memberContext.getMember().getMemberEmail();
        memberDto.memberPhone = memberContext.getMember().getMemberPhone();
        memberDto.token = token;
        return memberDto;
    }
}
