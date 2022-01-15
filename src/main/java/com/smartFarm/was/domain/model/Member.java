package com.smartFarm.was.domain.model;

import com.smartFarm.was.domain.dto.request.JoinForm;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
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

    public Member(JoinForm joinDTO) {
        this.memberName = joinDTO.getMemberName();
        this.memberPassword = joinDTO.getMemberPassword();
        this.memberEmail = joinDTO.getMemberEmail();
        this.memberPhone = joinDTO.getMemberPhone();
        this.memberAddress = joinDTO.getMemberAddress();
        this.privacyConsent = joinDTO.getPrivacyConsent();
        this.memberBirthday = joinDTO.getMemberBirthday();
        this.memberAuthority = joinDTO.getMemberAuthority();
        this.memberCreated = new Timestamp(System.currentTimeMillis());
        this.memberLastUpdated = new Timestamp(System.currentTimeMillis());
        this.memberLastAccessed = new Timestamp(System.currentTimeMillis());
    }

    public String getMemberAuthority() {
        return memberAuthority;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }
}

// "memberPhone": "01026727162",
// "memberAddress": "상동",
// "memberBirthday": "2020-10-11T01:01:01Z",
// "memberAuthority": ["USER"],
// "privacyConsent": "true",
// "memberPassword": "123"
