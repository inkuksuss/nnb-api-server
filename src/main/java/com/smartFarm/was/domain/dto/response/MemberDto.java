package com.smartFarm.was.domain.dto.response;

import com.smartFarm.was.web.config.security.context.MemberContext;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class MemberDto {

    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String memberAuthority;

    public static MemberDto from(MemberContext memberContext) {
        MemberDto memberDto = new MemberDto();
        memberDto.memberName = memberContext.getMember().getMemberName();
        memberDto.memberAddress = memberContext.getMember().getMemberAddress();
        memberDto.memberAuthority = memberContext.getMember().getMemberAuthority();
        memberDto.memberEmail = memberContext.getMember().getMemberEmail();
        memberDto.memberPhone = memberContext.getMember().getMemberPhone();
        return memberDto;
    }
}
