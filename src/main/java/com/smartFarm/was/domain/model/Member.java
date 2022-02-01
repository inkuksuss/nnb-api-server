package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.dto.request.JoinForm;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
public class Member implements Serializable {

    private Long memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private String privacyConsent;
    private String memberAuthority;
    private Timestamp memberBirthday;
    private Timestamp memberCreated;
    private Timestamp memberLastUpdated;
    private Timestamp memberLastAccessed;

    public static Member from(JoinForm joinForm) {
        Member member = new Member();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        member.memberName = joinForm.getMemberName();
        member.memberPassword = joinForm.getMemberPassword();
        member.memberEmail = joinForm.getMemberEmail();
        member.memberPhone = joinForm.getMemberPhone();
        member.memberAddress = joinForm.getMemberAddress();
        member.privacyConsent = joinForm.getPrivacyConsent();
        member.memberBirthday = joinForm.getMemberBirthday();
        member.memberAuthority = joinForm.getMemberAuthority();
        member.memberCreated = now;
        member.memberLastUpdated = now;
        member.memberLastAccessed = now;
        return member;
    }
    private Member() {}

    public String getMemberAuthority() {
        return memberAuthority;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", privacyConsent='" + privacyConsent + '\'' +
                ", memberAuthority='" + memberAuthority + '\'' +
                ", memberBirthday=" + memberBirthday +
                ", memberCreated=" + memberCreated +
                ", memberLastUpdated=" + memberLastUpdated +
                ", memberLastAccessed=" + memberLastAccessed +
                '}';
    }

}

// "memberPhone": "01026727162",
// "memberAddress": "상동",
// "memberBirthday": "2020-10-11T01:01:01Z",
// "memberAuthority": ["USER"],
// "privacyConsent": "true",
// "memberPassword": "123"
